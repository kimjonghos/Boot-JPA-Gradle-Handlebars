package com.example.webservice.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix="kauth")
@Getter
@Setter
public class TestClass {
	private String RestApiKey;
	private String Redirect_URI;
	public void talk() {
		System.out.println(RestApiKey);
		System.out.println(Redirect_URI);
	}
	
}
