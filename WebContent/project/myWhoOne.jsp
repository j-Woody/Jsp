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
var page = 1;
$(document).ready(function(){
	 $.ajax({
			url : '${conPath}/pMyWhoOne.do',
			type : 'post',
			dataType : 'html',
			data : "pageNum="+page,
			success : function(data){
				$('#myProject').html(data);
				pageNum = Number($('.pageNum').last().val());
			},
			error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	       }
		});//ajax
});
$(window).scroll(function() {
    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
      ++page
      $.ajax({
			url : '${conPath}/pMyWhoOne.do',
			type : 'post',
			dataType : 'html',
			data : "pageNum="+page,
			success : function(data){
				$('#myProject').append(data);
				pageNum = Number($('.pageNum').last().val());
			},
			error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	       }
		});//ajax
    }
});
</script>
<style>
    #myProject {
        width: 70%;
        height: auto;
        margin: 0 auto;
        overflow:hidden;
      
    }h1{
       text-align: center;
       margin: 50px auto;
    }
        #myProject .listWrap {
            width: 95%;
            cursor: pointer;
            box-sizing: border-box;
            padding: 2%;
            margin:2%;
            overflow:hidden;
            
        }
            #myProject .listWrap .sumImg {
                width: 200px;
                height: 150px;
                text-align: center;
                float: left;
                
            }
                #myProject .listWrap .sumImg img {
                    width: 200px;
                    height: 150px;
                }
                #myProject .listWrap div:not(.sumImg) {
                    height: 36px;
                }
                    #myProject .listWrap div:not(.sumImg) a {
                        line-height: 35px;
                        margin: 0 0 0 10px;
                    }
        #myProject .listButton{
            float:right;
        }
        #myProject .listButton button{
            border: 0;
            background-color: white;
        }
        #myProject .listButton button img{
        	width: 30px;
        	height: 30px;
        }
        #lastRight{
        	margin-left: 20px;
        }
     	*:focus {
			outline:0px;
		}a{
			text-decoration: none;
			color:black;
		}
  </style>
</head>
<body>
<c:if test="${not empty Msg }">
	<script>
		alert('${Msg}');
	</script>
</c:if>
<c:if test="${not empty errorMsg }">
	<script>
		alert('${errorMsg}');
	</script>
</c:if>
<jsp:include page="../main/header.jsp"></jsp:include>
	<h1>내가 후원한 프로젝트</h1>
	<div id="myProject">
	</div>
	<a style="display:scroll;position:fixed;bottom:10px;right:10px;" href="#">▲맨 위로</a> 

</body>
</html>