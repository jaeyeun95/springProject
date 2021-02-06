<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 상대경로로 대상 파일의 위치를 지정한 경우 --%>
<c:import url="../common/menubar.jsp"/>
<%-- JSTL 에서는 절대경로 표기법이 달라짐 : /context-root명 ==> / 로 바뀜
	 예 : "/first/common/menubar.jsp" ==> "/common/menubar.jsp" --%>
<%-- <c:import url="/views/common/menubar.jsp"/> --%>
<hr>
<h1 align="center">공지사항</h1>
<br>
<c:if test="${ !empty sessionScope.loginUser and sessionScope.loginUser.id eq 'admin'}">
	<div style="align:center; padding-left:400px;">
	<c:url var="nwf" value="/nwform.do"></c:url>
	<button onclick="javascript:location.href='${ nwf }';">글쓰기</button>
	</div>
</c:if>
<br>
<table align="center" width="500" border="1" cellspacing="0" cellpadding="1">
<tr><th>번호</th><th>제목</th><th>작성자</th><th>첨부파일</th><th>날짜</th></tr>
<c:forEach items="${ requestScope.list }" var="n">
<tr>
	<td align="center">${ n.nid }</td>
	<c:url value="ndetail.do" var="und">
		<c:param name="nid" value="${ n.nid }"/>
	</c:url>
	<td align="center"><a href="${ und }">${ n.ntitle }</a></td>
	<td align="center">${ n.nwriter }</td>
	<td align="center">
		<c:if test="${ !empty n.file_path }">◎</c:if>
		<c:if test="${ empty n.file_path }">&nbsp;</c:if>
	</td>
	<td align="center">
		<fmt:formatDate value="${n.n_create_date }" pattern="yyyy-MM-dd"/>
	</td>
</tr>
</c:forEach>
</table>


</body>
</html>







