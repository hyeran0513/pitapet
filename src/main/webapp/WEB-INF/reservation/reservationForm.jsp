<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>예약하기</title>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="/css/header.css" />
	<link rel="stylesheet" href="/css/footer.css" />
	<link rel="stylesheet" href="/css/reservationForm.css" />
	<script src="/js/reservationForm.js"></script>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" />
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script>
		var ableService = JSON.parse('${ableService}');
		var userPetsJson = JSON.parse('${userPetsJson}');
	</script>
</head>

<body id="pageBg">
	<%@include file="../components/header.jsp"%>

	<form name="form" method="POST" action="<c:url value='/reservation/reserve'/>" onsubmit="return reservation()">
	<div id="reservationFormWrap">
		<input type="hidden" name="fromDate" value="${param.fromDate}" />
		<input type="hidden" name="toDate" value="${param.toDate}" />
		<input type="hidden" name="totalPrice" value="${param.totalPrice}" />
		<input type="hidden" name="sitterId" value="${petsitterInfo.sitter.id}" />
		
		<div id="targetName">${petsitterInfo.sitter.id} 반려동물 돌보미</div>
		<div id="carePrice"><img src="/images/dollarImg.svg" id="dollarImg" />이용 요금 ${param.totalPrice}원</div>
		<div id="subPageTit">반려동물 선택하기</div>
		<div id="petPickWrap">
			<c:if test="${empty userPetsMap}">
				돌봄 가능한 반려동물이 없습니다.<br/>
			</c:if>
			<c:forEach var="pet" items="${userPetsMap}">
				<div id="petCheckBoxWrap">
					<c:if test="${pet.value.petImage eq null}">
						<img src="/images/petCheckImg.svg" id="petCheckImg" />
					</c:if>
					<c:if test="${pet.value.petImage ne null}">
						<img src="${pet.value.petImage}" id="petCheckImg" />
					</c:if>
					<div id="petInfoName">${pet.value.name}</div>
					<div id="petInfoBirth">${pet.value.birth}살</div>
					<div id="tagCheckWrap">
						<div id="petInfoKind">#${pet.value.kind.smallCategory}</div>
						<input type="checkbox" class="checkbox" name="pet" value="${pet.key}" />
					</div>
				</div>
			</c:forEach>
		</div>
		<div id="subPageTit">제공받을 서비스</div>
		<div id="petSitterService"></div>
		<div id="subPageTit">요구사항</div>
		<textarea placeholder="추가적인 요구사항을 입력하세요." id="reviewText" name="cautionText"></textarea>
		<button id="reservationBtn">예약하기</button>
	</div>
	</form>

	<%@include file="../components/footer.jsp" %>
</body>
</html>
