<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>회원가입</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="/css/registerForm.css"/>
</head>

<body id="joinBg">
    <c:if test="${registerFailed}">
       <script> alert('${exception}'); </script>
    </c:if>
   
   <form name="form" method="POST" action="<c:url value='/member/register'/>">
   <c:choose>
   <c:when test="${registerFailed}">
   <div id="joinWrap">
        <div id="joinTit">PIT A PET</div>
        <div id="idPwdWrap">
            <input type="text" placeholder="아이디" id="joinId" name="id" value="${registerInfo.id}"/>
            <input type="password" placeholder="비밀번호" id="joinPwd" name ="password"/>
            <input type="password" placeholder="비밀번호 재확인" id="joinCheckPwd" name="checkPassword" />
        </div>

        <div id="privacyWrap">
            <input type="text" placeholder="이름" id="joinName" name="name" value="${registerInfo.name}"/>
            <div id="genderWrap">
                <div id="genderInner">
                   <select name = "gender">
                      <option id="joinMan" class="gender" value="M">남성</option>
                         <option id="joinWoman" class="gender" value="F">여성</option>
                   </select>
                </div>
            </div>
            <input type="text" placeholder="생년월일" id="joinBirth" name="birth" value="${registerInfo.birth}" />
            <input type="text" placeholder="전화번호 (숫자만 입력)" id="joinPhoneNumber" name="phone" value="${registerInfo.phone}"/>
            <input type="text" placeholder="이메일" id="joinEmail" name="email" value="${registerInfo.email}"/>
            <input type="text" placeholder="주소" id="joinAddress" name="address" value="${registerInfo.address}"/>
        </div>
       <button type="submit" id="joinBtn" onClick="return userCreate()">가입하기</button>
     </div>
     </c:when>
    
     <c:otherwise>
       <div id="joinWrap">
        <div id="joinTit">PIT A PET</div>
        <div id="idPwdWrap">
            <input type="text" placeholder="아이디" id="joinId" name="id" />
            <input type="password" placeholder="비밀번호" id="joinPwd" name ="password"/>
            <input type="password" placeholder="비밀번호 재확인" id="joinCheckPwd" name="checkPassword" />
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
       <button type="submit" id="joinBtn" onClick="return userCreate()">가입하기</button>
     </div>
     </c:otherwise>
    </c:choose>
   </form> 
</body>

<script>
   function userCreate() {
      if (form.password.value != form.checkPassword.value) {
         alert("비밀번호가 일치하지 않습니다.");
         form.checkPassword.focus();
         return false;
      }
      form.submit();
   }
   init();
</script>
</html>