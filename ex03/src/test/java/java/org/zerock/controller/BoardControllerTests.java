package java.org.zerock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.org.zerock.service.BoardServiceTest;

import lombok.extern.log4j.Log4j;

@WebAppConfiguration //컨트롤러 테스트위해
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" }) //컨트롤러 테스트위해

	
	
@Log4j
public class BoardControllerTests {
	
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc; // 이 객체를 이용해서 컨트롤러는 테스트를 해야함
							// 객체를 직접 인스턴해서 사용해야함.
	
	@Before //junit으로 테스트할 때 먼저 수행하는 메소드
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testList() throws Exception {
		
		// board/list?id=abc&pw=1234
		MockMvcRequestBuilders.get("/board/list").param("id", "abc").param("pw", "1234");
		
		log.info("url 리스트 요청결과 모델데이터:"+ mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView().getModelMap());
		
	}
		@Test
		public void testRegister() throws Exception {
			log.info("등록 처리 요청결과:"+
				mockMvc.perform(MockMvcRequestBuilders.post("/board/register").param("title","junit으로")
						.param("content", "mockMVC를 이용해서").param("writer", "tester1"))
				.andReturn().getModelAndView().getViewName());
		}
		
		@Test
		public void testGet() throws Exception {
			log.info("url 상세보기 모델 데이터:"+
				mockMvc.perform(MockMvcRequestBuilders.get("/board/get").param("bno","12"))
				.andReturn().getModelAndView().getModelMap());
		}
		
		@Test
		public void testRemove() throws Exception {
			log.info("url 삭제처리 모델데이터:"+
				mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
						.param("bno","12"))
				.andReturn().getModelAndView().getViewName());
		}
		
		@Test
		public void testModify() throws Exception {
			log.info("수정 처리 요청결과:"+
				mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
						.param("bno","21").param("title","junit으로")
						.param("content", "mockMVC를 이용해서"))
				.andReturn().getModelAndView().getViewName());
		}
		
		@Test
		public void testgetregister() throws Exception {
			log.info("url 등록 화면 요청:"+
				mockMvc.perform(MockMvcRequestBuilders.get("/board/register"))
				.andReturn().getModelAndView().getViewName());
		}
		
		@Test
		public void testGetremove() throws Exception {
			log.info("url 삭제 화면 요청:"+
				mockMvc.perform(MockMvcRequestBuilders.get("/board/remove"))
				.andReturn().getModelAndView().getViewName());
		}
	

}
