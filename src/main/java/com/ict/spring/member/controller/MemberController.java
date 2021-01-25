package com.ict.spring.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller	// Controller타입의 클래스로 선언해서 빈 스캐닝에 자동으로 등록시켜서 빈객체를 만들겠다.(이름을 지정하지 않으면 클래스명
			// 앞에 글자를 소문자로해서 id가 만들어진다.
public class MemberController {
	
	/**
	 * @RequestMapping(value="login.do",method=RequestMethod.POST)
	 * 
	 *  @RequestMapping 타입의 어노테이션을 붙여줌으로써 HandlerMapping 등록
	 *  
	 *  @RequestMapping의 속성
	 *  여러개의 속성을 명시 할 때는 "value=", "method="를 명시해야되고
	 *  value만 명시해야되는 경우 직접적으로 @RequestMapping("login.do")처럼 생략가능
	 *  
	 *  (value="",method="")와 같이 매핑 조건을 부여하고 전달하는 method방식을 지정해준다.
	 */
	
	// 파라미터를 전송하는 방법
	/*
	 * 1. HttpServletRequest를 통해 전송받기(기존 jsp/servlet때 방식)
	 * 	    메소드의 매개변수로 HttpServletRequest를 작성하면 메소드 실행 시
	 * 	    스프링 컨테이너가 자동으로 객체를 인자로 주입해준다.
	 */
	
//	@RequestMapping(value="login.do",method=RequestMethod.POST)
//	public String memberLogin(HttpServletRequest request) {
//		String id = request.getParameter("id");
//		String pwd = request.getParameter("pwd");
//		
//		System.out.println("ID : " + id);
//		System.out.println("PWD : " + pwd);
//		
//		return "home";
//	}
	
	/*
	 * 2.	@RequestParam 어노테이션 방식
	 * 
	 * 		스프링에서는 조금 더 간단하게 파라미터를 받아올 수 있는 방법 제공
	 * 		HttpServlet과 비슷하게 request객체를 이용하여 데이터를 전송받는 방법
	 * 
	 * 		파라미터 value속성에 없는 값이 넘어오는 경우 ""이기 때문에 에러는 안난다.
	 * */
	
	@RequestMapping(value="login.do",method=RequestMethod.POST)
	public String memberLogin(@RequestParam("id") String id,
							  @RequestParam("pwd") String pwd) {
		System.out.println("ID : " + id);
		System.out.println("PWD : " + pwd);
		
		return "home";
	}
	
}
