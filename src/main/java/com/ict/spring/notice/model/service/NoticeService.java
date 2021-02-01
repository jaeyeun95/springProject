package com.ict.spring.notice.model.service;

import java.util.ArrayList;

import com.ict.spring.notice.model.vo.Notice;

public interface NoticeService {	// 인터페이스 = 메소드의 선언만 되있음
	ArrayList<Notice> selectAll(); //--> 미완성 메소드 abstract 메소드
	Notice selectNotice(int nid);
	int insertNotice(Notice notice);	
	int updateNotice(Notice notice);	//mybatis 기본 메소드
	int deleteNotice(int nid);
	ArrayList<Notice> selectNewTop3();	
	
}
