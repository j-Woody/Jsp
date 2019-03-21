<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${conPath }/bReply.do" method="post">
		<input type="hidden" name="pageNum" value="${pageNum }">
		<input type="hidden" name="bGroup" value="${bDto.bGroup }">
		<input type="hidden" name="bStep" value="${bDto.bStep }">
		<input type="hidden" name="bIndent" value="${bDto.bIndent }"> 
		<table>
			<caption>${bDto.bNo }번 글의 답변 쓰기</caption>
			<tr><th>제목</th>
					<td><input type="text" name="bTitle" size="30" required="required" value="[답]${bDto.bTitle.substring(0,3) }"></td>
			</tr>
			<tr><th>본문</th>
					<td><textarea rows="5" cols="32" name="bContent"></textarea></td>
			</tr>
			<tr><td colspan="2">
						<input type="submit" value="답변저장">
						<input type="reset" value="취소">
						<input type="button" value="이전" onclick="history.go(-1)">
						<input type="button" value="목록" onclick="location='${conPath}/bList.do?pageNum=${pageNum}'">
		</table>
	</form>
</body>
</html>