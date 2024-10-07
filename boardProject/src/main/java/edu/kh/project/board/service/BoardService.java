package edu.kh.project.board.service;

import java.util.Map;

import edu.kh.project.board.dto.Board;

public interface BoardService {

	/** 게시글 목록 조회
	 * @param boardCode
	 * @param cp
	 * @return map
	 */
	Map<String, Object> selectBoardList(int boardCode, int cp);

	Board selectDetail(Map<String, Integer> map);
	
}
