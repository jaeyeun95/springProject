package com.ict.spring.board.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.spring.board.model.dao.BoardDao;
import com.ict.spring.board.model.vo.Board;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;

	@Override
	public ArrayList<Board> selectTop3() {
		return boardDao.selectTop3();
	}

	@Override
	public int getListCount() {
		return boardDao.getListCount();
	}

	@Override
	public ArrayList<Board> selectList(int currentPage, int limit) {
		return boardDao.selectList(currentPage, limit);
	}

	@Override
	public Board selectBoard(int bid) {
		return boardDao.selectBoard(bid);
	}

	@Override
	public int addReadCount(int bid) {
		return boardDao.addReadCount(bid);
	}

	@Override
	public int insertBoard(Board board) {
		return boardDao.insertBoard(board);
	}

	@Override
	public int updateBoard(Board board) {
		return boardDao.updateBoard(board);
	}

	@Override
	public int deleteBoard(int bid) {
		return boardDao.deleteBoard(bid);
	}

}