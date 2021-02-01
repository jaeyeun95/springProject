package com.ict.spring.notice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ict.spring.notice.model.service.NoticeService;

@Controller
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger("NoticeController.class");
	
	@Autowired // 자동 DI 선언을 위해서 사용
	private NoticeService noticeService;
	
	
}
