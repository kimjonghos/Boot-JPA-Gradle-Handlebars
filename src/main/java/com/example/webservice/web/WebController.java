package com.example.webservice.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.webservice.dto.KakaoInfo;
import com.example.webservice.dto.PostsMainPageResponseDto;
import com.example.webservice.service.KakaoLoginUtil;
import com.example.webservice.service.PostsService;
import com.example.webservice.service.TestClass;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class WebController {

	private PostsService postsService;
	private KakaoLoginUtil kakaoLoginUtil;
	public static final int pageShowNum = 5;
	@GetMapping("/")
	public String main(Model model) {
		return "index";
	}
	@GetMapping("/kakaoSuccess")
	public ModelAndView kakaoSuccess(@RequestParam(required = false) String code,HttpServletRequest req) {
		RedirectView r=null;
		if(req.getHeader("referer")!=null) {
			r=new RedirectView(req.getHeader("referer"));
		}
		else {
			r=new RedirectView("/");
		}
		r.setExposeModelAttributes(false);
		ModelAndView m=new ModelAndView(r);
		if (code != null) {
			KakaoInfo kakaoInfo=kakaoLoginUtil.getKakaoInfo(code);
			if(kakaoInfo!=null) {
				System.out.println("KakaoInfo@@@@@@@@@@@@@@"+kakaoInfo);
				HttpSession session=req.getSession();
				session.setAttribute("kakaoid", kakaoInfo.getKakaoid());
				session.setAttribute("profile_image", kakaoInfo.getProfile_image());
				session.setAttribute("profile_nickname", kakaoInfo.getProfile_nickname());
				session.setAttribute("accessToken", kakaoInfo.getAccessToken());
			}
		}
		return new ModelAndView(r);
	}
	@GetMapping("/kakaoLogin")
	public String kakaoLogin() {
		System.out.println("come");
		return kakaoLoginUtil.getLoginPage();
	}

	@GetMapping("/board")
	public String board(Model model,HttpServletRequest req) {
		model.addAttribute("board","ok");
		HttpSession session=req.getSession();
		if(session.getAttribute("kakaoid")!=null) {
			model.addAttribute("kakaoid", session.getAttribute("kakaoid"));
			model.addAttribute("profile_image", session.getAttribute("profile_image"));
		}
		return "index";
	}
	
	// prev=-2 , next=-1
	@GetMapping("/board/{pageNum}")
	public String boardPage(HttpServletRequest req,Model model, @PathVariable String pageNum) {
		PostsMainPageResponseDto dto = postsService.findAllDesc(Integer.parseInt(pageNum), pageShowNum);
		System.out.println(dto);
		model.addAttribute("kakaoid",req.getSession().getAttribute("kakaoid"));
		model.addAttribute("posts", dto.getPosts());
		model.addAttribute("pageinfo", dto.getPageInfo());
		model.addAttribute("board","ok");
		return "index";

	}
	
}
