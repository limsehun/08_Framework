package edu.kh.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.demo.dto.UserDto;

/* @Mapper
 * - 마이바티스 mapper와 연결된 읹터페이스임을 명시
 * - 자동으로 해당 인터페이스를 상속 받은 클래스를 만들어 Bean으로 등록
 */
@Mapper
public interface TestMapper {
	
	/**
	 * 
	 * @param userNo
	 * @return userName
	 */
		String selectUserName(int userNo);
		// -> 해당 메서드가 호출된 경우 연결되어져 있는 mapper.xml파일에서 id속성값이
		//    메서드명과 같은 sql 태그가 수행된다!

		/** 사용자 전체 조회
		 * mapper.xml 파일에서 resultType이 DTO로 설정되어 있는데
		 * 반환형은 List<DTO> 형태라서 다르다고 생각할 수 있지만
		 * 한 행이 조회될 때 마다 List에 DTO가 add(추가) 되는 것이다!!
		 * @return userList
		 */
		List<UserDto> selectAll();

		UserDto selectUser(int userNo);

		int updateUser(UserDto user);

		int deleteUser(int userNo);

		int insertUser(UserDto user);
		
		
}
