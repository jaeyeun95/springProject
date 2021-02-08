package com.ict.spring.notice.model.service;

import java.util.ArrayList;

import com.ict.spring.notice.model.vo.Notice;
import com.ict.spring.common.SearchDate;

public interface NoticeService {	// 인터페이스 = 메소드의 선언만 되있음
	ArrayList<Notice> selectAll(); //--> 미완성 메소드 abstract 메소드
	Notice selectNotice(int nid);
	int insertNotice(Notice notice);	
	int updateNotice(Notice notice);	//mybatis 기본 메소드
	int deleteNotice(int nid);
	ArrayList<Notice> selectNewTop3();
	ArrayList<Notice> selectSearchTitle(String keyword);
	ArrayList<Notice> selectSearchWriter(String keyword);
	ArrayList<Notice> selectSearchDate(SearchDate dates);
}
