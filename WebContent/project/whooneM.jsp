<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
     <c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#SPwrap{
		width: 80%;
		margin: 0 auto;
		text-align: center;
	}
	.button{
		cursor: focus;
	}
</style>
</head>
<body>
<div id="SPwrap">
	<table>
	<c:forEach begin="0" items="${sDto }" var="s" end="${fn:length(sDto)}">
	<tr>
		<td>후원명</td>
		<td>후원금액</td>
		<td>후원설명</td>
	</tr>
	<tr class="button" onclick="location.href='${conPath}/pWHoModify.do?spNo=${s.spNo }&pay=${s.pay }'">
	<fmt:formatNumber value="${s.pay }" type="number" pattern="###,###" var="pay"/>
		<th>${s.pName}</th>
		<td>${pay }</td>
		<td>${s.pexp}</td>
	</tr>
	</c:forEach>
	</table>
</div>
</body>
</html>