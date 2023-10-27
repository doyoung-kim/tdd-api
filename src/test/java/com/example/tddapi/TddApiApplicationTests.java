package com.example.tddapi;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import com.example.tddapi.controller.BoardController;
import com.example.tddapi.model.Board;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ActiveProfiles("dev")
@Slf4j
class TddApiApplicationTests {

	@Autowired 
	private BoardController controller;

	@Test
	@DisplayName("게시판 리스트 테스트")
	void list() throws Exception {
		List<Board> boardList = controller.list(10, 1);
		log.info("*****[test]**** boardList : {}", boardList.toString());
		assertTrue( boardList.size() == 10);
	}

}
