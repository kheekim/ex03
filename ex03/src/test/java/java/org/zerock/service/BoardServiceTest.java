package java.org.zerock.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.service.BoardService;

import lombok.extern.log4j.Log4j;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j

public class BoardServiceTest {

	
	@Autowired
	private  BoardService service;
	
	@Test
	public void TestgetList() {
		log.info("리스트 서비스 : " + service.getList(new Criteria()));		
	}
	
	@Test
	public void TestRegister() {
		
		BoardVO vo = new BoardVO();
		vo.setBno(31L);
		vo.setTitle("테스트 30");
		vo.setContent("테스트 30");
		vo.setWriter("테스터");
		log.info("등록 서비스:");
		service.register(vo);
		
	}
	
	@Test
	public void TeervicstModify() {
		BoardVO vo = new BoardVO();
		vo.setBno(31L);
		vo.setTitle("테스트 31");
		vo.setContent("테스트 31");
		
		log.info("수정 서비스 : " + service.modify(vo));
	}
	
	@Test
	public void TestRemove() {
		log.info("삭제 서비스 : " + service.remove(31L));
	}
	
	@Test
	public void TestGet() {
		log.info("상세보기 서비스 : " + service.get(1L));
	}
	

	@Test
	public void TestGood() {
		log.info("좋아요 +1");
		service.good(2687010L);
	}
	
//	@Test
//	public void TestNow() {
//		log.info("현재 시간:"+service.now());
//	}
}
