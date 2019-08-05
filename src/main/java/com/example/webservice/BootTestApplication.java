package com.example.webservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class BootTestApplication {
	@Value("${restapikey}")
	private static String temp;
	public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml"
//			+",file:..\\..\\config\\bootTest\\real-db.yml"
//			+",file:..\\..\\config\\bootTest\\kauth.yml";
            + ",file:/home/ec2-user/app/config/bootTest/real-db.yml"
            +",file:/home/ec2-user/app/config/bootTest/kauth.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(BootTestApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
        
    }

}
