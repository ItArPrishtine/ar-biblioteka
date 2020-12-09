package com.akropoliprishtine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;


@SpringBootApplication
@EnableConfigurationProperties
@EntityScan({"com.akropoliprishtine.entities"})
public class AkropoliPrishtineApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AkropoliPrishtineApplication.class, args);
	}

	@Bean
	public PasswordEncoder encoder () {
		return new BCryptPasswordEncoder();
	}

}
