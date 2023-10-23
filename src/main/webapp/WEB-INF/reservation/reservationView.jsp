<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>예약 상세 정보</title>
	<link rel="stylesheet" href="/css/header.css" />
	<link rel="stylesheet" href="/css/footer.css" />
	<link rel="stylesheet" href="/css/reservationView.css" />
</head>

<body>
	<%@include file="../components/header.jsp"%>
	
	<div id="pageWrap">
		<div id="pageTit">예약 상세 정보</div>
		
		<div id="pageSubTit"><img src="/images/sole.svg" id="soleImg" /> 반려동물 돌보미 정보</div>
		<table id="sitterInfoWrap">
			<tr id="tableTit">
				<th>돌보미 이름</th>
				<th>돌보미 연락처</th>
				<th>돌보미 주소</th>
			</tr>
			<tr>
				<td>${reservationInfo.sitter.sitter.name}</td>
				<td>${reservationInfo.sitter.sitter.phone}</td>
				<td>${reservationInfo.sitter.sitter.address}</td>
			</tr>
		</table>
			
		<div id="pageSubTit"><img src="/images/sole.svg" id="soleImg"/> 신청 정보</div>
		<table id="sitterInfoWrap">
			<tr id="tableTit">
				<th>완료 상태</th>
				<th>돌봄 기간</th>
				<th>금액</th>
				<th>유의사항</th>
			</tr>
			<tr>
				<c:if test="${reservationInfo.status eq 'X'}">
				<td>예약완료</td>
				</c:if>
				<c:if test="${reservationInfo.status eq 'Y'}">
				<td>돌봄진행</td>
				</c:if>
				<c:if test="${reservationInfo.status eq 'Z'}">
				<td>돌봄완료</td>
				</c:if>
				<td>${fn:split(reservationInfo.startDate, ' ')[0]} ~ ${fn:split(reservationInfo.endDate, ' ')[0]}</td>
				<td>${reservationInfo.totalPrice}</td>
				<td>${reservationInfo.notes}</td>
			</tr>
		</table>
		
		<div id="pageSubTit"><img src="/images/sole.svg" id="soleImg"/> 반려동물 정보</div>
		<table>
			<tr id="tableTit">
				<th>반려동물 이름</th>
				<th>받을 서비스</th>
			</tr>
			<c:forEach var="careList" items="${reservationInfo.careList}">
			<tr>
				<td>${careList.carePet.name}</td>
				<td>${careList.serviceInfo.title}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>