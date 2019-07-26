package com.example.webservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.webservice.domain.Comments;
import com.example.webservice.domain.CommentsRepository;
import com.example.webservice.domain.Posts;
import com.example.webservice.domain.PostsRepository;
import com.example.webservice.dto.CommentsListResponseDto;
import com.example.webservice.dto.CommentsSaveRequestDto;
import com.example.webservice.dto.PostsSaveRequestDto;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" })
@SpringBootTest

public class CommentsServiceTest {
	@Autowired
	private CommentsService commentsService;

	@Autowired
	private CommentsRepository commentsRepository;
	
	@Autowired
	private PostsRepository postsRepository;

	@Autowired
	private PostsService postsService;
	@After
	public void cleanUp() {
		commentsRepository.deleteAllInBatch();
		postsRepository.deleteAllInBatch();
	}
	@Test 
	public void commentsFindByPostsId() {
		// given
				PostsSaveRequestDto dto = PostsSaveRequestDto.builder().author("jojoldu@gmail.com").content("테스트")
						.title("테스트 타이틀").build();

				// when
				postsRepository.save(dto.toEntity());

				// then
				Posts posts = postsRepository.findAll().get(0);
				
				
				CommentsSaveRequestDto dto2 = CommentsSaveRequestDto.builder()
						.author("testAuth")
						.content("테스트")
						.pwd("333")
						.posts(posts)
						.build();
				CommentsSaveRequestDto dto3 = CommentsSaveRequestDto.builder()
						.author("asdjasd")
						.content("testContent")
						.pwd("testPwd")
						.posts(posts)
						.build();

				// when
				commentsService.save(dto2);
				commentsService.save(dto3);
				
				// then
				List<CommentsListResponseDto> comments = commentsService.findByPostsId(posts);
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				System.out.println(comments.size());
				if(comments.size()>0) {
					for(CommentsListResponseDto cc:comments)
						System.out.println(cc);
				}
	}
	@Test
	public void dtoDataSaveCommentsTable() {
//		System.out.println("?@#@#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//		System.out.println(commentsRepository.findAll().size());
		// given
		PostsSaveRequestDto dto = PostsSaveRequestDto.builder().author("jojoldu@gmail.com").content("테스트")
				.title("테스트 타이틀").build();

		// when
		postsRepository.save(dto.toEntity());

		// then
		Posts posts = postsRepository.findAll().get(0);
		
		
		CommentsSaveRequestDto dto2 = CommentsSaveRequestDto.builder()
				.author("testAuth")
				.content("테스트")
				.pwd("333")
				.posts(posts)
				.build();

		// when
		commentsService.save(dto2);
		
		// then
		Comments comments = commentsRepository.findAll().get(0);
		assertThat(comments.getAuthor()).isEqualTo(dto2.getAuthor());
		assertThat(comments.getContent()).isEqualTo(dto2.getContent());
		assertThat(comments.getPwd()).isEqualTo(dto2.getPwd());
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(comments.getPosts().toString());
		System.out.println(comments.toString());
	}

}
