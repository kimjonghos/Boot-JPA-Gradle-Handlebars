package com.example.webservice.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
public class PostsMainPageResponseDto {
	
	private List<PostsMainResponseDto> posts;
	private PageInfoResponseDto pageInfo;
	
	@Builder
	public PostsMainPageResponseDto(List<PostsMainResponseDto> posts,
										PageInfoResponseDto pageInfo) {
		this.posts=posts;
		this.pageInfo=pageInfo;
	}
}
