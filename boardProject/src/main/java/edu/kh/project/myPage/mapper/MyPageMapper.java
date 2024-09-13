package edu.kh.project.myPage.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.dto.Member;

@Mapper // XML파일과 연결해줌, 상속 받은 클래스 구현 + Bean 등록
public interface MyPageMapper {


	/** 회원 정보 수정
	 * @param inputMember
	 * @return result
	 */
	int updateInfo(Member inputMember);

	
	int checkNickname(String input);

}
