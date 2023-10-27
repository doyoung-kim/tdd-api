package com.example.tddapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.tddapi.model.Board;

@Repository

public interface BoardRepository extends JpaRepository<Board, Integer>{


    @Modifying
	@Query(value="UPDATE board b set b.title = :#{#board.title}, b.contents = :#{#board.contents}, b.modify_id = :#{#board.modifyId}, b.modify_name = :#{#board.modifyName}, b.modify_date = now()  WHERE b.num = :#{#board.num}",  nativeQuery= true)
	Integer  updateBoard(@Param("board") Board board);

	@Query(value="SELECT b.* FROM board b WHERE b.title LIKE :search or b.contents LIKE :search order by num desc" ,  nativeQuery= true)
    List<Board> searchByStartsWith(String search);

}
