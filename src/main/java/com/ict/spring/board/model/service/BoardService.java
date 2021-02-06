package com.ict.spring.board.model.service;

import java.util.ArrayList;

import com.ict.spring.board.model.vo.Board;

public interface BoardService {
// 앞에 public abstract 는 생략(자동생성됨)
	ArrayList<Board> selectTop3();
	int getListCount();
	ArrayList<Board> selectList(int currentPage, int limit);
	Board selectBoard(int bid);	//페이지별로 상세보기 
	int addReadCount(int bid);
	int insertBoard(Board board);
	int updateBoard(Board board);
	int deleteBoard(int bid);
}
