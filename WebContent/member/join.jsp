<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function(){
		$('input[name="mEmailChk"]').keyup(function(){
			if($('input[name="mEmail"]').val() != $('input[name="mEmailChk"]').val()){
				$('#idMsg').html('<b>이메일 주소가 일치하지 않습니다.</b>');
			}else{
				$('#idMsg').html('');
			}
			
			if($('input[name="mEmailChk"]').val() == ""){
				$('#idMsg').html('');
			}
		});
		
		$('input[name="mPwChk"]').keyup(function(){
			if($('input[name="mPw"]').val() !=
					$('input[name="mPwChk"]').val()){
				$('#pwMsg').html('<b>비밀번호 불일치</b>');
			}else{
				$('#pwMsg').html('');
			}
			if($('input[name="mPwChk"]').val() == ""){
				$('#pwMsg').html('');
			}
		});
		$('input[name="mPw"]').keyup(function(){
			if($('input[name="mPw"]').val() !=
					$('input[name="mPwChk"]').val()){
				$('#pwMsg').html('<b>비밀번호 불일치</b>');
			}else{
				$('#pwMsg').html('');
			}
			if($('input[name="mPwChk"]').val() == ""){
				$('#pwMsg').html('');
			}
		});
		$('input[name="mEmail"]').keyup(function(){
			var mEmail = $('input[name="mEmail"]').val();
		
			$.ajax({
				url: '${conPath}/eMailChk.do',
				type:'post',
				dataType:"html",
				data: "mEmail="+mEmail,
				success:function(data){
					$('#idMsg').html("<b>"+data+"</b>");
				}
			});
		});
		
		
	});
</script>
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
	#content table .joinbutton{
		width:350px;
		height:50px;
		border:0;
		background:skyblue;
		border-radius:5px;
	}
	*:focus{
		outline:none;
	} 
	#content .msgText{
		height:40px;
		margin:0;
	}#content .msgText div{
		height:40px;
		margin:0 auto;
		text-align:center;
	}
</style>
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
<div id=contentWrap>
<div id="content">
<form action="${conPath }/join.do" method="post">
	<table>
		<tr>
			<td>
				<input type="text" placeholder="이름 입력" required="required" name="mName" class="input" maxlength="20">
			</td>
		
		</tr>
		<tr>
			<td>
				<input type="email" placeholder="이메일 주소 입력" required="required" name="mEmail" class="input" maxlength="40"><br>
				
			</td>
		</tr>
		<tr>
			<td>
				<input type="email" placeholder="이메일 주소 확인" required="required" name="mEmailChk" class="input" maxlength="40">
			</td>
		</tr>
		<tr>
			<td class="msgText">
			<div id="idMsg"></div>
			</td>
		</tr>
		<tr>
			<td>
				<input type="password" placeholder="비밀번호 입력" required="required" name="mPw" class="input" maxlength="20">
			</td>
		</tr>
		<tr>
			<td>
				<input type="password" placeholder="비밀번호 확인" required="required" name="mPwChk" class="input" maxlength="20">
			</td>
		</tr>
		<tr>
			<td  class="msgText">
				<div id="pwMsg"></div>
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="회원가입" class="joinbutton"> 
			</td>
		</tr>
	</table>
</form>
<div id="joinInfo">
	<hr>
	<a href="${conPath }/loginView.do">이미 계정이 있다면 <span>로그인하기</span></a>
</div>
</div>
</div>
</body>
</html>