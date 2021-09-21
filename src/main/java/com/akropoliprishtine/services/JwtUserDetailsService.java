package com.akropoliprishtine.services;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.repositories.UserRepository;
import com.akropoliprishtine.utils.JwtTokenUtil;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    ApplicationUserService applicationUserService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = this.applicationUserService.loadUserByUsername(username);

        if (applicationUser != null) {
            return new User(applicationUser.getUsername(), applicationUser.getPassword(),
                    new ArrayList<>());
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
}
