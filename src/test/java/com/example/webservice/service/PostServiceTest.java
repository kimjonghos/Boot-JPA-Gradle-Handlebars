package com.example.webservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.webservice.domain.Posts;
import com.example.webservice.domain.PostsRepository;
import com.example.webservice.dto.PostDetailRequestDto;
import com.example.webservice.dto.PostsDetailResponseDto;
import com.example.webservice.dto.PostsSaveRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("application") // Like this
public class PostServiceTest {

	@Autowired
	private PostsService postsService;

	@Autowired
	private PostsRepository postsRepository;

	@After
	public void cleanUp() {
		postsRepository.deleteAll();
	}

	@Test
	public void printPosts() {

//		List<PostsMainResponseDto> list=postsService.findAllDesc();
//		PostsMainResponseDto po1=list.get(0);
//		PostsMainResponseDto po2=list.get(1);
//		assertThat(po2.getTitle()).isEqualTo("테스트1");
//		assertThat(po1.getTitle()).isEqualTo("테스트2");

	}

	@Test
	public void dtoDataSavePostsTable() {
		// given
		PostsSaveRequestDto dto = PostsSaveRequestDto.builder().author("jojoldu@gmail.com").content("테스트")
				.title("테스트 타이틀").build();

		// when
		postsService.save(dto);

		// then
		Posts posts = postsRepository.findAll().get(0);
		assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
		assertThat(posts.getContent()).isEqualTo(dto.getContent());
		assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
	}

//	@Test
//	public void loadOnePostDetail() {
//
//		// given
//		PostsSaveRequestDto dto = PostsSaveRequestDto.builder().
//				author("jojoldu@gmail.com").
//				content("테스트")
//				.title("테스트 타이틀").build();
//
//		// when
//		postsService.save(dto);
//		PostDetailRequestDto d=new PostDetailRequestDto("1");
//		PostsDetailResponseDto p = postsService.findOnePost(d);
//		System.out.println(p.getTitle());
//		assertThat(p.getId()).isEqualTo((long) 1);
//	}

}
