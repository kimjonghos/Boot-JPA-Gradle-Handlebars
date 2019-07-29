package com.example.webservice.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.webservice.dto.PostsMainPageResponseDto;
import com.example.webservice.service.PostsService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class WebController {

	private PostsService postsService;
	public static final int pageShowNum=5;
	@GetMapping("/")
	public String main(Model model) {
		
		return "index";
	}
	@GetMapping("/board")
	public String board(Model model) {
		return "forward:/board/1";
	}
	//prev=-2 , next=-1
	@GetMapping("/board/{pageNum}")
	public String boardPage(Model model,@PathVariable String pageNum) {
		PostsMainPageResponseDto dto=
				postsService.findAllDesc(Integer.parseInt(pageNum),pageShowNum);
		System.out.println(dto);
		model.addAttribute("posts",dto.getPosts());
		model.addAttribute("pageinfo",dto.getPageInfo());
		return "board";
	}
	
	
	
}
