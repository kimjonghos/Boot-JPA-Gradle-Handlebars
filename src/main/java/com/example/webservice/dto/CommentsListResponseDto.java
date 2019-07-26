package com.example.webservice.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.example.webservice.domain.Comments;
import com.example.webservice.domain.Posts;

import lombok.Getter;
import lombok.ToString;
@ToString
@Getter
public class CommentsListResponseDto {

	private Long id;
	private String author;
	private String pwd;
	private String content;
	private String modifiedDate;
	private Posts posts;
	public CommentsListResponseDto(Comments entity) {
		this.id=entity.getId();
		this.author=entity.getAuthor();
		this.pwd=entity.getPwd();
		this.content=entity.getContent();
		this.posts=entity.getPosts();
		this.modifiedDate=this.toStringDateTime(entity.getModifiedDate());
	}
	public String toStringDateTime(LocalDateTime localDateTime) {
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return Optional.ofNullable(localDateTime)
				.map(formatter::format)
				.orElse("");
	}
}
