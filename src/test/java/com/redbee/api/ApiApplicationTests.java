package com.redbee.api;

import com.redbee.api.model.Comentario;
import com.redbee.api.service.ComentarioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.redbee")
@SpringBootTest
public class ApiApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(ApiApplicationTests.class);
	private static final int CONCURRENCY = 10;

	@Autowired
	ComentarioService comentarioService;

	@Test
	public void contextLoads() {
		List<Comentario> comments = new ArrayList<Comentario>();
		IntStream.rangeClosed(1, CONCURRENCY)
				.forEach(i -> comments.add(addComment(i)));

		Long initTime = System.nanoTime();
		comments.forEach(comment -> comentarioService.updateComment(comment));
		Long endTime = System.nanoTime();

		logger.info("Time finish: "+ (endTime-initTime));
	}

	public Comentario addComment(int i){
		Comentario comment = new Comentario();
		comment.setDate(new Date());
		try{
			Thread.sleep(10);
		}catch (Exception e){
			e.printStackTrace();
		}
		comment.setName("ramdom");
		comment.setComment("comment"+ i);
		return comment;
	}

}
