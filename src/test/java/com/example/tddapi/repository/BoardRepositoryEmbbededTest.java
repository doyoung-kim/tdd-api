package com.example.tddapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.tddapi.model.Board;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@ActiveProfiles("local")
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// // 실DB 사용
@Slf4j
public class BoardRepositoryEmbbededTest {
    @Autowired
    BoardRepository repository;

    @BeforeEach
    void setUp() {
        saveBoardData();
    }

    @Test
    void testFindById() {
        int num = 4;
        Board board = repository.findById(num).orElse(new Board());
        log.info("*****[test]**** BoardRepositoryTest testFindById board : {}", board);
        assertEquals(num, board.getNum());
    }

    @Test
    @Transactional
    void testInsert(){
        int i = 994;
        Board reqBoard =  Board.builder()           
            .title("test-"+i)
            .contents("testcontents-"+i)
            .writeId("user1")
            .writeName("테스터")
            .writeDate(LocalDateTime.now())
            .build();
       Board board =  repository.save(reqBoard);
       assertTrue(board.getNum() > 0);
       Board queryBoard = repository.findById(board.getNum()).orElse(new Board());
       assertEquals(queryBoard.getNum(),board.getNum());
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
