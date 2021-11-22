<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>회원가입</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/registerForm.css"/>
    
    <style>
	    #logo {
		    z-index: 1;
		   	margin-left: 14px;
		}
	</style>
</head>

<body id="joinBg">
	<form name="form" method="POST" action="<c:url value='/member/register'/>">
	<div id="joinWrap">
        <div id="joinTit">PIT A PET</div>
        <div id="idPwdWrap">
            <input type="text" placeholder="아이디" id="joinId" name="id" />
            <input type="text" placeholder="비밀번호" id="joinPwd" name ="password"/>
            <input type="text" placeholder="비밀번호 재확인" id="joinCheckPwd" name="checkPassword" />
        </div>

        <div id="privacyWrap">
            <input type="text" placeholder="이름" id="joinName" name="name" />
            <div id="genderWrap">
                <div id="genderInner">
                	<select name = "gender">
                		<option id="joinMan" class="gender" value="M">남성</option>
                   		<option id="joinWoman" class="gender" value="F">여성</option>
                	</select>
                </div>
            </div>
            <input type="text" placeholder="생년월일" id="joinBirth" name="birth" />
            <input type="text" placeholder="전화번호 (숫자만 입력)" id="joinPhoneNumber" name="phone" />
            <input type="text" placeholder="이메일" id="joinEmail" name="email" />
            <input type="text" placeholder="주소" id="joinAddress" name="address" />
        </div>

        <button type="submit" id="joinBtn" onClick="userCreate()">가입하기</button>
     </div>
   </form> 
</body>
<script src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-1b93190375e9ccc259df3a57c1abc0e64599724ae30d7ea4c6877eb615f89387.js"></script>

<script id="rendered-js" >
    var gender = document.getElementsByClassName("gender");

    function handleClick(event) {
        console.log(event.target);

        console.log(event.target.classList);

        if (event.target.classList[1] === "clicked") {
            event.target.classList.remove("clicked");
        } else {
            for (var i = 0; i < gender.length; i++) {if (window.CP.shouldStopExecution(0)) break;
                gender[i].classList.remove("clicked");
            }window.CP.exitedLoop(0);
            event.target.classList.add("clicked");
        }
    }

    function init() {
        for (var i = 0; i < gender.length; i++) {if (window.CP.shouldStopExecution(1)) break;
            gender[i].addEventListener("click", handleClick);
        }window.CP.exitedLoop(1);
    }
    
    function userCreate() {
		if (form.password.value != form.checkPassword.value) {
			alert("비밀번호가 일치하지 않습니다.");
			form.name.focus();
			return false;
		}
		form.submit();
    }
    
    init();
</script>
</html>
