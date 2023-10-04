package java.org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void TestGetList() {
		boardMapper.getList().forEach(x->log.info(x));
		
	}
	
	@Test
	public void TestGetListWithPaging() {
		Criteria cri = new Criteria();
		cri.setType("TCW");
		cri.setKeyword("테스트");
		boardMapper.getListWithPaging(cri).forEach(x->log.info("결과확인:" +x));
	}

	@Test
	public void TestRead() {
		 log.info("2번글 읽기:"+boardMapper.read(2L));
	}
	
	@Test
	public void TestInsert() {
		BoardVO vo = new BoardVO();
		vo.setTitle("mapper 테스트");
		vo.setContent("잘되겠지");
		vo.setWriter("테스터");
		boardMapper.insert(vo);
		
	}
	
	@Test
	public void TestInsertSelectKey() {
		BoardVO vo = new BoardVO();
		vo.setTitle("mapper 테스트");
		vo.setContent("잘되겠지");
		vo.setWriter("테스터");
		boardMapper.insertSelectKey(vo);
		log.info("입력된 글번호는"+vo.getBno());
		
	}
	
	@Test
	public void TestUpdate() {
		BoardVO vo = new BoardVO();
		vo.setBno(2L);
		vo.setTitle("mapper 테스트1");
		vo.setContent("잘되겠지1");
		log.info("수정결과:" + boardMapper.update(vo));
		
	}
	
	@Test
	public void TestDelete() {
		log.info("삭제 : "+boardMapper.delete(2L));
		
	}
	
	@Test //더미 데이터 넣기
	public void insertData() {
		BoardVO vo = new BoardVO();
		vo.setTitle("mapper 테스트");
		vo.setContent("잘되겠지");
		
		for(int i=0; i<5; i++) {
			vo.setTitle("mapper 테스트"+i);
			vo.setContent("잘되겠지"+i);
			vo.setWriter("테스터"+i);
			
			boardMapper.insert(vo);
		}
		
	
	}
	
	@Test
	public void TestRank() {
		boardMapper.rank().forEach(x->log.info(x));
		
	}
	
	@Test
	public void TestCount() {
		Criteria cri = new Criteria();
		log.info("전체글 개수:"+boardMapper.count(cri));
		cri.setType("C");
		cri.setKeyword("테스트");
		log.info("검색글 개수:"+boardMapper.count(cri));
	}

	@Test
	public void TestlastBoard() {
		boardMapper.lastBoard().forEach(x->log.info(x));
		log.info(boardMapper.lastBoard());
	}
	
	@Test
	public void TestHourStatistics() {
		log.info(boardMapper.hourStatistics());
		
	}
	
	@Test
	public void testGood() {
		boardMapper.good(2677010L);
	}
	
}
