<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	$('document').ready(function(){
		
		$('#story').click(function(){
				$('#pContent').css('display',"block");
				$('#paybackNotice').css('display','none');
				$('#settingPay').css('display','none');		
		});
	
		$('#payback').click(function(){
				$('#pContent').css('display',"none");
				$('#paybackNotice').css('display','block');
				$('#settingPay').css('display','none');		
		});
		$('#setPay').click(function(){
			$('#pContent').css('display',"none");
			$('#paybackNotice').css('display','none');
			$('#settingPay').css('display','block');		
			
	});
	
		
	});
</script>
<style>
	#wrap{
		width:80%;
		
		margin:0 auto;
		text-align:center;
	}
	#img{
	width:100%;
		height:auto;
		margin:0 auto;
	}
	 #title,#takePart,#nav,#date,#pContent,#paybackNotice{
		width:80%;
		height:auto;
		margin:0 auto;
		
	}
	#title b{
		font-size:2em;
		
	}
	#img{
		overflow:hidden;
	}
	#img img{
	width:100%;
	height:auto;
	}
	.caregory{
		float:left;
		margin-left:10%;
	}
	#date tr td{
		width:30%;
		margin:0 auto;
		
	}
	#nav table{
		width:80%;
		margin: 0 auto;
	}
	#nav table tr td{
		width:33%;
	
	}
	#nav button{
		background-color:lightgreen;
		border:0;
		outline:none;
		width:80%;
	}
	#takePart button{
		background-color:skyblue;
		border:0;
		width:90%;
		height:70px;
		margin:20px;
		outline:none;
		border-radius:5px;
		color:white;
	}
	#pContent{
		margin-top:50px;
		margin-bottom:10px;
	}
	#paybackNotice,#settingPay{
		display:none;
	}
	hr{
		margin:0 auto;
	}
	#paybackNotice{
		margin:20px auto;
	}
	#settingPay{
		width: 100%;
	}
	#settingPay table{
		width: 500px;
		margin: 0 auto;
	}
</style>
</head>
<body>
<c:if test="${not empty Msg }">
	<script>
		alert('${Msg}');
	</script>
</c:if>
<jsp:include page="../main/header.jsp"/>
<div id="wrap">
	<div id="img">
		<div>
			<img alt="${pDto.pImage}" src="${conPath }/upload/${pDto.pImage}">
		</div>
		<div >
			<button class="caregory">${pDto.pCategory}</button>
		</div>
	</div>
	<div id="title">
		<b>${pDto.pTitle }</b><span>(${pDto.mEmail })</span>		
	</div>
	<table id="date">
		<tr>
			<td>
				모금액
			</td>
			<td>
				남은기간
			</td>
			<td>
				후원자
			</td>
		</tr>
		<tr>
			<td>
				<fmt:formatNumber value="${pDto.pNow }" type="number" pattern="##,###" var="pNow"></fmt:formatNumber>
				<fmt:formatNumber value="${pDto.pNow/ pDto.pPay*100}" type="number" pattern="#,###.#" var="payP"/>
				<b>${pNow} 원</b>  <b>(${payP}%)</b>
			</td>
			<td>
				<c:set var="today" value="<%=new java.util.Date()%>"/>
				<fmt:parseNumber var="nowTime" value="${today.time/(1000*60*60*24)}" integerOnly="true" ></fmt:parseNumber>
				<fmt:parseNumber var="pDate" value="${pDto.pDate.time/(1000*60*60*24)}" integerOnly="true" ></fmt:parseNumber>
				<c:if test="${0 lt pDate-nowTime}">
					<b>${pDate-nowTime}</b>일
				</c:if>
				<c:if test="${0 ge pDate-nowTime}">
			       	<b>종료</b>
			    </c:if>
			</td>
			<td>
			<b>${pDto.pCnt}</b>명
			</td>
		</tr>
	</table>
	<div id="takePart">
	<c:if test="${empty gDto && 0 lt  pDate-nowTime && pDto.mEmail ne mEmail}">
		<button onclick="location.href='${conPath}/pWHoView.do?pNo=${pDto.pNo}'">이 프로젝트 펀딩하기</button>
	</c:if>
	<c:if test="${0 ge pDate-nowTime}">
		<button >프로젝트 펀딩종료</button>
	 </c:if>
	<c:if test="${not empty gDto && pDto.mEmail eq mEmail }">
		<button>펀딩 금액은 금액설정에서</button>
	</c:if>
	<c:if test="${not empty gDto && pDto.mEmail ne mEmail }">
		<button onclick="location.href='${conPath}/pWHoView.do?pNo=${pDto.pNo}'">이 프로젝트 펀딩하기</button>
	</c:if>
	</div>
	<div id="nav">
		<table>
			<tr>
				<td>
					<button id="story">스토리</button>
				</td>
				<td>
					<c:if test="${pDto.mEmail eq sessionScope.mEmail }">
						<button id="setPay">금액설정</button>
					</c:if>
				</td>
				<td>
					<button id="payback">환불문의</button>
				</td>
			</tr>
		</table>
	</div>
	<hr width="80%">
	<div id="pContent">
	<pre style="white-space: pre-wrap;">${pDto.pContent}</pre>
	</div>
	<div id="paybackNotice">
	<div>
		<pre style="white-space: pre-wrap;">이 프로젝트의 환불 및 교환 정책
⋅ 프로젝트 마감일 후에는 즉시 제작 및 실행에 착수하는 프로젝트 특성상 단순 변심에 의한 후원금 환불이 불가능합니다.
⋅ 쿠션 및 인형은 제작 진행되어 박음질, 무늬 등이 조금씩 차이가 있을 수 있어요.
⋅ 불량이 의심이 될 경우 문의 메일로 사진, 후원번호와 함께 문의 해주세요.
⋅ 예상 전달일로부터 15일 이상 전달 지연 시 수수료 포함 후 환불 가능합니다.
⋅ 파손, 불량품 수령시 10일 이내로 교환이 가능합니다. 교환 시 발생하는 배송비는 진행자 부담입니다.
⋅ 후원자가 배송지를 잘못 기재하거나 진행자에게 사전 고지 없이 배송지를 수정하여 배송사고가 발생할 경우 진행자는 최대 1번까지 재발송 해 드립니다. 
(배송비 부담은 후원자에게 있습니다.)</pre>
	</div>
	<div>
		<button>창작자에게 문의하기</button>
	</div>
	</div>
<div id="settingPay">
	<form action="${conPath }/setPay.do">
	<input type="hidden" name="pNo" value="${pDto.pNo }">
		<table>
		<c:if test="${not empty sDto }">
			<c:forEach begin="0" end="${fn:length(sDto)}" items="${sDto }" var="s">
			<tr>
				<th>${s.pName }</th>
				<td>
					${s.pay }
				</td>
				<td>
					${s.pexp }
				</td>
			</tr>
			</c:forEach>
		</c:if>
		<c:if test="${3 gt fn:length(sDto) }">
				<tr>
					<td>
					<input type="text" name="pName" placeholder="후원명">
					</td>
				</tr>
				<tr>
					<td>
					<input type="number" name="pay" placeholder="후원금액">
					</td>
				</tr>
				<tr>
					<td>
					<textarea rows="5" cols="20" name="pexp" placeholder="설명"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit"value="등록">
					</td>
				</tr>
		</c:if>
		</table>
		</form>
	</div>
</div>
</body>
</html>