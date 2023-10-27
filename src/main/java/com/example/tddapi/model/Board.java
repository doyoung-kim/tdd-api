package com.example.tddapi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "board")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int num;
	
	@Column
	private String title;
	
	@Column
	private String contents;

	@Column(name = "write_id")	
	private String writeId;
	
	@Column(name = "write_name")
	private String writeName;
	
	@Column(name = "write_date")
	private LocalDateTime writeDate;		
	
	@Column(name = "modify_id")
	private String modifyId;
	
	@Column(name = "modify_name")
	private String modifyName;

	@Column(name = "modify_date")
	private LocalDateTime  modifyDate;
}