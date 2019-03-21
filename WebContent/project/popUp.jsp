<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계좌등록</title>
<script>
	function setParams() {
		var account = document.getElementsByName("Account")[0].value;
		var bankName = document.getElementsByName("BankName")[0].value;
		var acName =document.getElementsByName("AcName")[0].value;
		if(!account ==''&&!bankName==''&&!acName==''){
			opener.document.getElementsByName("pAccount")[0].value = account;
			opener.document.getElementsByName("pBankName")[0].value = bankName;
			opener.document.getElementsByName("pAcName")[0].value = acName;
			window.close();
		}else{
			alert('입력을 확인해 주세요');	
		}
	}
</script>
<style>
	#popup{
	
		width: 400px;
		height: 200px;
		
	}
	#popup table{
		width: 350px;
		margin: 0 auto;
		text-align: center;
		
	}
	
</style>
</head>
<body>
  <div id ="popup">
            	<table>
            		<tr>
            			<th>계좌번호</th>
            			<td>
	            			<input type="text" name="Account">
	            		</td>
	            	</tr>
	            	<tr>
	            		<th>은행명</th>
            			<td>
	            			<input type="text" name="BankName" >
	            		</td>
	            	</tr>
	            	<tr>
	            		<th>계좌주</th>
            			<td>
	            			<input type="text" name="AcName" >
	            		</td>
            		</tr>
            		<tr>
            			<td colspan="2">
            				<button onclick="setParams();">저장</button>
            				<button onclick="window.close();">닫기</button>
            			</td>
            		</tr>
            	</table>
            </div>
</body>
</html>