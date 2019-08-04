package com.example.webservice.web;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=RANDOM_PORT)
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" })
public class WebControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
//	@Test
//	public void loading_main_page() {
//		//when
//		String body=this.restTemplate.getForObject("/board",String.class);
//		
//		//then
//		assertThat(body).contains("!!호롤룰루호롤룰루호롤룰루!!");
//	}
}
