package com.akropoliprishtine;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.io.IOException;

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
@EntityScan({"com.akropoliprishtine.entities"})
public class AkropoliPrishtineApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AkropoliPrishtineApplication.class, args);
	}

	@Bean
	public PasswordEncoder encoder () {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AmazonS3 amazonS3Client(AWSCredentialsProvider credentialsProvider) {
		return AmazonS3ClientBuilder
				.standard()
				.withCredentials(credentialsProvider)
				.withRegion("eu-central-1")
				.build();
	}
}
