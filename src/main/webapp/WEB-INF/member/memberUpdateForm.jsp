<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>내 정보</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="/css/header.css" />
	<link rel="stylesheet" href="/css/footer.css" />
	<link rel="stylesheet" href="/css/memberUpdateForm.css" />
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="/js/memberUpdateForm.js"></script>
</head>

<body>
	<%@include file="../components/header.jsp"%>

	<div id="pageWrap">
		<c:if test="${updateFailed}">
			<script> alert('${exception}'); </script>
		</c:if>
		<div id="pageTit">내 정보</div>
		<form name="form" method="POST"
			action="<c:url value='/member/updateMember'/>" onsubmit="return update()">
			<table>
				<tr>
					<td id="tableTit">내 정보</td>
					<td id="tableTit"></td>
				</tr>
				
				<tr>
					<td id="tdTit">이름</td>
					<td><input type="text" id="textDesign" value="${memberInfo.name}" disabled /></td>
				</tr>
				
				<tr>
					<td id="tdTit">생년월일</td>
					<td><input type="text" id="textDesign" value="${memberInfo.birth}" disabled /></td>
				</tr>

				<tr>
					<td id="tdTit">기존 비밀번호</td>
					<td><input type="password" name="oldPassword"
						placeholder="기존 비밀번호를 입력하세요." id="textDesign" /></td>
				</tr>

				<tr>
					<td id="tdTit">새 비밀번호</td>
					<td><input type="password" name="newPassword"
						placeholder="새 비밀번호를 입력하세요." id="textDesign" /></td>
				</tr>

				<tr>
					<td id="tdTit">이메일</td>
					<td><input type="text" name="email"
						value="${memberInfo.email}" id="textDesign" /></td>
				</tr>

				<tr>
					<td id="tdTit">전화번호</td>
					<td><input type="text" name="phone"
						value="${memberInfo.phone}" id="textDesign" /></td>
				</tr>

				<tr>
					<td id="tdTit">주소</td>
					<td><input type="text" name="address"
						value="${memberInfo.address}" id="textDesign" /></td>
				</tr>
			</table>

			<div id="btnWrap">
				<button id="myInfoSaveBtn">저장</button>
			</div>
		</form>
	</div>

	<%@include file="../components/footer.jsp" %>
</body>
</html>