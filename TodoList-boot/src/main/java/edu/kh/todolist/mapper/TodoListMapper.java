package edu.kh.todolist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.todolist.dto.Todo;

@Mapper // 상속 받은 클래스 생성 후 Bean 등록
public interface TodoListMapper {

	/** 할 일 목록 조회
	 * @return todoList
	 */
	List<Todo> selectTodoList();

	/** 완료된 할 일 개수 조회
	 * @return completeCount
	 */
	int selectCompleteCount();

	
	/**
	 * 할 일 추가
	 * @param todo
	 * @return
	 */
	int todoAdd(Todo todo);

	/**
	 * 할 일 상세 조회
	 * @param todoNo
	 * @return
	 */
	Todo todoDetail(int todoNo);

	/**
	 * 
	 * @param todoNo
	 * @return
	 */
	int todoComplete(int todoNo);

	int todoUpdate(Todo todo);

	int todoDelete(int todoNo);

	

	
	
	

}
