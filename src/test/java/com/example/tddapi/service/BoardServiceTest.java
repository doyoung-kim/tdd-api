package com.example.tddapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.tddapi.data.MockData;
import com.example.tddapi.model.Board;
import com.example.tddapi.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
public class BoardServiceTest {
    @InjectMocks
    private BoardService service;

    @Mock
    private BoardRepository repository;
    @Mock
    private BoardValidCheck check;

    @Test
    @Order(4)
    void testView(){
        // given
        int num=5;
        given(repository.findById(num)).willReturn( MockData.getOptionalBoard(num));

        
        //when 
        Board board = service.view(num);      

        // then
        assertEquals(num, board.getNum());

        log.info("*****[test]**** testview board : {}", board);
    }

    @Test
    @Order(1)
    void testList_성공() throws Exception{
        // given 
        int page = 1;
        int size= 5;
        List<Board> mockDataList =  MockData.getBoardList();
        PageRequest pageRequest = PageRequest.of((page - 1), size, Sort.Direction.DESC, "num");
        Page<Board> pageList = new PageImpl<Board>(mockDataList);
        given(repository.findAll(pageRequest)).willReturn(pageList);
        given(check.checkList(mockDataList)).willReturn(true);

        // when
        List<Board> boardList = service.list(pageRequest);
        log.info("*****[test]**** testList boardList : {}", boardList);
        // then
        assertTrue(boardList.size() == 10  );
        reset(check);
    
    }
    @Test
    @Order(2)
    void testList_체크_게시판리스트() throws Exception{
        // given 
        int page = 1;
        int size= 5;
        List<Board> mockDataList =  MockData.getBoardList();
       
        PageRequest pageRequest = PageRequest.of((page - 1), size, Sort.Direction.DESC, "num");
        Page<Board> pageList = new PageImpl<Board>(mockDataList);

        given(repository.findAll(pageRequest)).willReturn(pageList);
        given(check.checkList(mockDataList)).willReturn(false);

        // when
        List<Board> boardList = service.list(pageRequest);
        // then
        assertTrue(boardList.size()== 1 );
        log.info("*****[test]**** testList boardList : {}", boardList);
        reset(check);
    }


    @Test
    @Order(3)
    void testList_에러발생시() {
        // given         
        PageRequest pageRequest = PageRequest.ofSize(1);
       
        given(repository.findAll(pageRequest)).willThrow(new RuntimeException("runtime error"));
        
        // when
        Exception exception = assertThrows(Exception.class, ()-> service.list(pageRequest));
       
        // then
        String message = exception.getMessage();
        assertEquals("msg: runtime error", message);
        log.info("*****[test]**** testList message : {}", message);
    }

 

    

}
