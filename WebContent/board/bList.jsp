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
table {
	
	border: 2px solid pink; 
	width:70%; 
	margin: 30px auto 10px auto;
	border-radius: 10px;
	text-overflow:ellipsis;

}
table tr:hover { background-color: #b3ff9e;
	cursor: pointer;
}
td, th {text-align: center; padding:5px; }
th{
background-color: #b3ff9e;
}
h2{text-align: center; }
a { text-decoration: none;}
.left{
padding:0 10%;
text-align: left;
text-overflow:ellipsis;

}
.paging {text-align: center;}
b {color:red;}
#seach{
	text-align: center;
	ovarflow:hidden;
	margin-bottom: 30px;
}
.viewTitle{
	width: 50%;
}
table td:not(.viewTitle) {	
	width:10%;

}
</style>
</head>
<body>
<jsp:include page="../main/header.jsp" />
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
		<tr><th>글번호</th><th class="viewTitle">글제목</th><th>작성자</th><th>작성일</th>
				<th>조회수</th>
		</tr>
		<c:if test="${totCnt == 0 }">
			<tr><td colspan="5">글이 없습니다</td></tr>
		</c:if>
		<c:if test="${totCnt != 0 }">
			<c:forEach var="bDto" items="${bDto }">
				<tr><td>${bDto.bNo }</td>
						<td class="left">
							<c:forEach var="i" begin="1" end="${bDto.bIndent }">
								<c:if test="${i == bDto.bIndent }">
								   └─
								</c:if>
								<c:if test="${i != bDto.bIndent }">
								    &nbsp; &nbsp; &nbsp; 
								</c:if>
							</c:forEach>
				<a href="${conPath }/bView.do?bNo=${bDto.bNo}&pageNum=${pageNum}">
							${bDto.bTitle }</a>
						</td>
						
						<td>${bDto.mEmail }</td>
						<td><fmt:formatDate value="${bDto.bDate }" pattern="yyyy/MM/dd(E)"/></td>
						<td>${bDto.bHit }</td>
					</tr>
			</c:forEach>
		</c:if>
	</table>
	<div class="paging">
		<c:if test="${startPage > BLOCKSIZE }">
			<a href="${conPath }/bList.do?pageNum=${startPage-1}"> 이전 </a>
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i == pageNum }">
				[ <b>${i }</b> ]
			</c:if>
			<c:if test="${i != pageNum }">
				[ <a href="${conPath }/bList.do?pageNum=${i}">${i }</a> ]
			</c:if>
		</c:forEach>
		<c:if test="${endPage < pageCnt }">
			<a href="${conPath }/bList.do?pageNum=${endPage+1}"> 다음 </a>
		</c:if>
	</div>
	
	<div id="seach">
		<form action="${conPath }/bSeach.do">
			<select name="select">
					<option value="제목" selected="selected">제목</option>
				<option value="작성자">작성자</option>
			</select>
			<input type="text" name="key" width="30">
			<input type="submit" value="검색">
			<c:if test="${not empty mDto }">
				<input type="button" onclick="location.href='${conPath}/bInsertView.do'" value="글쓰기">
			</c:if>
		</form>
		
	</div>
</body>
</html>