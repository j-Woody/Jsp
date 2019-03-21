<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function() {
		$('#bTitle').keyup(function () {
			var bTitle = $('#bTitle').val();
			var max = 20;
			if(bTitle.length>max){
				alert('제목은 20자를 초과할 수 없습니다.');
				$('#bTitle').val(bTitle.substring(0,20));
				$('input[type="submit"]').attr("disabled",true);
			}else{
				$('input[type="submit"]').attr("disabled",false);
			}
		});
		$('#bContent').keyup(function () {
			var bTitle = $('#bContent').val();
			var max = 500;
			if(bContent.length>max){
				alert('500자를 초과할 수 없습니다.');	
			}
		});
	});
</script>
</head>
<body>
<c:if test="${not empty Msg }">
	<script>
		alert('${Msg}');
		location.href="";
	</script>
</c:if>
<c:if test="${not empty errorMsg }">
	<script>
		alert('${errorMsg}');
	</script>
</c:if>
	<form action="${conPath }/bInsert.do" method="post" enctype="multipart/form-data">
		<table>
			<caption>글쓰기</caption>
			
			<tr><th>제목</th>
					<td>
						<input type="text" name="bTitle" id="bTitle" required="required" size="30">
					</td>
			</tr>
			<tr><th>본문</th>
					<td>
						<textarea rows="5" cols="32" id="bContent" name="bContent"></textarea>
					</td>
			</tr>
			<tr>
				<th>파일</th>
					<td>
						<input type="file" name=file>
					</td>
			</tr>
			<tr><td colspan="2">
						<input type="submit" value="글쓰기">
						<input type="reset" value="취소">
						<input type="button" value="목록" onclick="location.href='${conPath}/bList.do'">
		</table>
	</form>
</body>
</html>