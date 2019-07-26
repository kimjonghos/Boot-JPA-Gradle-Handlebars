package com.example.webservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDetailRequestDto {

	private Long pid;
	
	@Builder
	public PostDetailRequestDto(Long pid) {
		this.pid=pid;
	}
	
}
