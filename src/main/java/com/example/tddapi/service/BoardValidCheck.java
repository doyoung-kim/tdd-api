package com.example.tddapi.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.tddapi.model.Board;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BoardValidCheck {

    public boolean checkList( List<Board> boardList) {
        log.info("==checkList2== boardList.size(): {}",boardList.size());
        if (boardList.size() >  0) {
            return true;
        }
        return false;

    }
    
}
