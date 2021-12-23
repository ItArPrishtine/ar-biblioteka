package com.akropoliprishtine.services;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.Permission;
import com.akropoliprishtine.entities.Role;
import com.akropoliprishtine.repositories.UserRepository;
import com.akropoliprishtine.utils.JwtTokenUtil;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    ApplicationUserService applicationUserService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = this.applicationUserService.loadUserByUsername(username);

        if (applicationUser != null) {
            return new User(applicationUser.getUsername(), applicationUser.getPassword(),
                    getAuthorities(applicationUser));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public ApplicationUser getUserFromToken() {
        String token =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization")
                        .split(" ")[1];

        Map<String, Object> tokenData = jwtTokenUtil.getAllClaimsFromToken(token);
        Long userId =  Long.parseLong(tokenData.get("id").toString());

        Optional<ApplicationUser> returnedUser = this.userRepository.findById(userId);
        return returnedUser.orElseGet(ApplicationUser::new);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(ApplicationUser user) {
        return getGrantedAuthorities(user);
    }

    private List<GrantedAuthority> getGrantedAuthorities(ApplicationUser user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        List<String> userPermissions = user.getRole().getPermissions().stream().map(
                Permission::getEndpoint
        ).collect(Collectors.toList());

        userPermissions.stream().forEach(
                permission -> authorities.add(new SimpleGrantedAuthority(permission))
        );

        return authorities;
    }
}
