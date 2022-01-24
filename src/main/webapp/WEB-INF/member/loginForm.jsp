<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>로그인</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/loginForm.css"/>
    <script src="/js/loginForm.js"></script>
</head>

<body id="loginBg">
	<div id="loginWrap">
        <div id="loginTit">PIT A PET</div>
        <c:if test="${loginIdFailed}">
	    	<script> alert('${exception}'); </script>
	 	</c:if>
	 	<c:if test="${loginPwFailed}">
	    	<script> alert('${exception}'); </script>
	 	</c:if>
        <form name="form" method="POST" action="<c:url value='/member/login'/>">
        	<div id="loginInner">
		        <input type="text" placeholder="아이디" id="loginId" name="memberId" />
		        <input type="password" placeholder="비밀번호" id="loginPwd" name="password" />
		        <input type="button" id="loginBtn" value="로그인" onClick="login()">
	        </div>
        </form>
        <div id="joinLinkWrap">아직 회원이 아니신가요? <a href="<c:url value='/member/register/form'/>" id="joinLink">회원가입</a></div>
    </div>
</body>
</html>