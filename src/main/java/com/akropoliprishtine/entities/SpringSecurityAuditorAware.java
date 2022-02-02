package com.akropoliprishtine.entities;

import com.akropoliprishtine.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<String> {

	@Autowired
	JwtUserDetailsService jwtUserDetailsService;
	
	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of(jwtUserDetailsService.getUserFromToken().getId().toString());
	}
}
