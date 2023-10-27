package com.example.tddapi.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;

import com.example.tddapi.data.MockData;
import com.example.tddapi.model.Board;
import com.example.tddapi.service.BoardService;

@WebMvcTest(BoardController.class)
public class BoardControllerTest {
    @Autowired 
    private MockMvc mvc;
    
    @MockBean // 컨트롤러에서 사용하는 서비스가 등록되지 않았기 때문에 @MockBean을 이용하여 의존성 대체
    private BoardService boardService;

    @Test
    void testController() throws Exception{
        // given
        int page=1;
        int size=10;
        PageRequest pageRequest = PageRequest.of((page - 1), size, Sort.Direction.DESC, "num");
		
        given(boardService.list(pageRequest)).willReturn(MockData.getBoardList());

        // when
        this.mvc.perform(get("/api/boards"))       
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andDo(print());
        reset(boardService);
       
    }

     
    
}
