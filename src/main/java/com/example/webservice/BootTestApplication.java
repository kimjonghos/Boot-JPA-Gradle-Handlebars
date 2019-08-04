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
            + "classpath:application.yml,"
			//+"..\\..\\config\\bootTest\\real-db.yml"
            //+",kauth.yml";// db 정보는 노출되면 안되기 때문에 외부에 파일을 생성해서 읽어옴
	
            + "/home/ec2-user/app/config/bootTest/real-db.yml"
            +",kauth.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(BootTestApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
        
    }

}
