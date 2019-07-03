package com.example.webservice.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
