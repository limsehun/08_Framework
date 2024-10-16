package edu.kh.project.error.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;


@Controller // 컨트롤러 명시 + Bean 등록
public class CommonErrorController implements ErrorController{

	// ErrorController 인터페이스를 상속받은 경우 기존 Spring에서 
	// 에러를 처리하던 코드(에러 출력 페이지 forward)를 대체하여 동작함
	
	//[동작순서]
	
	// @ControllerAdvice에서 일하는 예외처리 메서드 찾기
	// -> 없으면 ErrorController 구현 객체가 처리
	
	/**
	 * 공용 예외 처리 메서드
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("error")
	public String errorHandler(Model model, HttpServletRequest req) {
		
		// 응답 상태 코드 얻어오기
		Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		int statusCode = Integer.parseInt(status.toString());
		
		// 에러 메세지 얻어오기
		Object message = req.getAttribute(RequestDispatcher.ERROR_MESSAGE);
		String errorMessage = (message != null) ? message.toString() : "알 수 없는 오류 발생";
		
		model.addAttribute("errorMessage", errorMessage);
		model.addAttribute("statusCode", statusCode);
		
		return "error/common-error";
	}
	
	
	@GetMapping("map-test")
	public String mapTest() {
		return "maptest/mapTest";
	}
	
	
}
