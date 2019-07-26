package com.example.webservice.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.webservice.domain.CommentsRepository;
import com.example.webservice.domain.Posts;
import com.example.webservice.dto.CommentsDeleteRequestDto;
import com.example.webservice.dto.CommentsListResponseDto;
import com.example.webservice.dto.CommentsSaveRequestDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CommentsService {

	private CommentsRepository commentsRepository;
	
	@Transactional
	public Long save(CommentsSaveRequestDto dto) {
		return commentsRepository.save(dto.toEntity()).getId();
	}
	@Transactional
	public void deleteAll() {
		commentsRepository.deleteAll();
	}
	@Transactional
	public String deleteCommentsById(CommentsDeleteRequestDto dto) {
		String inputPwd=dto.getPwd();
		String targetPwd=commentsRepository.findById(dto.getId()).get().getPwd();
		if(inputPwd.equals(targetPwd)) {
			commentsRepository.deleteById(dto.getId());
			return "success";
		}
		else {
			return "false";
		}
	}
	@Transactional
	public List<CommentsListResponseDto> findByPostsId(Posts posts){
		return commentsRepository.findByPostsId(posts.getId())
				.map(CommentsListResponseDto::new)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public List<CommentsListResponseDto> findAllDesc(){
		return commentsRepository.findAllDesc()
				.map(CommentsListResponseDto::new)
				.collect(Collectors.toList());
	}
}
