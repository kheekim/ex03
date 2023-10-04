package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.HourStatisticsVO;
import org.zerock.domain.RankVO;

public interface BoardMapper {
	
	//@Select("select * from tbl_board")
	//게시글 목록보기
	List<BoardVO> getList();
	
	//페이지 처리 - 현재는 무조건 2페이지 출력(한페이지당10개)
	List<BoardVO> getListWithPaging(Criteria cri);
	
	// 게시글 상세보기
	BoardVO read(Long bno);
	
	//게시글 작성
	void insert(BoardVO vo);  // insert, update, delete 시에는 return 타입을 int 하면 처리된 행의 개수가 리턴된다.
	
	// 게시글 작성(작성된 글번호 확인)
	void insertSelectKey(BoardVO vo);
	
	//게시글 수정
	int update(BoardVO vo);
	
	//게시글 삭제
	int delete(Long bno);
	
	//보너스(작성글 랭킹 5등까지만 작석장, 장성글 개수)
	List<RankVO> rank();
	
	//보너스(업데이트 일자 기준으로 최근 갱신된 글 5개)
	List<BoardVO> lastBoard();
	
	//보너스(시간대별 글 개수 통계 작성일 기준)
	List<HourStatisticsVO> hourStatistics();

	
	//전체 글 개수 가져오기
	Long count(Criteria cri);
	
	//좋아요
	void good(Long bno);
	
	void time();
	//게시글 페이지 처리
	
	//게시글 검색
}
