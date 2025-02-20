package com.akropoliprishtine.services;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.Organization;
import com.akropoliprishtine.entities.Role;
import com.akropoliprishtine.enums.UserRolesEnum;
import com.akropoliprishtine.repositories.UserRepository;
import com.akropoliprishtine.services.book.OrganizationService;
import com.akropoliprishtine.utils.GeneralConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    AmazonClient amazonClient;
    
    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    ObjectMapper objectMapper;
    
    @Autowired
    ApplicationUserService userService;
    
    @Autowired
    RoleService roleService;
    
    @Autowired
    OrganizationService organizationService;

    public ApplicationUserService(UserRepository userRepository,
                                  EmailService emailService,
                                  ObjectMapper objectMapper,
                                  JwtUserDetailsService jwtUserDetailsService,
                                  RoleService roleService,
                                  OrganizationService organizationService,
                                  AmazonClient amazonClient) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.emailService = emailService;
        this.amazonClient = amazonClient;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.roleService = roleService;
        this.organizationService = organizationService;
    }


    public ApplicationUser loadUserByUsernameAndPassword(String username, String password) throws UsernameNotFoundException {
        ApplicationUser applicationUser = userRepository.findByUsername(username);
        if (applicationUser == null || !checkPassword(applicationUser, password)) {
            throw new UsernameNotFoundException(username);
        }
        return applicationUser;
    }

    public ApplicationUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<ApplicationUser> findById(Long id) {
        return userRepository.findById(id);
    }

    public ApplicationUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public List<ApplicationUser> getUsers(long organization) {
        ApplicationUser loggedUser = this.userService.getLoggedUser();

        Organization org;

        if ((loggedUser.getRole().getName().equals(UserRolesEnum.KK.label) ||
                loggedUser.getRole().getName().equals(UserRolesEnum.ADMIN.label) &&
                        organization != 0)
        ) {
            org = organizationService.getOrganizationById(organization);
        } else {
            org = loggedUser.getOrganization();
        }
        
        return this.userRepository.findAllByOrganization(org);
    }
    
    public ApplicationUser getLoggedUser() {
        return this.jwtUserDetailsService.getUserFromToken();
    }

    public Optional<ApplicationUser> getUserById(long id) {
        return this.userRepository.findById(id);
    }

    public Optional<ApplicationUser> getPersonalData() {
        ApplicationUser user = this.getLoggedUser();
        System.out.println(user.getId());
        return this.getUserById(user.getId());
    }

    public List<ApplicationUser> getUsersByRole(Role role) {
        return this.userRepository.findAllByRole(role);
    }

    public ApplicationUser createUser(JsonNode jsonNode) {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setFirstName(jsonNode.get("firstName").textValue());
        applicationUser.setLastName(jsonNode.get("lastName").textValue());
        applicationUser.setEmail(jsonNode.get("email").textValue());
        applicationUser.setDateOfBirth(jsonNode.get("dateOfBirth").textValue());
        applicationUser.setRole(objectMapper.convertValue(jsonNode.get("role"), Role.class));
        String username = applicationUser.getFirstName() + applicationUser.getLastName();
        applicationUser.setUsername(username.toLowerCase());
        applicationUser.setPassword(GeneralConstants.DEFAULT_USER_PASSWORD);
        
        ApplicationUser loggedUser = this.userService.getLoggedUser();
        
        if (loggedUser.getRole().getName().equals(UserRolesEnum.ADMIN.label) ||
            loggedUser.getRole().getName().equals(UserRolesEnum.KK.label)) {
            applicationUser.setOrganization(objectMapper.convertValue(jsonNode.get("organization"), Organization.class));
        } else {
            applicationUser.setOrganization(loggedUser.getOrganization());
        }
        
        ApplicationUser user = this.userRepository.save(applicationUser);
        this.emailService.sendWelcomeMail(user.getEmail(), user.getUsername().toLowerCase());

        return user;
    }

    public ApplicationUser changePassword (JsonNode jsonNode) throws Exception {
        ApplicationUser user = this.userRepository.getOne(Long.parseLong(jsonNode.get("id").toString()));

        if (checkPassword(user, jsonNode.get("oldPassword").textValue())) {
            String encodedPassword = passwordEncoder.encode(jsonNode.get("newPassword").textValue());
            user.setPassword(encodedPassword);
            return this.userRepository.save(user);
        } {
            throw new Exception("The old password is not the same");
        }
    }

    public ApplicationUser updateMyUser(ApplicationUser applicationUser) {
        ApplicationUser user = this.jwtUserDetailsService.getUserFromToken();

        if (user.getId() != applicationUser.getId()) {
            return null;
        }

        updateUser(applicationUser);

        return applicationUser;
    }

    public ApplicationUser updateUser(ApplicationUser applicationUser) {
        ApplicationUser userToUpdate = userRepository.findById(applicationUser.getId()).get();

        userToUpdate.setEmail(applicationUser.getEmail());
        userToUpdate.setFirstName(applicationUser.getFirstName());
        userToUpdate.setLastName(applicationUser.getLastName());
        userToUpdate.setRole(applicationUser.getRole());
        userToUpdate.setDateOfBirth(applicationUser.getDateOfBirth());
        userToUpdate.setAllowProfessionalEmail(applicationUser.getAllowProfessionalEmail());
        userToUpdate.setProfessionalLabels(applicationUser.getProfessionalLabels());


        ApplicationUser loggedUser = this.userService.getLoggedUser();

        if (loggedUser.getRole().getName().equals(UserRolesEnum.ADMIN.label) ||
                loggedUser.getRole().getName().equals(UserRolesEnum.KK.label)) {
            applicationUser.setOrganization(userToUpdate.getOrganization());
        } else {
            applicationUser.setOrganization(loggedUser.getOrganization());
        }

        return this.userRepository.save(userToUpdate);
    }

    public List<ApplicationUser> getUsersWithAllowedEmail() {
        return this.userRepository.findAllByAllowProfessionalEmail(true);
    }

    public Optional<ApplicationUser> uploadESign(Long id, MultipartFile multipartFile) {
        String uploadedFileUrl = null;

        Optional<ApplicationUser> user = this.userRepository.findById(id);

        if (user.get().getESign() != null) {
            amazonClient.deleteFromS3Bucket(user.get().getESign());
        }

        uploadedFileUrl = amazonClient.uploadFileTos3bucket(multipartFile);

        try {
            user.get().setESign(uploadedFileUrl);
            this.userRepository.save(user.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    private boolean checkPassword(ApplicationUser applicationUser, String password) {
        return passwordEncoder.matches(password, applicationUser.getPassword());
    }

}
