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
	<form action="${conPath }/bModify.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="pageNum" value="${pageNum }">
		<input type="hidden" name="bNo" value="${bDto.bNo}">
		<input type="hidden" name=bfImage value="${bDto.bImage }">
		<table>
			<caption>${bDto.bNo }번 글 수정</caption>
			<tr><th>제목</th>
					<td><input type="text" name="bTitle" required="required" size="30"
								value="${bDto.bTitle }"></td>
			</tr>
			<tr><th>본문</th>
					<td><textarea rows="5" cols="32" 
							name="bContent">${bDto.bContent }</textarea></td>
			</tr>
			<tr><th>파일(사진)</th>
					<td><input type="file" name="bImage"></td>
			</tr>
			<tr><td colspan="2">
						<input type="submit" value="수정">
						<input type="button" value="목록" onclick="location='${conPath}/bList.do?pageNum=${pageNum }'">
						<input type="reset" value="취소"
						  onclick="history.back()">
					</td>
			</tr>
		</table>
	</form>
</body>
</html>