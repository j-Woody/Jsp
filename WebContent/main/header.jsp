<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function openMenu() {
    document.getElementById("menu").style.width="250px";
    document.getElementById("profile").style.width="0";
};

function closeMenu() {
    document.getElementById("menu").style.width="0";
};
function openProfile() {
    document.getElementById("profile").style.width="250px";
    document.getElementById("menu").style.width="0";
};

function closeProfile() {
    document.getElementById("profile").style.width="0";
};
</script>
    <style>
        *{
            margin:0;
            padding:0;
        }
        header{
            height:60px;
            border-bottom:1px solid gray;
            text-align:center;
            background-color:white;
        }
        header ul{
            overflow:hidden;
        }
    
            header ul li:nth-child(1) {
              
                float: left;
                line-height: 60px;
             
                
            }
            header ul li:nth-child(2) {
                float: right;
                line-height: 60px;
                
            }
          header ul li:nth-child(3) {
                position:absolute;
                line-height: 60px;
                margin:0 48%;
            }
          
            header ul li{
                   list-style:none;
                margin:10px;
             
             }
             header ul li a {
                    text-decoration:none;
             }
        #menu{
           height: 100%;
		    width: 0;
		    position: fixed;
		    z-index: 1;
		    top: 0;
		    left: 0;
		    background-color: #111;
		    overflow-x: hidden;
		    transition: 0.5s;
		    padding-top: 30px;
		    
        }
  		#menu h1,#profile h1{
  			color:white;
  			text-align: center;
  			margin-bottom: 20px;
  		}
        
            #menu a:hover {
                   color:#b8b8b8;
            }
            #menu a {
                padding: 8px 0 8px 0;
			    text-decoration: none;
			    font-size: 25px;
			    color: #818181;
		 	    display: block;
			    transition: 0.3s;
			    text-align: center;
            }
          
        #menu #closeMenu{
            position:absolute;
            top:0;
            right:0px;
            font-size:3em;
            
        }
        #profile {
          height: 100%;
		    width: 0;
		    position: fixed;
		    z-index: 1;
		    top: 0;
		    right: 0;
		    background-color: #111;
		    overflow-x: hidden;
		    transition: 0.5s;
		    padding-top: 30px;

          
        }
            #profile a:hover {
                color: #b8b8b8;
            }

            #profile a {
                padding: 8px 8px 8px 32px;
			    text-decoration: none;
			    font-size: 25px;
			    color: #818181;
			    display: block;
			    transition: 0.3s;


            }
			#profile p {
                padding: 8px 8px 8px 32px;
			    text-decoration: none;
			    font-size: 3em;
			    color: white;
			    display: block;
			    transition: 0.3s;


            }
            #profile #closeProfile {
                position: absolute;
                top: 0;
                lefr: 0;
			    font-size: 3em;
			    padding: 5px;
			    
            }
            .line{
            	margin: 20px auto;
            	
            }
            #logo{
            	height:50px;
            }
    </style>
</head>
<body>
    <header>
        <ul>
            <li>
                <a href="#" ><img src="${conPath }/mainImg/menu.png" alt="메뉴" height="30" id="openMenu" onclick="openMenu();" /></a>
            </li>
            <c:if test="${not empty mDto}">
		            <li>
		                <a href="#" ><img src="${conPath }/memberImg/${mDto.mImage}" alt="프로필사진" height="30" id="openProfile" class="pimg" onclick="openProfile();"/></a>
		            </li>
        	</c:if>
        	 <c:if test="${empty mDto}">
            	 <li>
            	 	<a href="${conPath}/loginView.do" ><img src="${conPath }/mainImg/login.png" alt="로그인" height="30" id="openProfile" onclick="openProfile();"/></a>
	            </li>
            </c:if>
	            <li>
	                <a href="${conPath }/main.do"><img src="${conPath}/mainImg/main.png" alt="logo" height="30" id="logo" /></a>
	            </li>
        </ul>
    </header>
    <div id="menu">
        <h1>메뉴</h1>
        <hr />
        <a href="#" id="closeMenu" onclick="closeMenu();">x</a>
       
        <a href="${conPath }/bList.do">자유게시판</a>
        <hr class="line" width="70%"/>
        <h1>프로젝트</h1>
        <hr class="line" width="70%"/>
        <a href="${conPath }/pAllView.do">전체보기</a>
    </div>
    <div id="profile">
   <c:if test="${not empty mDto }">
        <h1>프로필</h1>
        <hr />
        <p><img alt="${mDto.mImage }" src="${conPath }/memberImg/${mDto.mImage}" height="50">${mDto.mName}</p>
        <a href="#" id="closeProfile" onclick="closeProfile();">x</a>
      
        <hr class="line" width="70%"/>
       
        <a href="${conPath }/project/myProject.jsp">내 프로젝트 보기</a>
        <a href="${conPath }/pInsertView.do">프로젝트 올리기</a>
        <a href="${conPath }/project/myWhoOne.jsp">내 후원현황</a>
        <hr class="line" width="70%" />
        <a href="${conPath }/mModifyView.do">프로필 설정</a>
        <c:if test="${empty pcDto }">
        <a href="${conPath }/paymentView.do">지불 정보 설정</a>
        </c:if>
        <c:if test="${not empty pcDto }">
            <a href="${conPath }/pModifyView.do">지불 정보 변경</a>
        </c:if>
        <a href="${conPath }/logout.do">로그아웃</a>
    </c:if>
    </div>

</body>
</html>