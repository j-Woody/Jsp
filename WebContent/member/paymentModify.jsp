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
		$('#selectPhone').change(function(){ 
			$("#selectPhone option:selected").each(function () { 
				if($(this).val()== '1'){ 
					 $("#pPhone1").val(''); 
					 $("#pPhone1").attr("readonly",true); //활성화 
					}else{ 
						$("#pPhone1").val($(this).text()); 
						$("#pPhone1").attr("readonly",false); //비활성화 
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
		margin:60px auto;
		border: 1px solid gray;
		border-radius: 5px;
	}
	#content table{
		width:80%;
		margin: 20% auto;
		text-align:center;
	}
	#content table tr td{
		margin: 40px;
		padding: 10px;
	}
	#content table tr td:nth-child(2n-1){
		width:35%;
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
	#content h1{
		text-align: center;
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
	.lo,.pPhone{
	width: 40px;
	}
</style>
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
<c:if test="${empty mDto}">
		<script>
		location.href = "${conPath}/loginView.do";
		</script>
</c:if>
	<c:if test="${not empty errorMsg}">
		<script>
			alert('${errorMsg}');
		</script>
	</c:if>
	<c:if test="${not empty Msg}">
		<script>
			alert('${Msg}');
			location.href = "${conPath}/main.do";
		</script>
	</c:if>
 <div id="content">
 <h1>결제 정보 변경</h1>
 	<form action="${conPath }/pModify.do" method="post">
            <table>
                <tr>
                    <td>
         	    카드번호
                    </td>
                    <td>
                        <input type="text" name="pc1" maxlength="4" class="lo"/>-
                        <input type="text" name="pc2" maxlength="4"class="lo"/>-
                        <input type="text" name="pc3" maxlength="4"class="lo"/>-
                        <input type="text" name="pc4" maxlength="4"class="lo"/>
                    </td>
                </tr>
                <tr>
                    <td>
      		       유효기간
                    </td>
                    <td>
                        <input type="text" name="pMonth" placeholder="월" maxlength="2"class="lo">
                        /<input type="text" name="pYear" placeholder="년도" maxlength="2"class="lo"/>
                    </td>
                </tr>
                 <tr>
                    <td>
      		       생년월일
                    </td>
                    <td>
                        <input type="text" name="pBirth" placeholder="생년월일" maxlength="6">
                      
                    </td>
                </tr>
                <tr>
                    <td>
		     	카드 비밀번호
                    </td>
					<td>
						<input type="text" name="pPw" maxlength="2"class="lo">**
					</td>
                </tr>
                <tr>
                    <td>
                       배송시 연락처
                    </td>
                    <td>
                        <select name="selectPhone" id="selectPhone">
                            <option value="010" selected>010</option>
                            <option value="1">직접입력</option>
                        </select>
                        <input type="text" name="pPhone1" id="pPhone1" readonly="true" maxlength="4" value="010"class="lo"/> - 
                        <input type="text" name="pPhone2" class="pPhone" maxlength="4"class="lo"/> - 
                        <input type="text" name="pPhone3" class="pPhone" maxlength="4"class="lo"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="카드수정" />
                        <input type="reset" value="다시작성" />
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>