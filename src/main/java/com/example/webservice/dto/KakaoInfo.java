package com.example.webservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
@ToString
@Getter
public class KakaoInfo {

	private String profile_nickname;
	private String profile_image;
	private String kakaoid;
	private String accessToken;
	@Builder
	public KakaoInfo(String profile_nickname,String profile_image,String kakaoid,String accessToken) {
		this.profile_image=profile_image;
		this.profile_nickname=profile_nickname;
		this.kakaoid=kakaoid;
		this.accessToken=accessToken;
	}
}
