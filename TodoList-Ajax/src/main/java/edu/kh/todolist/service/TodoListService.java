package edu.kh.todolist.service;

import java.util.List;
import java.util.Map;

import edu.kh.todolist.dto.Todo;

public interface TodoListService {

	/**
	 * 할 일 목록 조회 + 완료된 할 일 개수 
	 * @return
	 */
	Map<String, Object> selectTodoList();

	
	/**
	 * 할 일 추가
	 * @param todo
	 * @return result
	 */
	int todoAdd(Todo todo);


	/**
	 * 할 일 추가
	 * @param todoNo
	 * @return
	 */
	Todo todoDetail(int todoNo);


	/**
	 * 할 일 변경
	 * @param todoNo
	 * @return
	 */
	int todoComplete(int todoNo);


	int todoUpdate(Todo todo);


	int todoDelete(int todoNo);


	String searchTitle(int todoNo);


	/** 전체 할 일 개수 조회
	 * @return totalCount
	 */
	int getTotalCount();

	/** 완료된 할 일 개수 조회
	 * @return completeCount
	 */
	int getCompleteCount();

	/** 할 일 전체 목록
	 * @return
	 */
	List<Todo> getTodoList();

	
	

}