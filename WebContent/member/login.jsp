<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#contentWrap{
		width:100%
		height:auto;
	}
	#content{
		width:600px;
		height: 500px;
		margin:auto;
	}
	#content table, #content div{
		width:80%;
		margin: 50px auto;
		text-align:center;
	}
	#content table tr td{
		width:400px;
		height:100px;
		border-radius:20px;
	} 
	#content table .input{
		width:350px;
		height:50px;
		border-radius:10px;
		padding:5px;
		text-align:center;
		ontline:none;
	} 
	*:focus{
		outline:none;
	} 
	#content table .loginbutton{
		width:350px;
		height:50px;
		border:0;
		background:skyblue;
		border-radius:5px;
	}
	#content div hr{
		margin:0 auto;
		
	}
	#content #textInfo a{
		text-height:3em;
		text-decoration:none;
	}#content #textInfo span{
		color:red;
	}
	
</style>
</head>
<body>
<c:if test="${not empty mDto || not empty admin}">
	<c:redirect url="/main/main.jsp" />
</c:if>
<c:if test="${not empty errorMsg}">
	<script>
	 	alert('${errorMsg}');
	</script>
</c:if>
<c:if test="${not empty joinMsg}">
	<script>
	 	alert('${joinMsg}');
	 	location.href = "${conPath}/joinView.do";
	</script>
</c:if>
<c:if test="${not empty Msg}">
	<script>
	 	alert('${Msg}');
	</script>
</c:if>
<jsp:include page="../main/header.jsp"/>
<div id="contentWrap">
	<div id="content">
		<form action="${conPath }/login.do" method="post">
			<table>
				<tr>
					<td>
						<input type="email" placeholder="이메일 주소 입력" required="required" name="mEmail" value="${mEmail}" class="input">
					</td>
				</tr>
				<tr>
					<td>
						<input type="password" placeholder="비밀번호 입력" required="required" name="mPw" class="input">
						<div id="pwMsg"></div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="로그인" class="loginbutton"> 
					</td>
				</tr>
			</table>
		</form>
		<div id="textInfo">
			<hr />
			<br>
			<a href="${conPath }/joinView.do">아직 계정이 없으신가요? <span>회원가입하기!</span></a><br>
			
		</div>
	</div>
</div>
</body>
</html>