package com.example.webservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class BootTestApplication {
	
	public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "/home/ec2-user/app/config/bootTest/real-application.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(BootTestApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

}
