<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">	
	<style>
  
        .listWrap {
            width: 95%;
            cursor: pointer;
            box-sizing: border-box;
            padding: 2%;
            margin:2%;
            overflow:hidden;
            
        }
         .listWrap .sumImg {
                width: 200px;
                height: 150px;
                text-align: center;
                float: left;
                
            }
          .listWrap .sumImg img {
                    width: 200px;
                    height: 150px;
                }
         .listWrap div:not(.sumImg) {
                    height: 36px;
                }
         .listWrap div:not(.sumImg) a {
                        line-height: 35px;
                        margin: 0 0 0 10px;
                    }
     	*:focus {
			outline:0px;
		}
		.delete{
			margin: 0 auto;
			width: 40px;
		}
  </style>
</head>
<body>
<%System.out.println("get들어옴"); %>

	<c:if test="${empty error }">
			<c:forEach items="${list }" var="dto">
			<c:set var="today" value="<%=new java.util.Date()%>"/>
			<fmt:parseNumber var="nowTime" value="${today.time/(1000*60*60*24)}" integerOnly="true" ></fmt:parseNumber>
			<fmt:parseNumber var="pDate" value="${dto.pDate.time/(1000*60*60*24)}" integerOnly="true" ></fmt:parseNumber>
				<fmt:formatNumber var="payNow" value="${ dto.pNow / dto.pPay *100}" pattern="###,###.#"  />
					<div class="listWrap" onclick="location.href='${conPath }/pSeleteView.do?pNo=${dto.pNo}'">
			            <div class="sumImg">
			                <a><img src="${conPath }/upload/${dto.pImage}" alt="${dto.pImage }" /></a>
			            </div>
			            <div>
			                <a>프로젝트명 : ${dto.pTitle } </a>
			            </div>
			            <div>
			                <a>작성자 : ${dto.mEmail }</a>
			            </div>
			          <div>
			          <c:if test="${0 ge pDate-nowTime}">
			            	<a>예정일 : 종료</a>
			          </c:if>
			          <c:if test="${0 lt pDate-nowTime}">
			            	<a>예정일 : ${pDate-nowTime}남음</a>
			           </c:if>
			            	 <span>모금액 : ${dto.pNow }원</span><a>진행도 : ${payNow}%</a> 
			            </div>
			            <div>
			                <a>투자금액 : ${dto.pay}원 / 투자상품번호 : ${dto.gNo }</a>
			            </div>
			       
			        </div>
			         <div class="delete">
			            <c:if test="${0 lt pDate-nowTime}"><button onclick="location.href='${conPath }/whoOneDelete.do?gNo=${dto.gNo }'" >삭제하기</button></c:if>
			            </div>
			        <hr />
			</c:forEach>
		<input type="hidden" name="pageNum" class="pageNum" value="${pageNum }">
	</c:if>
</body>
</html>