package com.example.webservice.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.webservice.domain.Posts;
import com.example.webservice.domain.PostsRepository;
import com.example.webservice.dto.CommentsListResponseDto;
import com.example.webservice.dto.PageInfoResponseDto;
import com.example.webservice.dto.PostDetailRequestDto;
import com.example.webservice.dto.PostsDetailResponseDto;
import com.example.webservice.dto.PostsMainPageResponseDto;
import com.example.webservice.dto.PostsMainResponseDto;
import com.example.webservice.dto.PostsSaveRequestDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostsService {

	private PostsRepository postsRepository;
	private CommentsService commentsService;
	
	@Transactional
	public Long save(PostsSaveRequestDto dto) {
		return postsRepository.save(dto.toEntity()).getId();
	}
	
	@Transactional
	public void deleteById(PostDetailRequestDto dto) {
		postsRepository.deleteById(dto.getPid());
	}
	@Transactional
	public PostsMainPageResponseDto findAllDesc(int page,int showNum){
		PageRequest p=PageRequest.of(page-1,showNum,new Sort(Direction.DESC,"id"));
		long totalPosts=postsRepository.count();
		PageInfoResponseDto pageinfo=PageInfoResponseDto.builder()
						.totalCount(totalPosts)
						.pageNum(page)
						.showNum(showNum)
						.build();
		List<PostsMainResponseDto> list=postsRepository.findAllDesc(p)
				.map(PostsMainResponseDto::new)
				.collect(Collectors.toList());
		return PostsMainPageResponseDto.builder().pageInfo(pageinfo)
				.posts(list).build();
	}
	@Transactional
	public void deleteAll() {
		postsRepository.deleteAll();
	}
	@Transactional
	public PostsDetailResponseDto findOnePost(PostDetailRequestDto dto) {
		Posts p=postsRepository.getOne(dto.getPid());
		List<CommentsListResponseDto> c=commentsService.findByPostsId(p);
		return new PostsDetailResponseDto(p,c);
	}
}
