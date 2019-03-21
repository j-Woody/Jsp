<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>금액설정</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	function setParams() {
		var pay = document.getElementsByName("pay")[0].value;
		var pDate = document.getElementsByName("pDate")[0].value;
		if(!pay=="" && !pDate==""){
			opener.document.getElementsByName("pPay")[0].value = pay;
			opener.document.getElementsByName("pDate")[0].value = pDate;
			window.close();
		}else{
			alert('입력을 확인해 주세요');	
		}
	}
	 $( function() {
		    $( "#datepicker" ).datepicker({
		    	dateFormat:'yy-mm-dd',
		    	showOtherMonths:true,
		    	showMonthAfterYear:true,
		    	changeYear: true,
		    	changeMonth: true,
		    	minDate:"+1M",
		    	maxDate:"+1Y",
		    	monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
		    	monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		    	dayNamesMin: ['일','월','화','수','목','금','토'],
		    	dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일']
		    });
		  });
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
	
	input[type="number"]::-webkit-outer-spin-button,
	input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}
</style>
</head>
<body>
  <div id ="popup">
            	<table>
            		<tr>
            			<th>금액입력</th>
            			<td>
	            			<input type="number" name="pay" placeholder="숫자만입력">
	            		</td>
	            	</tr>
	            	<tr>
	            		<th>마감일	</th>
	            		<td>
	            		<input type="text" name="pDate"id="datepicker" readonly="readonly">
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