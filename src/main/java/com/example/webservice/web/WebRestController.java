package com.example.webservice.web;

import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webservice.auth.KakaoOAuth;
import com.example.webservice.dto.CommentsDeleteRequestDto;
import com.example.webservice.dto.CommentsSaveRequestDto;
import com.example.webservice.dto.PostDetailRequestDto;
import com.example.webservice.dto.PostsDetailResponseDto;
import com.example.webservice.dto.PostsSaveRequestDto;
import com.example.webservice.service.CommentsService;
import com.example.webservice.service.KakaoLoginUtil;
import com.example.webservice.service.PostsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WebRestController {

	private PostsService postsService;
	private Environment env;
	private CommentsService commentsService;
	private KakaoLoginUtil kakaoLoginUtil;
	@PostMapping("/posts")
    public void savePosts(@RequestBody PostsSaveRequestDto dto){
        postsService.save(dto);
    }
	@PostMapping("/detail")
	public PostsDetailResponseDto detailPost(@RequestBody PostDetailRequestDto dto) {
		PostsDetailResponseDto re=postsService.findOnePost(dto);
		System.out.println(re);
		return re;
	}
	
	@PostMapping("/comments")
	public void saveComments(@RequestBody CommentsSaveRequestDto dto) {
		commentsService.save(dto);
		
	}
	@PostMapping("/comments/delete")
	public String deleteComments(@RequestBody CommentsDeleteRequestDto dto) {
		return commentsService.deleteCommentsById(dto);
	}
	@GetMapping("/profile")
	public String getProfile() {
		return Arrays.stream(env.getActiveProfiles())
				.findFirst()
				.orElse("");
	}
	@GetMapping("/logout")
	public void logout(HttpServletRequest req) {
		
		System.out.println("%%%%%%%%%%%%%%%%"+req.getSession().getAttribute("accessToken"));
		kakaoLoginUtil.kakaoLogOut((String)req.getSession().getAttribute("accessToken"));
		req.getSession().invalidate();
	}
}
