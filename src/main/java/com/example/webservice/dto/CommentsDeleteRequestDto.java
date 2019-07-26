package com.example.webservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommentsDeleteRequestDto {

	private Long id;
	private String pwd;
	
	@Builder
	public CommentsDeleteRequestDto(Long id,String pwd) {
		this.id=id;
		this.pwd=pwd;
	}
	
}
