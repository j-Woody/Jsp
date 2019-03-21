<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
<script>
$(document).ready(function() {
    $('#summernote').summernote({    
    	width:750,
        height: 600,                 // set editor height
        minHeight: null,             // set minimum height of editor
        maxHeight: null,             // set maximum height of editor
        focus: true                  // set focus to editable area after initializing summernote});
});
    function sumNail(input) {
        if (input.files && input.files[0]) {//파일있음?
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#getSum').attr('src', e.target.result); //src에 파일이름으로 변경
            }
            reader.readAsDataURL(input.files[0]);
        }else{
        	$('#getSum').attr('src','#');
        }
    }

    $("#sumNail").change(function() { //첨부진행함?
        sumNail(this);
    });
 
    function popUp() {//계좌등록 팝업
    	var interval=null;
    	var pop = null;
		pop = window.open('${conPath}/project/popUp.jsp','팝업창','width=430,height=250,resizable=no,scrollbars=no,fullscreen=no,left=800, top=300');
		interval = window.setInterval(function() {
	        try {
	            // 창이 꺼졌는지 판단
	            if( pop == null || pop.closed ) {
	                window.clearInterval(interval);
	                pop = null;
	                checkHidden();
	            }
	        } catch (e) { }
	    }, 500);
	};
	function checkHidden() {//계좌등록 value 확인
		var pAccount = $('#pAccount').val();
		if(pAccount.length>0){
			$('#checkbox').prop("checked",true);
		}else{
			$('#checkbox').prop("checked",false);
			$('#submit').prop("disabled",true);
		}
		if($('#checkbox').is(":checked") && $('#pPay').is(":checked")){
			$('#submit').prop("disabled",false);
		}
	}
	$('#checkbox').click(function () {//계좌등록 클릭시 진행
		var chk = $('#checkbox').is(":checked"); // 체크유무
		if(!chk){//체크일 경우
			$('#checkbox').prop("checked",false); // 체크해제
			document.getElementsByName("pAccount")[0].value = "";
			document.getElementsByName("pBankName")[0].value = "";
			document.getElementsByName("pAcName")[0].value = "";
		}else{
		$('#checkbox').prop("checked",false);
		popUp();
		}
	});
	function payPopUp() {
		var interval=null;
    	var pop = null;
		pop = window.open('${conPath}/project/payPopup.jsp','팝업창','width=430,height=250,resizable=no,scrollbars=no,fullscreen=no,left=800, top=300');
		interval = window.setInterval(function() {
	        try {
	            // 창이 꺼졌는지 판단
	            if( pop == null || pop.closed ) {
	                window.clearInterval(interval);
	                pop = null;
	                checkPay();
	            }
	        } catch (e) { }
	    }, 500);
	}
	$('#pPay').click(function () {
		var pay = $('#pPay').is(":checked");

		if(!pay){
			$('#pPay').prop("checked",false); // 체크해제
			document.getElementsByName("pPay")[0].value = "";
			document.getElementsByName("pDate")[0].value = "";
		}else{
			$('#pPay').prop("checked",false); // 체크해제
			payPopUp();
		}
	})
	function checkPay() {//계좌등록 value 확인
		var pPay = $('#pPay').val();
		if(pPay.length>0){
			$('#pPay').prop("checked",true);
		}else{
			$('#pPay').prop("checked",false);
			$('#submit').prop("disabled",true);
		}
		if($('#checkbox').is(":checked") && $('#pPay').is(":checked")){
			$('#submit').prop("disabled",false);
		}
	}
	$('#sumNail').change(function(){//파일체크
		var ext = $(this).val().split(".").pop().toLowerCase();//확장자만 뽑아내기
		if($.inArray(ext,["gif","jpg","jpeg","png"])== -1){
			alert("gif,jpg,jpeg,png 파일만 업로드 가능합니다.");
			$(this).val("");
			return;
		}
		var size = this.files[0].size;
		var max = 1024*1024*10;
		if(size > max){
			alert("10Mb를 초과할 수 없습니다.");
			$(this).val("");
			return;
		}
		
	});
	
	
});
</script>
   <style>
     #container{
         text-align:center;
     }
     #container .subject{
         width:600px;
     }
 	 #container .form-group{
 	 	width: 800px;
 	 	margin: 0 auto;
 
 	 	
 	 }
 	  
 	 #container .bf{
 	 	height : 20px;
 	 }
   	#container .submit{

   	margin: 0 auto;
   	}
   	#container .submit .button{

   	margin: 0 auto;
   	}
    #file{
    	width: 400px;
    	margin: 0 auto;
    }
    #file input{
    	
    	margin: 10px auto;
    }
    #file .sumNail{
    	border: 1px solid black;
    	width:400px;
    	height:100px;
    }
    </style>
</head>
<body>
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
    <div id="container">
        <h1 class="page-header">프로젝트 작성하기</h1>
        <hr />
        <form action="${conPath }/pInsert.do" class="form-horizontal" enctype="multipart/form-data" method="post" >
        <input type="hidden" name="pAccount" value="" id="pAccount">
        <input type="hidden" name="pBankName" value="" >
        <input type="hidden" name="pAcName" value="" >
     
            <div class="form-group">
            <select name="pCategory">
            	<option value="카테고리">카테고리</option>
            	<option value="도서">도서</option>
            	<option value="공예">공예</option>
            	<option value="사진">사진</option>
            	<option value="게임">게임</option>
            	<option value="기타">기타</option>
            </select>
            <input class="subject" type="text" name="pTitle" placeholder="프로젝트 명">
            
            <div class="bf">
            </div>
             <div class="col-sm-10">
                    <textarea name="pContent" id="summernote" class="summernote"></textarea>
                 
              </div>
            </div>
              <div id="file">
              	<table>
	              	<tr>
		              	<td>
		              		<label>썸네일</label><br>
		              		<span>프로젝트 썸네일로 사용됩니다.</span><br>
		              		<span>사이즈 : 00 x 00</span>
		              	<td>
		              	<td rowspan="2" class="sumNail">
		              		<img src="" alt="sumNail" width="200" height="200" id="getSum"/>
		              	</td>
            	  	</tr>
	              	<tr>
		              	<td>
		              		<input type="file" id="sumNail" name="sumNail" required="required" >
		              	<td>
	              	</tr>
              	</table>
              	<div id="checkTerms">
              		<table>
              			<tr>
              				<td colspan="2">
			              		<input type="checkbox" id="checkbox"><label for="checkbox">입금 계좌 등록</label><br>
			              	</td>
			            </tr>
			            <tr>
              				<td rowspan="2">
			              		<input type="checkbox" id="pPay"><label for="pPay">목표 금액</label>
			              	</td>
			              	<td>
			              	<input type="text" name="pPay" readonly="readonly" value="">
			              	<input type="text" name="pDate" readonly="readonly" value="">
			              	</td>
			            </tr>
              		</table>
              	</div>
              </div>
            <div class="submit">
                    <input type="submit" class="btn btn-default" id="submit" disabled="disabled" value="save">
                     <input type="button" onclick="history.go(-1)" class="btn btn-default" value="back">
            </div>

        </form>
    </div>
</body>
</html>