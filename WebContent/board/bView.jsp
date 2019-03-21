<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	
	font-size: 12px;
}
table {border: 2px solid pink; width:80%; margin: 30px auto;}

table tr:hover { background-color: #b3ff9e;
	cursor: pointer;
}
th {
	text-align: center; padding:5px; width: 20%; 
}
td{
	text-align: center; padding:5px; width:80%;
}
caption {font-size: 25px; padding:10px;}
h2{text-align: center; color:red;}
a { text-decoration: none;}
.left{text-align: left;}
.paging {text-align: center;}
#bImage{
	width: 70%;
}
</style>
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
<c:if test="${not empty Msg}">
		<script>
			alert('${Msg}');
		</script>
	</c:if>
	<c:if test="${not empty errorMsg}">
		<script>
			alert('${errorMsg}');
		</script>
	</c:if>
	<table>
		<caption>${bDto.bNo }글 상세보기</caption>
		<tr><th>글번호</th><td>${bDto.bNo }</td></tr>
		<tr><th>작성자</th><td>${bDto.mEmail}</td></tr>
		<tr><th>제 목</th><td>${bDto.bTitle}</td></tr>
		<tr><th>본문</th><td><pre>${bDto.bContent}</pre></td></tr>
		<tr><th>첨부파일</th><td><img alt="${bDto.bImage }" src="${conPath }/boardImg/${bDto.bImage}" id="bImage"> </td></tr>
		<tr><th>작성일</th>
				<td><fmt:formatDate value="${bDto.bDate}" pattern="yy/MM/dd(E)"/></td>
		</tr>
		<tr><th>조회수</th><td>${bDto.bHit}</td></tr>
		<tr><th>IP</th><td>${bDto.bIp }</td></tr>
		<tr><td colspan="2">
		<c:if test="${not empty mEmail && mDto.mEmail eq bDto.mEmail}">
					<button onclick="location='${conPath}/bModifyView.do?bNo=${bDto.bNo}&pageNum=${pageNum }'">수정</button>
					<button onclick="location='${conPath}/bDelete.do?bNo=${bDto.bNo }&pageNum=${pageNum }'">삭제</button>
		</c:if>
		<c:if test="${not empty mEmail }">
					<button onclick="location='${conPath}/bReplyView.do?bNo=${bDto.bNo}&pageNum=${pageNum }'">답변</button>
		</c:if>
					<button onclick="location='${conPath}/bList.do?pageNum=${pageNum }'">목록</button>
				</td>
		</tr>
	</table>
</body>
</html>