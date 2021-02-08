<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="maxPage" value="${ requestScope.maxPage }"/>
<c:set var="startPage" value="${ requestScope.startPage }"/>
<c:set var="endPage" value="${ requestScope.endPage }"/>
<c:set var="currentPage" value="${ requestScope.currentPage }"/>

<%--글쓰기 페이지 이동 요청 url --%> 
<c:url var="bwf" value="/bwmove.do"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>first</title>
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(function(){
	showDiv();
	
	$("input[name=item]").on("change", function(){	//radio 버튼 상태가 바뀌면 showDiv()함수가 실행 되라
		showDiv();
	});
	
function showDiv(){
	if($("input[name=item]").eq(0).is(":checked")){
		$("#titleDiv").css("display", "block");
		$("#writerDiv").css("display", "none");
		$("#dateDiv").css("display", "none");
	}
	
	if($("input[name=item]").eq(1).is(":checked")){
		$("#titleDiv").css("display", "none");
		$("#writerDiv").css("display", "block");
		$("#dateDiv").css("display", "none");
	}
	
	if($("input[name=item]").eq(2).is(":checked")){
		$("#titleDiv").css("display", "none");
		$("#writerDiv").css("display", "none");
		$("#dateDiv").css("display", "block");
	}
}
});

function showWriteForm(){
   location.href = "${ bwf }";
}
</script>
</head>
<body>
<c:import url="../common/menubar.jsp"/>
<hr>
<%-- 로그인한 사용자만 글쓰기 기능 사용할 수 있게 함 --%>
<c:if test="${ !empty sessionScope.loginUser }">
   <div style="align: center; text-align: center;">
      <button onclick="showWriteForm();">글쓰기</button>
   </div>
</c:if>
<br>
<%-- 검색기능 --%>
<center>
<div>
	<h2>검색할 항목을 선택하시오.</h2>
	<input type="radio" name="item" value="title" checked> 제목 &nbsp; &nbsp; &nbsp;
	<input type="radio" name="item" value="writer"> 작성자 &nbsp; &nbsp; &nbsp;
	<input type="radio" name="item" value="date"> 날짜
</div>
<div id="titleDiv">
	<form action="bsearchTitle.do" method="post">
	<input type="hidden" name="page" value="1">
	<label>검색할 제목을 입력하시오 : <input type="search" name="keyword"></label>
	<input type="submit" value="검색">
	</form>
</div>
<div id="writerDiv">
<form action="bsearchWriter.do" method="post">
	<input type="hidden" name="page" value="1">
	<label>검색할 작성자 아이디를 입력하시오 : <input type="search" name="keyword"></label>
	<input type="submit" value="검색">
	</form>
</div>
<div id="dateDiv">
<form action="bsearchDate.do" method="post">
	<input type="hidden" name="page" value="1">
	<label>검색할 날짜를 입력하시오 : 
	<input type="date" name="begin"> ~ <input type="date" name="end"></label>
	<input type="submit" value="검색">
	</form>
</div>
</center>
<br>
<%-- 목록 출력 --%>
<div style="align:center; padding-left:400px;">
	<c:url var="blist" value="/blist.do">
		<c:param name="page" value="1"/>
	</c:url>
	<button onclick="javascript:location.href='${ blist }';">전체 목록 보기</button>
</div>
<br>
<table align="center" border="1" width="700" cellspacing="0">
   <tr>
      <th>번호</th>
      <th>제목</th>
      <th>작성자</th>
      <th>날짜</th>
      <th>조회수</th>
      <th>첨부파일</th>
   </tr>
      <c:forEach items="${ requestScope.list }" var="b">
   <tr>
      <td align="center">${ b.bid }</td>
      <td>
         <c:url var="bdt" value="/bdetail.do">
         <c:param name="page" value="${ currentPage }"/>
         <c:param name="bid" value="${ b.bid }"/>
         </c:url>   
          <a href="${ bdt }">${ b.btitle }</a>
      </td>
      <td align="center">${ b.bwriter }</td>
      <td align="center">${ b.b_create_date }</td>
      <td align="center">${ b.bcount }</td>
      <td align="center">
      <c:if test="${ !empty b.original_filename }">
         ◎ 
      </c:if>
      <c:if test="${ !empty b.original_filename }">
         &nbsp;
      </c:if>
      </td>
   </tr>
   </c:forEach>
   </table>
   <br>
   <%-- 페이징 처리 
   [맨처음][이전] 숫자...........  [다음][맨끝]
	--%>
   <div style="text-align: center;">
      <%-- 현재 페이지가 1이 아니면 링크설정, 현재 1페이지이면 링크없음 --%>
      <c:if test="${ currentPage <=1 }">
      [맨처음]
      </c:if>
      <c:if test="${ currentPage > 1 }">
         <c:url var="bls" value="/blist.do">
            <c:param name="page" value="1"/>
         </c:url>
      </c:if>
      <c:if test="${ (currentPage -10) < startPage and (currentPage -10) >= 1 }">
         <c:url var="bls2" value="/blist.do">
            <c:param name="page" value="${ startPage - 10 }"/>
         </c:url>      
         <a href="${ bls2 }">[이전]</a>
      </c:if>
      <c:if test="${ !((currentPage -10) < startPage and (currentPage -10) >= 1) }">
            [이전]
      </c:if>   
      <%-- 가운데 표시할 페이지 그룹 숫자 링크 설정 --%>
      <c:forEach var="p" begin="${ startPage }" end="${ endPage }" step="1">
         <c:if test="${ p eq currentPage }">
            <font size="4" color="red">[${ p }]</font>
         </c:if>
         <c:if test="${ p ne currentPage }">
            <c:url var="bls3" value="/blist.do">
               <c:param name="page" value="${ p }"/>            
            </c:url>
            <a href="${ bls3 }">${ p }</a>
         </c:if>
      </c:forEach> &nbsp;
      <!-- 다음 그룹이 있으면 링크설정, 다음 그룹 없으면 링크없음 -->
      <c:if test="${ (currentPage +10) < startPage and (currentPage +10) <maxPage }">
         <c:url var="bls4" value="/blist.do">
            <c:param name="page" value="${ startPage + 10 }"/>
         </c:url>      
         <a href="${ bls4 }">[다음그룹]</a>
      </c:if>
      <c:if test="${ !((currentPage -10) < startPage and (currentPage +10) < maxPage) }">
            [다음]
      </c:if>   
      <c:if test="${ currentPage < maxPage }">
         <c:url   var="bls5" value="blist.do">
            <c:param name="page" value="${ maxPage }"/>
         </c:url>
         <a href="${ bls }">맨끝</a>
      </c:if>
      <%-- <%
         for (int p = startPage; p <= endPage; p++) {
         if (p == currentPage) {
      %>
      <font size="4" color="red">[<%=p%>]
      </font>
      <%
         } else {
      %>
      <a href="/first/blist?page=<%=p%>"><%=p%></a>
      <%
      
      
   
      
         } //else 
      } //for
      
      &nbsp;
      다음 그룹이 있으면 링크설정, 다음 그룹 없으면 링크없음
      <%
         if ((currentPage + 10) > endPage && (currentPage + 10) < maxPage) {
      %>
      <a href="/first/blist?page=<%=endPage + 10%>">[다음]</a>
      <%
         } else {
      %>
      [다음]
      <%
         }
      %>
      &nbsp;
      현재 페이지가 끝이 아니면 링크설정, 끝페이지이면 링크없음
      <%
         if (currentPage >= maxPage) {
      %>
      [맨끝]
      <%
         } else {
      %>
      <a href="/first/blist?page=<%=maxPage%>">[맨끝]</a>
      <%
         }
      %>
 --%>   
 </div>
</body>
</html>







