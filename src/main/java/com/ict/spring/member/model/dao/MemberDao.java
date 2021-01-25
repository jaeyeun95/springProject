package com.ict.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.spring.member.model.vo.Member;

//@Repository라는 어노테이션 : DB와 접근하는 클래스에 부여(@Conponent의 구체화된 개념이다)
@Repository("mDao")
public class MemberDao {
	
	// 데이터베이스 연결객체 (root-context에서 작성한 bean으로 생성되어 주입이된다.)
	@Autowired
	SqlSessionTemplate sqlSession;

	public Member loginMember(Member m) {
		
		return (Member)sqlSession.selectOne("memberMapper.loginMember",m);	// ""는 필요한 정보 m만 넘겨준다
	}

}
