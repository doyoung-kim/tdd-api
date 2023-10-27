package com.example.tddapi.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.tddapi.model.Board;

public class MockData {

    public static List<Board> getBoardList(){
        List<Board> boardList = new ArrayList<>();
        for(int i=0; i < 10; i++){
            Board board =  Board.builder()
                        .num(i+1)
                        .title("test-"+i)
                        .contents("testcontents-"+i)
                        .writeId("user1")
                        .writeName("테스터")
                        .writeDate(LocalDateTime.now())
                        .build();
            boardList.add(board);
        }

        return boardList;
    }

    public static Board getBoard(int num) {
      return Board.builder()
            .num(num)
            .title("test-"+num)
            .contents("testcontents-"+num)
            .writeId("user1")
            .writeName("테스터")
            .build();

    }

    public static Optional<Board> getOptionalBoard(int num) {
        return Optional.ofNullable(getBoard(num));
    }
    
}
