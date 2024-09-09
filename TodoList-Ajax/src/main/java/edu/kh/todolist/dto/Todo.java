package edu.kh.todolist.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Todo  {
	
	private int 		todoNo;
	private String 	todoTitle;
	private String 	todoDetail;
	private int 		todoComplete;
	private String 	regDate;
	
}
