package com.example;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=App.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")	// 0 を指定すると空いてるポートを利用する
public class AppTest {

	@Value("${local.server.port}")
	int port;
	RestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void testHome() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port, String.class);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		assertThat(response.getBody(), is("Hello, Spring Boot!"));
	}
}
