package com.ict.spring.board.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ict.spring.board.model.service.ReplyService;
import com.ict.spring.board.model.vo.Board;
import com.ict.spring.board.model.vo.Reply;

@Controller
public class ReplyController {
	@Autowired
	private ReplyService replyService;

	@RequestMapping(value = "rinsert.do", method = RequestMethod.POST) // form태그에서 POST방식으로 보내주니까 post로 받아야됨
	public String replyInsertMethod(Reply reply, Model model) { // command객체인 Reply전체가 받겠따
		if (replyService.insertReply(reply) > 0) {
			return "redirect:bdetail.do?bid=" + reply.getRef_bid();
		} else {
			model.addAttribute("mgs", reply.getRef_bid() + "번 글에 대한 댓글달기 실패");
			return "common/errorPage";
		}

	}
	
	// ajax 로 원글에 대한 댓글 조회 요청 처리용
	@RequestMapping(value="rlist.do", method=RequestMethod.POST)
	@ResponseBody
	public String selectTop3Method(@RequestParam("ref_bid") int ref_bid) throws UnsupportedEncodingException {	//전송온 ref_bid를 int ref_bid에 담아주겠다
		// 원글에 대한 댓글 조회 요청
		ArrayList<Reply> list = replyService.selectList(ref_bid); // selectList(ref_bid)로

		// 전송용 json 객체 준비
		JSONObject sendJson = new JSONObject();
		// list 옮길 json 배열 준비
		JSONArray jarr = new JSONArray();

		// list 를 jarr 로 옮기기(복사)
		for (Reply reply : list) {
			// reply 필드값 저장할 json 객체 생성
			JSONObject job = new JSONObject();

			job.put("rid", reply.getRid()); // map이랑 같다
			job.put("rwriter", reply.getRwriter()); // 인코딩 해서 제이슨 객체 안에 담는다
			job.put("rcontent", URLEncoder.encode(reply.getRcontent(), "utf-8"));
			job.put("r_create_date", reply.getR_create_date().toString());	//날짜 데이터를 json에 담을때는 반드시 String 형으로 바꾸고 보내줘야 뷰에서 출력된다
			job.put("ref_bid", reply.getRef_bid());
			// 날짜형식의 데이터를 json객체에 담을 때 주의사항, 뷰쪽에서 꺼낼 때 에러가나서, string형으로 바꿔서 json에 담아줘야한다.

			// job 를 jarr 에 저장
			jarr.add(job);
		}

		// 전송용 json 객체에 jarr 담음
		sendJson.put("list", jarr);

		return sendJson.toJSONString(); // jsonView 가 리턴됨

	}
	
	@RequestMapping("rdel.do")
	public String replyDeleteMethod(@RequestParam("rid") int rid,
			@RequestParam("bid") int bid, Model model) {
		if (replyService.deleteReply(rid) > 0) {
			return "redirect:bdetail.do?bid=" + bid;
		} else {
			model.addAttribute("mgs", rid + "번 댓글삭제 실패");
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="rupdate.do", method=RequestMethod.POST)
	public String replyUpdateMethod(Reply reply, 
			@RequestParam("bid") int bid,Model model) {
		if (replyService.updateReply(reply) > 0) {
			return "redirect:bdetail.do?bid=" + bid;
		} else {
			model.addAttribute("mgs", reply.getRid() + "번 댓글삭제 실패");
			return "common/errorPage";
		}
	}
	
	
	

}
