package com.example.webservice.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.example.webservice.domain.Posts;

import lombok.Getter;
import lombok.ToString;
@ToString
@Getter
public class PostsMainResponseDto {

	private Long id;
	private String title;
	private String author;
	private String modifiedDate;
	
	public PostsMainResponseDto(Posts entity) {
		id=entity.getId();
		title=entity.getTitle();
		author=entity.getAuthor();
		modifiedDate=toStringDateTime(entity.getModifiedDate());
	}
	public String toStringDateTime(LocalDateTime localDateTime) {
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return Optional.ofNullable(localDateTime)
				.map(formatter::format)
				.orElse("");
	}
}
