package edu.kh.project.sse.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import edu.kh.project.member.dto.Member;
import edu.kh.project.sse.dto.Notification;
import edu.kh.project.sse.service.SseService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController // @Controller + @ResponseBody 비동기 통신만 하겠다()
@Slf4j 
public class SseController {
	
	@Autowired
	private SseService service;
	
  // SseEmitter : 서버로 부터 메시지를 전달 받을 클라이언트 정보를 저장한 객체 == 연결된 클라이언트

	// ConcurrentHashMap : 멀티스레드 환경에서 동기화를 보장하는 Map
	//	-> 한 번에 많은 요청이 있어도 차례대로 처리 (줄 세우는 맵, 연결된 클라이언트들의 대기명단으로도 볼 수있다)
	private final Map<String, SseEmitter> emitters 
		= new ConcurrentHashMap<>();
	
	@GetMapping("sse/connect")
	public SseEmitter sseConnect(
			@SessionAttribute("loginMember") Member loginMember) {
	
		//Map에 저장될 Key 값으로 회원번호 얻어오기
		String clientId = loginMember.getMemberNo() + "";
		
		// SseEmitter 객체 설정 -> 연결 대기시간 10분 설정(ms 단위)
		SseEmitter emitter =  new SseEmitter(10*60*1000L);
		
		// 클라이언트 정보를 Map에 추가
		emitters.put(clientId, emitter);
		
		// 클라이언트 연결 종료시 Map에서 제거
		emitter.onCompletion(()-> emitters.remove(clientId));
		
		// 클라이언트 타임 아웃 시 Map에서 제거 
		emitter.onTimeout(() -> emitters.remove(clientId));
		
		return emitter;
		
		
	}
	
	/** 알림 메세지 전송 */
	@PostMapping("sse/send")
	public void sendNotification(
			@RequestBody Notification notification,
			@SessionAttribute("loginMember")Member loginMember) {
		
			// 알림보낸회원(현재 로그인 한 회원) 번호 추가
			notification.setSendMemberNo(loginMember.getMemberNo());
			
	    // 전달 받은 알림 데이터를 DB에 저장하고
	    // 알림 받을 회원의 번호 
	    //  + 해당 회원이 읽지 않은 알림 개수 반환 받는 서비스 호출
			Map<String, Object> map
				= service.insertNotification(notification);
			
		
			// 알림을 받을 클라리언트 id == 알림 받을 회원 번호
		  String clientId =  map.get("receiveMemberNo").toString();
		  
	    // 연결된 클라이언트 대기 명단(emitters)에서
	    // clientId가 일치하는 클라이언트 찾기
	    SseEmitter emitter = emitters.get(clientId);
	    
	    // clientId가 일치하는 클라이언트가 있을 경우
	    if (emitter != null) {
					try {
						emitter.send( map );
					} catch (Exception e) {
						emitters.remove(clientId);
					}
			} 
	}
	
  // -------------------------------------------------

  /**
   * 로그인한 회원의 알림 목록 조회
   * @param loginMember
   * @return
   */
  @GetMapping("notification") // 비동기 요청 오면 List<Notification> 하나 return
  public List<Notification> selectNotificationList(
    @SessionAttribute("loginMember") Member loginMember
  ){
  	int memberNo = loginMember.getMemberNo();
  	return service.selectNotificationList(memberNo);
  }
	
	
}
