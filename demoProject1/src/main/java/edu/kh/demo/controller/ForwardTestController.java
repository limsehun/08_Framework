package edu.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Controller : 어떤 요청을 받고, 어떻게 응답할지 제어하는 역할
// - 어떤 요청을 처리할지 주소 매핑
// - 요청 처리된 결과에 따라 응답하는 방법 제어
//   (forward, redirect, 값 등)


// * instance : 개발자가 new를 이용해 만든 객체
// * bean     : Spring(Java)이 만든 객체

// @Controller 어노테이션
// 1) Controller 임을 명시
// 2) 클래스 작성된 내용대로 구현 
//    -> 스프링이 객체 생성 == Bean 등록 / Bean 생성

@Controller
public class ForwardTestController {

	// 기존 Servlet
	// - @WebServlet("요청주소") -> 클래스명 위에 작성
	// == 해당 클래스를 "요청주소"를 매핑해서 처리하는 클래스
	// -> 클래스별로 요청주소 1개만 처리 가능
	
	/* * Spring Controller *
	 * 
	 * - @RequestMapping("요청주소") -> 클래스명, 메서드명 위에 작성
	 * 	또는 @GetMapping("요청주소") -> 메서드명 위에 작성
	 * 	또는 @PostMapping("요청주소") -> 메서드명 위에 작성
	 * 
	 * 
	 * @RequestMapping("요청주소")의 뜻
	 *  - 요청 주소를 처리할 클래스/메서드를 매핑하는 어노테이션
	 *  
	 * 1) 클래스 위에 작성하는 경우
	 * - 공통 주소를 해당 클래스에 매핑
	 *  ex) /todo/select, /todo/insert, /todo/update.....
	 *  
	 *  -> @RequestMapping("todo") // todo로 시작하는 요청
	 * 
	 * 2) 메서드 위에 작성하는 경우
	 * - "요청주소"로 요청을 받은 경우
	 * 		해당 메서드 처리
	 * 
	 */
	
	// Controller  메서드 작성방법
	
	// 1) 접근 제한자는 무조건 public
	
	// 2) 반환형은 대부분 String
	// 		(ModelAndView 또는 Ajex 사용시 달라질 수 있음)
	
	// - 왜 String? 
	//  -> Controller 메서드에서 반환되는 문자열이
	// 		forward할 html 파일의 경로가 되기 때문
	
	// 3) 메서드 명은 의미있게 마응대로 작성
	
	// **4** 매개변수는 필요한 만큼 마음대로
	// -> Arguments Resolver 참조
	
	// 5) 매핑할 때 주소를 @RequestMapping 등을 이용해 작성
	
	/* Sspring Boot Controller에서
	 * 특수한 경우를 제외하고
	 * 매핑주소 제일 앞에 "/"를 적지 않는다.
	 */
	@RequestMapping("forward") //@RequestMapping()어노테이션
	public String forwardTest() {
		System.out.println("/forward 매핑됐는지 확인");
		
		/* thymleaf : 템플릿 엔진(JSP 대신 사용)
		 * 
		 * - Thymleaf사용시 접두사, 접ㅁ니사가 제공됨
		 * 
		 * - 접두사(prefix) : classpath:/templates/
		 * - 접미사(suffix) : .html
		 * 
		 * * Controller 메서드에서 반환되는 문자열에
		 *  앞, 뒤에 접두사, 접미사가 붙어서
		 *  forward할 html 파일의 경로 형태가 된다!!
		 * */
		
		// classpath:/templates/forward.html
		return "forward";
	}
	
	
	
}



