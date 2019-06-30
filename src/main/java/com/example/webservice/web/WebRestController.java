package com.example.webservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webservice.domain.PostsRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WebRestController {

	private PostsRepository postsRepository;
	@GetMapping
	public String hello() {
		return "hello";
	}
	@PostMapping("/posts")
    public void savePosts(@RequestBody PostsSaveRequestDto dto){
        postsRepository.save(dto.toEntity());
    }
}
