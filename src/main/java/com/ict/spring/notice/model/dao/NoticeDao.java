package com.ict.spring.notice.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.spring.notice.model.vo.Notice;

@Repository("noticeDao")	// Dao 어노테이션은 Repository
public class NoticeDao {
	// 스프링-마이바티스 연동 객체 연결함 : root-context.xml 에 선언되어 있음
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public NoticeDao() {}
	
	public ArrayList<Notice> selectList(){}
	
	public Notice selectOne(int nid) {}
	
	public int insertNotice(Notice notice) {}
	
	public int updateNotice(Notice notice) {}
	
	public int deleteNotice(int nid) {}
	
	public ArrayList<Notice> selectNewTop3(){}

}
