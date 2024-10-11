package edu.kh.project.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.kh.project.board.dto.Comment;

@Mapper
public interface CommentMapper {

	/** 댓글 등록
	 * @param comment
	 * @return result
	 */
	int commentInsert(Comment comment);

	/**
	 * 
	 * @param commentNo
	 * @param memberNo
	 * @return
	 */
	int commentDelete(@Param("commentNo") int commentNo, @Param("memberNo") int memberNo);

	int commentUpdate(Comment comment);

}
