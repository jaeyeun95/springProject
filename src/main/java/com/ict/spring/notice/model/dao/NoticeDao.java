package com.ict.spring.notice.model.dao;



import java.util.ArrayList;
import java.util.List;

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
	
	public ArrayList<Notice> selectList(){
		List<Notice> list = sqlSession.selectList("noticeMapper.selectAll");	//noticeMapper.selectAll 을 리턴하겠다
		return (ArrayList<Notice>)list;
	}
	
	public Notice selectOne(int nid) {
		return null;
	}
	
	public int insertNotice(Notice notice) {
		return 0;
	}
	
	public int updateNotice(Notice notice) {
		return 0;
	}
	
	public int deleteNotice(int nid) {
		return 0;
	}
	
	public ArrayList<Notice> selectNewTop3(){
		return null;
	}

}
