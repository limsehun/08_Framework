package edu.kh.project.sse.service;

import java.util.List;
import java.util.Map;

import edu.kh.project.sse.dto.Notification;

public interface SseService {

  /**알림 삽입 후 알림 받을 회원 번호 + 알림 개수 반환
   */
  Map<String, Object> insertNotification(Notification notification);

  /**
   * 
   * @param memberNo
   * @return
   */
	List<Notification> selectNotificationList(int memberNo);

	/**
	 * 
	 * @param memberNo
	 * @return
	 */
	int notReadCheck(int memberNo);

	/**
	 * 알림삭제
	 * @param notificationNo
	 */
	void deleteNotification(int notificationNo);

	/**
	 * 
	 * @param notificationNo
	 */
	void updateNotification(int notificationNo);
  
}
