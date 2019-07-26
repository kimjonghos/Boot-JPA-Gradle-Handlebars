package com.example.webservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.webservice.domain.CommentsRepository;
import com.example.webservice.domain.Posts;
import com.example.webservice.domain.PostsRepository;
import com.example.webservice.dto.CommentsListResponseDto;
import com.example.webservice.dto.CommentsSaveRequestDto;
import com.example.webservice.dto.PostDetailRequestDto;
import com.example.webservice.dto.PostsDetailResponseDto;
import com.example.webservice.dto.PostsSaveRequestDto;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" })
@SpringBootTest

public class PostServiceTest {

	@Autowired
	private PostsService postsService;

	@Autowired
	private PostsRepository postsRepository;
	@Autowired
	private CommentsRepository commentsRepository;
	@Autowired
	private CommentsService commentsService;
	
	@After
	public void cleanUp() {
		commentsRepository.deleteAllInBatch();
		postsRepository.deleteAllInBatch();
	}


	@Test
	public void postsDetailTest() {
		// given
		PostsSaveRequestDto dto = PostsSaveRequestDto.builder().author("jojoldu@gmail.com").content("테스트")
				.title("테스트 타이틀").build();
		postsRepository.save(dto.toEntity());
		
		Posts posts = postsRepository.findAll().get(0);
		
		System.out.println("################");
		System.out.println(posts);
		CommentsSaveRequestDto dto2 = CommentsSaveRequestDto.builder()
				.author("a1")
				.content("c1")
				.pwd("p1")
				.posts(posts)
				.build();
		CommentsSaveRequestDto dto3 = CommentsSaveRequestDto.builder()
				.author("a2")
				.content("t2")
				.pwd("p2")
				.posts(posts)
				.build();
		// when
		commentsService.save(dto2);
		commentsService.save(dto3);
		
		PostDetailRequestDto pd=PostDetailRequestDto.builder()
				.pid(posts.getId())
				.build();
		
		PostsDetailResponseDto pdr=postsService.findOnePost(pd);
		for(CommentsListResponseDto as:pdr.getComments()) {
			System.out.println(as);
		}
		assertThat(pdr.getComments().get(0).getAuthor().equals(dto2.getAuthor()));
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(pdr.getComments().size());
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

