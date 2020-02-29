package com.ouriens.voting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class VotingApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/vote/create/{id}/{topic}/{option1}/{option2}/{duration}").allowedOrigins("https://www.youtube.com");
				registry.addMapping("/vote/create/{id}/{topic}/{option1}/{option2}/{option3}/{duration}").allowedOrigins("https://www.youtube.com");
				registry.addMapping("/vote/incremental/{option1Vote}/{option2Vote}").allowedOrigins("https://www.youtube.com");
				registry.addMapping("/vote/incremental/{option1Vote}/{option2Vote}/{option3Vote}").allowedOrigins("https://www.youtube.com");
				registry.addMapping("/vote/result").allowedOrigins("https://www.youtube.com");
				registry.addMapping("/vote/pause").allowedOrigins("https://www.youtube.com");
				registry.addMapping("/vote/resume").allowedOrigins("https://www.youtube.com");
				registry.addMapping("/vote/start").allowedOrigins("https://www.youtube.com");
				registry.addMapping("/admin/vote/update/{option}/{voteNumber}").allowedOrigins("https://www.youtube.com");
				registry.addMapping("/admin/vote/updateAll/{option1}/{option2}").allowedOrigins("https://www.youtube.com");
				registry.addMapping("/admin/vote/updateAll/{option1}/{option2}/{option3}").allowedOrigins("https://www.youtube.com");
				registry.addMapping("/admin/vote/massive/{totalVote}/{option1Percentage}/{option2Percentage}").allowedOrigins("https://www.youtube.com");
				registry.addMapping("/admin/vote/massive/{totalVote}/{option1Percentage}/{option2Percentage}/{option3Percentage}").allowedOrigins("https://www.youtube.com");
			}
		};
	}
}
