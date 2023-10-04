package org.zerock.controller;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/**")
@AllArgsConstructor
public class BoardController {
	
	
	BoardService service; //생성자 주입
	
	@GetMapping("/list") //전체 목록 board/list(get)
	public void list(Model model, Criteria cri) {
		log.info("list url 요청...");
		model.addAttribute("list", service.getList(cri)); //글목록
		model.addAttribute("pageMaker", new PageDTO(cri, 123));//페이지바 정보
//		model.addAttribute("now", now);
		
		
		// board/list.jsp
	}
	
	//등록 처리 (모든 항목-BoardVO)board/register(post) <- 입력화면(get)
	@PostMapping("/register")
	public String register(BoardVO vo, RedirectAttributes rttr) {
		log.info("register url post 요청");
		service.register(vo);
		rttr.addFlashAttribute("oper", "create");
		rttr.addFlashAttribute("result", vo.getBno()); // why? 데이터(작성글번호) 한번만 전송
		return "redirect:/board/list"; //why? redirect
										// redirect 가 있으면 요청
		
	}
	

	
	@GetMapping("/register")
	public void register() {
		
	}
	
	//조회(글번호-bno) board/get(get) //p218
	@GetMapping("/get")
	public void get(Long bno, Model model, Criteria cri) {
		log.info("글 상세보기 url 요청");
		model.addAttribute("board", service.get(bno));
		model.addAttribute("cri", cri);

		// -> board/get.jsp
		
	}
	
	
	//삭제(글번호-bno) board/remove(post) <-입력화면(get)
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr, Model model, Criteria cri, String pw) {
		log.info("삭제 url 요청");
		log.info("입력된 패스워드"+pw);
		if(pw.equals("1234")) {
		if(service.remove(bno)){// 이상 없으먼 result 이름으로 success 라는 문자 전송
			rttr.addFlashAttribute("oper", "remove");
			rttr.addFlashAttribute("result", bno);
		}
		return "redirect:/board/list?pageNum="+cri.getPageNum()+
				"&amount="+cri.getAmount();	
		} else {
			rttr.addFlashAttribute("alert", "fail");
			return "redirect:/board/remove?bno= ";
		}
	}
	
	@GetMapping("/remove")
	public String remove(Long bno,Model model,Criteria cri) {
		log.info("삭제화면 요청");
		

		model.addAttribute("board",service.get(bno));
		model.addAttribute("cri", cri);
		
		return "redirect:/board/list?pageNum="+cri.getPageNum()+
				"&amount="+cri.getAmount();	
	}
	

	//수정(수정글-BoardV) board/modify (post) <- 입력화면(get)
	@PostMapping("/modify")
	public String modify(BoardVO vo, RedirectAttributes rttr, Criteria cri) {
		log.info("수정 url 요청");
		if(service.modify(vo)) {
			rttr.addFlashAttribute("oper", "modify");
			rttr.addFlashAttribute("result", vo.getBno());
		}
		
		return "redirect:/board/list?pageNum="+cri.getPageNum()+
				"&amount="+cri.getAmount();	
	}
	
	@GetMapping("/modify")
	public void modify(Long bno,Model model,Criteria cri) {
		log.info("수정화면 요청");
		model.addAttribute("board",service.get(bno));
		model.addAttribute("cri", cri);
	}
	

	
	// 좋아요 처리
	@GetMapping("/good")
	public String good(Long bno, Criteria cri) {
		service.good(bno);
		return "redirect:/board/get?bno="+bno+"&pageNum="+cri.getPageNum()+"&amount="+cri.getAmount();
				
	}
	
//	@PostMapping
//	public String time(BoardVO vo, Model model, Criteria cri) {
//		log.info("시간 요청");
//		
//		
//		
//		return "redirect:/board/list";	
//	}

	
	
	
	
	
	

}
