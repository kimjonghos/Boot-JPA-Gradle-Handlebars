package com.example.webservice.dto;

import com.example.webservice.domain.Comments;
import com.example.webservice.domain.Posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentsSaveRequestDto {

	private String author;
	private String pwd;
	private String content;
	private Posts posts;
	
	@Builder
	public CommentsSaveRequestDto(String author,String pwd,String content,Posts posts) {
		this.author=author;
		this.pwd=pwd;
		this.content=content;
		this.posts=posts;
	}
	
	public Comments toEntity() {
		return Comments.builder()
				.author(author)
				.content(content)
				.pwd(pwd)
				.posts(posts)
				.build();
	}
}
