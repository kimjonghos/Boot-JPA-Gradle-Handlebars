package com.example.webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.webservice.auth.KakaoOAuth;
import com.example.webservice.dto.KakaoInfo;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class KakaoLoginUtil {
	
	@Autowired
	private KakaoOAuth kauth;
	public void talk() {
		kauth.talk();
	}
	public String getLoginPage() {
		return kauth.getLoginPage();
	}
	public void kakaoLogOut(String code) {
		kauth.KakaoLogOut(code);
	}
	public KakaoInfo getKakaoInfo(String code) {
		JsonNode jsonToken = kauth.getAccessToken(code);
		System.out.println("$$$$$ jsonToken : " + jsonToken);
		JsonNode userInfo = kauth.getKakaoUserInfo(jsonToken.get("access_token").asText());
		KakaoInfo kakaoInfo=null;
		JsonNode properties = userInfo.path("properties");
		System.out.println(userInfo.toString());
		if (properties.isMissingNode()) {
			// if "name" node is missing
			System.out.println("@@@@@@@@@@@@@@name is missing");

		} else {
			kakaoInfo=KakaoInfo.builder().profile_image(properties.path("profile_image").asText())
			.profile_nickname(properties.path("nickname").asText())
			.kakaoid(userInfo.path("id").asText())
			.accessToken(jsonToken.get("access_token").asText()).build();
		}
		return kakaoInfo;
	}
}
