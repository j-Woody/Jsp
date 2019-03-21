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
<script src="${conPath }/js/address.js"></script>
<script>
$(document).ready(function(){
	$('#selectPhone').change(function(){
		$('#selectPhone option:selected').each(function(){
			if($(this).val() == '1'){
				$('#mPhone1').val('');
				$('#mPhone1').attr("readonly",false);//활성화
			}else{
				$('#mPhone1').val($(this).text());
				$('#mPhone1').attr("readonly",true);// 비활성화
			}
		});
	});
});

</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>
    <style>
        *{
            padding:0;margin:0;
        }
        
        #content table{
            width:500px;
            height:600px;
            border:1px solid red;
            margin:20px auto;
        }
            #content table tr td {
                border: 1px solid red;
            }
        #content table tr td:nth-child(2n-1){
            text-align:center;
        }
        input[type=number]::-webkit-outer-spin-button, input[type=number]::-webkit-inner-spin-button{
            -webkit-appearance:none;
            margin:0;
        }
        input[type=number]{
            width:40px;
            text-align:center;
        }
        #content table tr td .fileNotice{
            color:#808080;
            font-size:0.7em;
        }
		#selectPhone{
			height:30px;
		}
		#sample6_address{
			width:250px;
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
        <form action="${conPath }/mModify.do" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>
                        이름
                    </td>
                    <td>
                        <input type="text" name="mName" />
                    </td>
                </tr>
                <tr>
                    <td>
                        프로필사진
                    </td>
                    <td>
                        <input type="file" name="mImage" /><br /><span class="fileNotice">사이즈는 200px,세로 250px<br /> 10mb 미만의 jpg,png파일을 지원합니다.</span>
                    </td>
                </tr>
                <tr>
                    <td>
		     주소
                    </td>
					<td>
						<input type="text" name="sample6_postcode" id="sample6_postcode" disabled="true" placeholder="우편번호">
						<input type="button"  onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
						<input type="text" name="sample6_address" id="sample6_address" disabled="true" placeholder="주소"><br>
						<input type="text" name="sample6_detailAddress" id="sample6_detailAddress" placeholder="상세주소">
						<input type="text" name="sample6_extraAddress"id="sample6_extraAddress" disabled="true" placeholder="참고항목">
					</td>
                </tr>
                <tr>
                    <td>
                        소개
                    </td>
                    <td>
                        <textarea cols="30" rows="5" style="resize:none" name = "mSelf"></textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        전화번호
                    </td>
                    <td>
                        <select name="selectPhone" id="selectPhone">
                           
                            <option value="010" selected>010</option>
                            <option value="1">직접입력</option>
                        </select>
                        <input type="number" name="mPhone1" id="mPhone1" readonly="readonly" value="010"/> - 
                        <input type="number" name="mPhone2" class="mPhone" /> - 
                        <input type="number" name="mPhone3" class="mPhone" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="수정" />
                        <input type="reset" value="다시작성" />
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>