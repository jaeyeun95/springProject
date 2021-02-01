package com.ict.spring.notice.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ict.spring.notice.model.service.NoticeService;
import com.ict.spring.notice.model.vo.Notice;

@Controller
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger("NoticeController.class");
	
	@Autowired // 자동 DI 선언을 위해서 사용
	private NoticeService noticeService;
	
	//공지사항 전체 목록보기 요청 처리용 
	@RequestMapping("nlist.do")	// 메소드 생략시 기본이 get방식
	public String noticeListMethod(Model model) {	// 일반적인 리턴 ->String형이나 뷰파일의Notice턴하는 것이 일반적임, Model은 spring이 제공하는 클래스 (request와 response를 같이 가지고 있는 클래스)
		ArrayList<Notice> list = noticeService.selectAll();
		
		if(list.size() > 0) {
			model.addAttribute("list",list);	// list이름으로 list를 담아줘라
			return "notice/noticeListView";
		}else {
			model.addAttribute("msg", "등록된 공지사항 정보가 없습니다.");
			return "common/errorPage";
		}
	}
	
}








