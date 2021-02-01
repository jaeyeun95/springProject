package com.ict.spring.notice.model.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.ict.spring.notice.model.vo.Notice;

@Service("noticeService") // Service 관련된 어노테이션  = Service, 자바 소스로는 레퍼런스라고 생각하면됨
public class NoticeServiceImpl implements NoticeService {	//추상 메소드를 상속받아서 완성시킴

	@Override
	public ArrayList<Notice> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notice selectNotice(int nid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertNotice(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateNotice(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteNotice(int nid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Notice> selectNewTop3() {
		// TODO Auto-generated method stub
		return null;
	}

}
