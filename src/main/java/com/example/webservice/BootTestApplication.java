package com.example.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class BootTestApplication {
	
	public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
			//+"..\\..\\config\\bootTest\\real-application.yml";
			//+"D:\\my\\STUDY\\MY_WORKS\\config\\bootTest\\real-application.yml";
            + "/home/ec2-user/app/config/bootTest/real-application.yml"; //linux
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(BootTestApplication.class)
			.properties(APPLICATION_LOCATIONS)
			.run(args);
		//SpringApplication.run(BootTestApplication.class, args);
	}

}
