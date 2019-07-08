package com.example.webservice.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.webservice.domain.Posts;
import com.example.webservice.domain.PostsRepository;
import com.example.webservice.dto.PostDetailRequestDto;
import com.example.webservice.dto.PostsDetailResponseDto;
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
	@Transactional
	public PostsDetailResponseDto findOnePost(PostDetailRequestDto dto) {
		Posts p=postsRepository.getOne(dto.getPid());
		return new PostsDetailResponseDto(p);
	}
}
