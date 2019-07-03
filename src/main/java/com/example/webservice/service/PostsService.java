package com.example.webservice.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.webservice.domain.PostsRepository;
import com.example.webservice.dto.PostsMainResponseDto;
import com.example.webservice.dto.PostsSaveRequestDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostsService {

	private PostsRepository postsRepository;
	
	@Transactional
	public Long save(PostsSaveRequestDto dto) {
		return postsRepository.save(dto.toEntity()).getId();
	}
	
	@Transactional
	public List<PostsMainResponseDto> findAllDesc(){
		return postsRepository.findAllDesc()
				.map(PostsMainResponseDto::new)
				.collect(Collectors.toList());
	}
}
