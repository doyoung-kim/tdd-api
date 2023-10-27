package com.example.tddapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.tddapi.model.Board;

import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// // 실DB 사용
@Slf4j
// @Rollback(false)
public class BoardRepositoryRealDBTest {
    @Autowired
    BoardRepository repository;

    @BeforeEach
    void setUp() {
        // saveBoardData();
    }

    @Test
    void testFindById() {
        int num = 5;
        Board board = repository.findById(num).orElse(new Board());
        log.info("*****[test]**** BoardRepositoryTest testFindById board : {}", board);
        assertEquals(num, board.getNum());
    }

    @Test
    void testSearchByStartsWith() {
        List<Board> boardList =  repository.searchByStartsWith("test%");
        assertTrue( boardList.size() > 0);
        log.info("*****[test]**** boardList : {}", boardList);
    }

    @Test
    void testInsert() {
        int i = 994;
        Board reqBoard = Board.builder()
                .title("test-" + i)
                .contents("testcontents-" + i)
                .writeId("user1")
                .writeName("테스터")
                .writeDate(LocalDateTime.now())
                .build();
        Board board = repository.save(reqBoard);
        assertTrue(board.getNum() > 0);
        Board queryBoard = repository.findById(board.getNum()).orElse(new Board());
        assertEquals(queryBoard.getNum(), board.getNum());
        log.info("*****[test]**** queryBoard : {}", queryBoard.toString());
    }

    @Test
    void testUpdate() {

        //  data insert

        int i = 991;
        Board reqBoard = Board.builder()
                .title("test-" + i)
                .contents("testcontents-" + i)
                .writeId("user1")
                .writeName("테스터")
                .writeDate(LocalDateTime.now())
                .build();
        Board insBoard = repository.saveAndFlush(reqBoard);        
        assertTrue(insBoard.getNum() > 0);
        log.info("*****[test]**** return insBoard  num : {}", insBoard.getNum());
        Board updateBoard = repository.findById(insBoard.getNum()).orElse(new Board());

        // given update
        updateBoard.setTitle("test-" + i+ "-updated");
        updateBoard.setContents("testcontents-" + i+ "-updated");
        updateBoard.setModifyId("user1");
        updateBoard.setModifyName("테스터");
        
        //when
        int result = repository.updateBoard(updateBoard);

        //then
        assertEquals(1,result);

        Board queryBoard = repository.findById(updateBoard.getNum()).orElse(new Board());
        log.info("*****[test]**** queryBoard : {}", queryBoard.toString());
    }

    private void saveBoardData() {

        for (int i = 1; i < 11; i++) {
            Board board = Board.builder()
                    .title("test-" + i)
                    .contents("testcontents-" + i)
                    .writeId("user1")
                    .writeName("유저1")
                    .writeDate(LocalDateTime.now())
                    .build();
            repository.save(board);
        }

    }

}
