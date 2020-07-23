package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {

	public int insert(ReplyVO vo);
	
	public ReplyVO read(Long bno);
	
	public int delete (Long rno);
	
	public int update(ReplyVO reply);
	
	public List<ReplyVO> getListWithPaging (
			@Param("cri") Criteria cri,
			@Param("bno") Long bno);
	
	public int getCountByBno(Long bno); // 댓글의 숫자 파악
	
	/*
	 * 댓글의 목록과 페이징 처리는 기존의 게시물 페이징 처리와 유사하지만, 추가적으로 특정
	 * 한 게시물의 댓글들만을 대상으로 하기 때문에 추가로 게시물의 번호가 필요하게 됩니다.
	 * 
	 * MyBatis는 두개 이상의 데이터를 파라미터로 전달하기 위해서는 
	 * 1.별도의 객체로 구성하거나
	 * 2.Map을 이용하는 방식
	 * 3.@Param을 이용해서 이름을 사용하는 방식
	 * 
	 */
}
