package com.example.webservice.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webservice.dto.PostDetailRequestDto;
import com.example.webservice.dto.PostsDetailResponseDto;
import com.example.webservice.dto.PostsSaveRequestDto;
import com.example.webservice.service.PostsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WebRestController {

	private PostsService postsService;
	
	@PostMapping("/posts")
    public void savePosts(@RequestBody PostsSaveRequestDto dto){
        postsService.save(dto);
    }
	@PostMapping("/detail")
	public PostsDetailResponseDto detailPost(@RequestBody PostDetailRequestDto dto) {
		return postsService.findOnePost(dto);
	}
//	@PostMapping("/detail")
//	public void detailPost(@RequestParam("pid") String pid) {
//		System.out.println(11);
//		postsService.findPost(Long.parseLong(pid));
//		System.out.println(22);
//	}
}
