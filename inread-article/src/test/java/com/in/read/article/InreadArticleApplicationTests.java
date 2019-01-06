package com.in.read.article;

import com.in.read.article.service.NoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InreadArticleApplicationTests {

	@Autowired
	private NoteService noteService;

	@Test
	public void contextLoads() {
		noteService.count();
	}

}

