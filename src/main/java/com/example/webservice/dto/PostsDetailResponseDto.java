package com.example.webservice.dto;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.example.webservice.domain.Posts;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PostsDetailResponseDto {
	private Long id;
	private String title;
	private String author;
	private String modifiedDate;
	private String content;
	private List<CommentsListResponseDto> comments;
	
	public PostsDetailResponseDto(Posts entity,List<CommentsListResponseDto> comments) {
		this.id=entity.getId();
		this.title=entity.getTitle();
		this.author=entity.getAuthor();
		this.modifiedDate=toStringDateTime(entity.getModifiedDate());
		this.content=entity.getContent();
		this.comments=comments;
	}
	public String toStringDateTime(LocalDateTime localDateTime) {
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return Optional.ofNullable(localDateTime)
				.map(formatter::format)
				.orElse("");
	}
}
