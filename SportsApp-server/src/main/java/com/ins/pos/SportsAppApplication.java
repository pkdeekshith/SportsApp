package com.ins.pos;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class SportsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsAppApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@PostConstruct
    public void init(){
      TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
    }
	
}
