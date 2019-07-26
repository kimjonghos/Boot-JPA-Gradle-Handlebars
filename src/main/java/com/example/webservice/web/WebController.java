package com.example.webservice.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.webservice.service.PostsService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class WebController {

	private PostsService postsService;
	
	@GetMapping("/")
	public String main(Model model) {
		
		return "index";
	}
	@GetMapping("/board")
	public String board(Model model) {
		System.out.println(postsService.findAllDesc().size());
		model.addAttribute("posts",postsService.findAllDesc());
		return "board";
	}
	
	
	
}
