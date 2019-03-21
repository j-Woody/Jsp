<%@page import="java.util.ArrayList"%>
<%@page import="com.tj.px.dto.ProjectDto"%>
<%@page import="com.tj.px.dao.ProjectDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
         <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  <c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
var pageNum;

$(document).ready(function(){
	var pageCnt = 3;
	pageNum = Number($('.pageNum').last().val());
	if(isNaN(pageNum)){
		pageNum	= 1;
	}
	if(pageNum <= 1){
		$('#lastLeft').attr('disabled','disabled');
		$('#leftImg').attr('src','${conPath}/mainImg/left_click.png');
	}
	
	function rollingPage(pNum){
		
			$.ajax({
				url : '${conPath}/pListView.do',
				type : 'get',
				dataType : 'html',
				data : "pageNum="+pNum,
				success : function(data){
					$('#forList').html(data);
					pageNum = Number($('.pageNum').last().val());
				},
				error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		       }
			});//ajax
		
	};
	$('#lastRight').click(function() {
		var pNum = pageNum+1;
		if(pNum >= pageCnt){
			pNum=pageCnt;
			$('#lastRight').attr('disabled','disabled');
			$('#lastLeft').attr('disabled',false);
			$('#leftImg').attr('src','${conPath }/mainImg/left.png');
			$('#rightImg').attr('src','${conPath }/mainImg/right_click.png');
		}else if(pNum>=0 && pNum <=pageCnt){
			$('#lastLeft').attr('disabled',false);
			$('#leftImg').attr('src','${conPath }/mainImg/left.png');
		}
		rollingPage(pNum);
	});
	
	$('#lastLeft').click(function() {
		var pNum = pageNum-1;
		if(pNum <= 1){
			$('#lastLeft').attr('disabled','disabled');
			$('#lastRight').attr('disabled',false);
			$('#rightImg').attr('src','${conPath }/mainImg/right.png');
			$('#leftImg').attr('src','${conPath }/mainImg/left_click.png');
			pNum=1;
		}else if(pNum>=0 && pNum<=pageCnt){
			$('#lastRight').attr('disabled',false);
			$('#rightImg').attr('src','${conPath }/mainImg/right.png');
		}
		rollingPage(pNum);
	});
	
});
</script>
<style>
    #list {
        width: 70%;
        height: auto;
        margin: 0 auto;
        overflow:hidden;
      
    } #list h1{
      cursor:pointer;
    }
        #list .listWrap {
            width: 95%;
            cursor: pointer;
            box-sizing: border-box;
            padding: 2%;
            margin:2%;
            overflow:hidden;
            
        }
            #list .listWrap .sumImg {
                width: 200px;
                height: 150px;
                text-align: center;
                float: left;
                
            }
                #list .listWrap .sumImg img {
                    width: 200px;
                    height: 150px;
                }
                #list .listWrap div:not(.sumImg) {
                    height: 36px;
                }
                    #list .listWrap div:not(.sumImg) a {
                        line-height: 35px;
                        margin: 0 0 0 10px;
                    }
        #list .listButton{
            float:right;
        }
        #list .listButton button{
            border: 0;
            background-color: white;
        }
        #list .listButton button img{
        	width: 30px;
        	height: 30px;
        }
        #lastRight{
        	margin-left: 20px;
        }
     	*:focus {
			outline:0px;
		}
  </style>
</head>
<body>
<jsp:include page="header.jsp"/>
<jsp:include page="banner.jsp"/>
<% ProjectDao pdao= ProjectDao.getInstance();
	ArrayList<ProjectDto> dto = pdao.hitView(1, 3);
	int i = 0;
%>
<c:set var="today" value="<%=new java.util.Date()%>"/>
    <div id="list">
    	<h1 onclick="">프로젝트</h1>
        <div class="listButton">
            <button id="lastLeft"><img alt=" > " src="${conPath }/mainImg/left.png" id="leftImg"></button><button id="lastRight"><img alt=" > " src="${conPath }/mainImg/right.png"id="rightImg"> </button>
        </div>
        <div id="forList">
        <!--  for로 처리 -->
	     	<c:forEach items="<%=dto %>" var="dto">
	     	<c:set var="today" value="<%=new java.util.Date()%>"/>
	     	<c:set var="date" value="<%=dto.get(i).getpDate() %>"></c:set>
	     	<% i++; %>
			<fmt:parseNumber var="nowTime" value="${today.time/(1000*60*60*24)}" integerOnly="true" ></fmt:parseNumber>
			<fmt:parseNumber var="pDate" value="${date.time/(1000*60*60*24)}" integerOnly="true" ></fmt:parseNumber>
					<div class="listWrap" onclick="location.href='${conPath }/pSeleteView.do?pNo=${dto.pNo}'">
			            <div class="sumImg">
			                <a><img src="${conPath }/upload/${dto.pImage }" alt="${dto.pImage }" /></a>
			            </div>
			            <div>
			                <a>프로젝트명 : ${dto.pTitle }</a>
			            </div>
			            <div>
			                <a>작성자 : ${dto.mEmail }</a>
			            </div>
			            <div>
			           	 <c:if test="${0 ge pDate-nowTime}">
			          	  	<a> 예정일 : 종료</a>
			        	 </c:if>
			       	 	 <c:if test="${0 lt pDate-nowTime}">
			            	<a> 예정일 : ${pDate-nowTime}일 남음</a><a>모금액 : ${dto.pNow }원</a>
			          	 </c:if>
			          	 </div>
			            <div>
			                <a>진행도 : ${ dto.pNow / dto.pPay *100}%</a>
			            </div>
			        </div>
			        <hr />
			</c:forEach>
	     <!-- 여기까지 -->
	     </div>
    </div>
</body>
</html>