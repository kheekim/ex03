package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	
	// 화면에서 페이지바를 표시하기 위해 필요한 정보
	
	private int startPage, endPage;
	private boolean prev, next;
	
	private Criteria cri; //현재 페이지, 페이지당 글 개수
	private long total; // 전체 글 개수
	
	public PageDTO(Criteria cri, long total) {
		this.cri=cri;
		this.total=total;
		//페이지 바의 끝 페이지 = (올림(현재페이지/10))*10
		endPage=(int)((Math.ceil(cri.getPageNum()/10.0))*10);
		//페이지바의 사작페이지=끝 페이지 -9
		startPage=endPage-9;
		
		//진짜 마지막 페이지 = 소수점 올림(전체 글개수/한 페이지당 개수)
		int realEnd =(int)(Math.ceil((total*1.0)/cri.getAmount()));
		
		// 페이지바의 끝페이지가 진짜 마지막 페이지보다 크면 진짜로 변경
		if(endPage>realEnd)
			endPage=realEnd;
		
		// endPage 가 끝 페이지가 아니라면 next가 true
		next = (endPage!=realEnd);
		
		// 시작 페이지가 1이 아닐 때 prev가 true
		prev=(startPage !=1);
	
	
	}
	
	

}
