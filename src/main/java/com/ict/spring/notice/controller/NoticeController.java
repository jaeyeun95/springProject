package com.ict.spring.notice.controller;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	
	//공지글 상세보기 요청 처리용
	@RequestMapping("ndetail.do")
	public String noticeDetailMethod(@RequestParam("nid") int nid, Model model) {	//RequestParam은 파싱까지 해줌, 객체 처리를 위해 model사용
		Notice notice = noticeService.selectNotice(nid);
		
		if(notice != null) {
			model.addAttribute("notice", notice);
			return "notice/noticeDetailView";
		}else {
			model.addAttribute("msg", nid + "번 공지글 상세보기 실패");
			return "common/errorPage";
		}
	}
	
	//공지글 등록 페이지 요청 처리용
	@RequestMapping("nwform.do")
	public String noticeWriteForm() {
		return "notice/noticeWriteForm";
	}
	
	//파일업로드 기능이 있는 공지글 등록 요청 처리용
		@RequestMapping(value="ninsert.do", method=RequestMethod.POST)
		public String noticeInsertMethod(Notice notice, HttpServletRequest request,
						@RequestParam(name="upfile", required=false) MultipartFile mfile, Model model) {
			//업로드된 파일 저장 폴더 지정하기
			String savePath = request.getSession().getServletContext().getRealPath("resources/notice_files");
			
			//업로드된 파일을 지정 폴더로 옮기기
			try {
				mfile.transferTo(new File(savePath + "\\" + mfile.getOriginalFilename()));
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "전송 파일 저장 실패");
				return "common/errorPage";
			}
			notice.setFile_path(mfile.getOriginalFilename());
			logger.info("ninsert.do : " + notice);
			
			if(noticeService.insertNotice(notice) > 0) {
				return "redirect:nlist.do";
			}else {
				model.addAttribute("msg","공지글 등록 실패.");
				return "common/errorPage";
			}
		}
	
}








