package com.example.tddapi.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tddapi.model.Board;
import com.example.tddapi.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/boards")
@Slf4j
@RequiredArgsConstructor
public class BoardController {
    private final  BoardService service;

    @GetMapping()
	public  List<Board>  list(@RequestParam(value="size", defaultValue = "10")  int size , @RequestParam(value="page", defaultValue = "1") int page) throws Exception {
        PageRequest pageRequest = PageRequest.of((page - 1), size, Sort.Direction.DESC, "num");
		log.info("====controller=======");
		List<Board> boardList = service.list(pageRequest);
		
		return boardList;
    }
}
