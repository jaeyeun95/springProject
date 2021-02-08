package com.ict.spring.notice.model.vo;

import java.sql.Date;

//네트워크로 입출력 할 게 아니고 백엔드 쪽에서만 쓰기 때문에 직렬화 생략하고 사용
public class SearchDate {
	private Date begin;
	private Date end;
	
	public SearchDate() {
		
	}

	public SearchDate(Date begin, Date end) {
		super();
		this.begin = begin;
		this.end = end;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "SearchDate [begin=" + begin + ", end=" + end + "]";
	}
	
	
}
