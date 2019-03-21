<%@page import="com.tj.px.dto.ProjectDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tj.px.dao.ProjectDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
     <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script type="text/javascript" src="${conPath }/js/jquery.ulslide.js"></script>
		<script type="text/javascript">
		$(document).ready(function() {
			var winWidth = document.body.clientWidth;
			$(function() {
                $('#banner').ulslide({
                    statusbar: true,
                    direction:'f',
                    width:winWidth,
                    height: 500,
                    affect: 'slide',
                    axis: 'x',
                    navigator: '#banner_bt a',
                    duration: 300,
					autoslide: 3000
                });
            });
			
			 $(window).resize(function() {
				winWidth = document.body.clientWidth;
				 $(function() {
		                $('#banner').ulslide({
		                
		                    width:winWidth,
		                    affect: 'slide',
		                    axis: 'x',
		                    navigator: '#banner_bt a',
		                    duration: 300,
							autoslide: 3000
		                });
		            });
			});
		});
           
        </script>

        <style type="text/css">
		
			#bannerWrap{
			position:relative;
			width: 100%;
			margin-bottom: 10px;
			}
			#banner{
			
			margin: 0 auto;	
			}
			#banner img{
				height: 500px;
				width: 100%;
					
			}
            #banner_bt {
                list-style-type:none;
				position:absolute;
				bottom:5px; left:5px;
				overflow: hidden;
			
            }

            #banner_bt li {
                float: left; padding:3px 6px; margin-right:5px;
               
            }
            #banner_bt li img{
                width: 30px;
                height: 30px;
                border-radius: 40px;
                	margin: 0 auto;
            }

			a, a:hover, a:visited{text-decoration:none;}

        </style>
    </head>
    <body>
 
	<div id="bannerWrap">
	<% ProjectDao pDao = ProjectDao.getInstance();
	ArrayList<ProjectDto> pDtos = pDao.banner(1, 3);
	ArrayList<String> pImage = new ArrayList<String>();
	ArrayList<String> pTitle = new ArrayList<String>();
	ArrayList<Integer> pNo = new ArrayList<Integer>();
	for(int i = 0 ; i < pDtos.size();i++){
		pImage.add(pDtos.get(i).getpImage());
		pTitle.add(pDtos.get(i).getpTitle());
		pNo.add(pDtos.get(i).getpNo());
	}
	%>
        <div id="banner">
        <%
        int p = 0;
        for(int i=0;i<pImage.size();i++){
        		p=pNo.get(i);	%>
        	<li>
                <a href="${conPath }/pSeleteView.do?pNo=<%=p%>"><img src="${conPath }/upload/<%=pImage.get(i) %>" alt="<%=pImage.get(i)%>" title="<%=pTitle.get(i)%>" /></a>
            </li>
            
        <%}%>
        </div>
		<div id="banner_bt">
		<%
		int num=1;
		for(int i=0;i<pImage.size();i++){%>
        	<li>
                <li><a href="#<%=num++%>"><img alt="1" src="${conPath }/upload/<%=pImage.get(i)%>"></a></li>
            </li>
        <%}%>
            
        </div>

	</div>

    </body>
</html>