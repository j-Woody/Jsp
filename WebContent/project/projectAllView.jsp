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
		.paging {text-align: center;}
		h1{
			text-align: center;
		}
		a{
			text-decoration: nono;
		}
b {color:red;}
  </style>
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
<%System.out.println("get들어옴"); %>
<h1>프로젝트 모두보기</h1>
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
			                <a>프로젝트명 : ${dto.pTitle }</a>
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
			            	 <span>모금액 : ${dto.pNow }원</span>
			            </div>
			            <div>
			                <a>진행도 : ${ payNow}%</a>
			            </div>
			        </div>
			        <hr />
			</c:forEach>
		<input type="hidden" name="pageNum" class="pageNum" value="${pageNum }">
	</c:if>
	<div class="paging">
		<c:if test="${startPage > BLOCKSIZE }">
			<a href="${conPath }/pAllView.do?pageNum=${startPage-1}"> 이전 </a>
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i == pageNum }">
				[ <b>${i }</b> ]
			</c:if>
			<c:if test="${i != pageNum }">
				[ <a href="${conPath }/pAllView.do?pageNum=${i}">${i }</a> ]
			</c:if>
		</c:forEach>
		<c:if test="${endPage < pageCnt }">
			<a href="${conPath }/pAllView.do?pageNum=${endPage+1}"> 다음 </a>
		</c:if>
	</div>
	
</body>
</html>