package com.example.tddapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.tddapi.model.Board;
import com.example.tddapi.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository repository;
    private final BoardValidCheck check;

    public Board view(int num) {
        Board board = repository.findById(num).orElse(new Board());
        log.info("========= BoardService view board : {}", board);
        return board;
    }

    public List<Board> list(PageRequest pageRequest) throws Exception {
        Page<Board> pageList = null;
        try {
            pageList = repository.findAll(pageRequest);
        } catch (Exception e) {
            throw new Exception("msg: " + e.getMessage());
        }

        List<Board> boardList = pageList.getContent();
        log.info("========= boardList : {}", boardList);

        boolean isDatalist = check.checkList(boardList);

        if (isDatalist == false) {
            log.info("========= boardList 데이터 업음");
            List<Board> noDataBoardList = new ArrayList<Board>();
            noDataBoardList.add(Board.builder().num(2000).title("no data").contents("no data contents").build());
            return noDataBoardList;
        }
        return boardList;
       
    }
    public boolean checkList2( List<Board> boardList) {
        log.info("==checkList2== boardList.size(): {}",boardList.size());
        if (boardList.size() >  0) {
            return true;
        }
        return false;

    }

   

}
