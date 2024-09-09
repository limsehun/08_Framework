package edu.kh.todolist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.todolist.dto.Todo;
import edu.kh.todolist.service.TodoListService;

@Controller // Controller임을 명시 + Bean 등록,  controller : 요청 응답 제어
public class MainController {
	
	@Autowired // 등록된 Bean중에서 같은 자료형 객체를 의존성 주입(DI) 
	private TodoListService service;
	
	@RequestMapping("/") // 최상위 주소 매핑(연결/GET, POST 가리지 않음)
	public String mainPage(Model model) {
		// model : 데이터 전달용 객체
		
		Map<String, Object> map = service.selectTodoList();
		
		// map에 담긴 값 꺼내놓기
		List<Todo> todoList = (List<Todo>)map.get("todoList");
		int completeCount = (int)map.get("completeCount");
		
		// 조회 결과 request scope에 추가
		model.addAttribute("todoList", todoList);
		model.addAttribute("completeCount", completeCount);
		
		// classpath:/templates/common/main.html  forward 
		return "common/main";
	}

	
	
	
}
