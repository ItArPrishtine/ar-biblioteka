package com.akropoliprishtine.config;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.services.JwtUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JPAAuditConfig {

    private JwtUserDetailsService jwtUserDetailsService;

    @Bean
    public AuditorAware<Long> auditorProvider() {

        /*
          if you are using spring security, you can get the currently logged username with following code segment.
          SecurityContextHolder.getContext().getAuthentication().getName()
         */
//        ApplicationUser user = jwtUserDetailsService.getUserFromToken();
//        System.out.println("123");
        return () -> Optional.ofNullable(1L);
    }
}
