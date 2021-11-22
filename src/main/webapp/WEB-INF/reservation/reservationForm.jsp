<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>예약하기</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Righteous&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="/css/header.css" />
<link rel="stylesheet" href="/css/footer.css" />
<script src="/js/reservationForm.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

<style>
@charset "UTF-8";

#headerWrap {
    border-bottom: 1px solid rgba(150, 150, 150, 0.2);
}

#reservationFormWrap {
    margin: 100px auto 0px auto;
    width: 1194px;
}

#targetName {
    font-size: 24px;
}

#reservationWrap {
    margin: 0 auto;
    width: 1194px;
}

#subPageTit {
    margin: 80px 0px 40px 0px;
    font-size: 18px;
}

#periodWrap {
    display: flex;
    justify-content: space-between;
}

#ableTimeWrap {
    padding: 20px 0px;
    width: 630px;
    border: 1px solid #EAEAEA;
    border-radius: 5px;
}

table {
    border-collapse: collapse;
}

a { color:#000000;text-decoration:none; }
.scriptCalendar { text-align:center; }
.scriptCalendar > thead > tr > td { width:80px;height:50px; }
.scriptCalendar > thead > tr:first-child > td { font-weight:bold; }
.scriptCalendar > thead > tr:last-child > td { 
    background-color:#F7F7F7;
    border-top: 2px solid #7AAFFF;
    border-left: 1px solid #EAEAEA; 
    border-right: 1px solid #EAEAEA;
    border-bottom: 1px solid #EAEAEA;  
}

.scriptCalendar > tbody > tr > td { width:80px;height:50px; 
    border: 1px solid #EAEAEA;  
}

#ableTimeTit {
    margin-bottom: -10px;
    text-align: center;
    font-size: 18px;
}

.scriptCalendar {
    margin: 0 auto;
}

#calYearMonth {
    font-size: 14px;
    color: #C1C1C1;
}

#petInfoBox {
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;
    padding: 20px;
    width: 440px;
    border-radius: 5px;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
    transition: all 0.3s cubic-bezier(.25, .8, .25, 1);
}

#petCheckImg {
    width: 200px;
    height: 200px;
}

#petInfoBoxInner {
    display: flex;
}

#petInfo {
    display: flex;
    flex-direction: column;
    margin-left: 20px;
}

#petNBWrap {
    display: flex;
    margin-bottom: 26px;
}

#petName {
    padding: 2px 4px;
    font-size: 14px;
    border: 1px slid #535353;
    background: white;
    color: #535353;
    border-radius: 50px;
}

#petBirth {
    margin-left: 14px;
    font-size: 12px;
    color: #C4C4C4;
}

#petKind {
    font-size: 12px;
    color: #E3C1A2;
}


#reservationBtn {
    margin-top: 80px;
    float: right;
    width: 120px;
    height: 40px;
    border-radius: 5px;
    background: #89A0F2;
    color: white;
    border: none;
    cursor: pointer;
}

#reservationBtn:focus {
    outline: none;
}

#periodInner {
    width: 480px;
}

#askPeriodTit {
    margin-bottom: 20px;
    font-size: 14px;
}

/*datepicker에서 사용한 이미지 버튼 style적용*/
img.ui-datepicker-trigger {
    margin-left:5px; vertical-align:middle; cursor:pointer;
}

#fromDate,
#toDate {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    width: 100px;
    height: 40px;
    font-size: 14px;
    cursor: pointer;
    border: none;
}

input::-webkit-input-placeholder {
    color: black;
    font-size: 14px;
}

input:-ms-input-placeholder {
    color: black;
    font-size: 14px;
}

#pickPeriodWrap {
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 480px;
    height: 70px;
    border-radius: 10px;
    border: 1px solid #C4C4C4;
}

#calendarImg {
    position: absolute;
    top: 34%;
    left: 8%;
    transform: (50%, 50%);
}

#pickPeriodInner {
    display: flex;
    justify-content: space-between;
    width: 320px;
}

#carePriceKind {
    margin-top: 20px;
    font-size: 14px;
    color: #7AAFFF;
}

#selectPetTit {
    margin: 0px 0px 20px 0px;
    font-size: 14px;
}

#checkBoxImg {
    cursor: pointer;
}

#ableTimeWrap {
    height: 382px;
}
#carePrice {
	display: flex;
	align-items: center;
	margin-top: 8px;
	font-size: 16px;
	color: #89A0F2;
}
#petCheckBoxWrap {
	margin-right: 60px;
	padding: 20px;
	width: 200px;
	height: 260px;
	border-radius: 10px;
	box-shadow: rgba(33, 35, 38, 0.1) 0px 10px 10px -10px;
}

#tagCheckWrap {
	display: flex;
	align-items: center;
	justify-content: space-between;
	margin-top: 20px;
}

#petInfoName {
margin-top: 20px;
}

#checkbox {
	width: 20px;
	height: 20px;
}

#petPickWrap {
	display: flex;
	overflow-x: scroll;
}

#petInfoBirth {
	margin-top: 4px;
	font-size: 14px;
	color: #C4C4C4;
}

#petInfoKind {
	font-size: 12px;
	color: #E3C1A2;
}

#petCheckImg {
	width: 200px;
	height: 150px;
	object-fit: cover;
	border-radius: 5px;
}

#logo {
    z-index: 1;
   	margin-left: 14px;
}

#pageBg {
	background-color: #F8F9FA;
}

#petCheckBoxWrap {
	background-color: white;
}

#dollarImg {
	margin-right: 6px;
}

#petSitterService {
	padding: 20px 20px 0px 20px;
	width: 1154px;
	background: white;
	border-radius: 5px;
	border: 1px solid #EAEDEF;
}


#reviewText {
    padding: 20px;
    width: 1154px;
    height: 160px;
    font-size: 14px;
    resize: none;
    background: white;
	border-radius: 5px;
	border: 1px solid #EAEDEF;
}

#reviewText:focus {
	outline: none;
}
  #petPickWrap::-webkit-scrollbar {
    width: 10px;
  }
  #petPickWrap::-webkit-scrollbar-thumb {
    background-color: #2f3542;
    border-radius: 10px;
  }
  #petPickWrap::-webkit-scrollbar-track {
    background-color: none;
    border-radius: 10px;
  }
</style>

<script>
	var ableService = JSON.parse('${ableService}');
	var userPetsJson = JSON.parse('${userPetsJson}');
</script>
</head>

<body id="pageBg">
	<%@include file="../components/header.jsp"%>

	<form name="form" method="POST" action="<c:url value='/reservation/reserve'/>" onsubmit="return reservation()">
	<div id="reservationFormWrap">
		<input type="hidden" name="fromDate" value="${fromDate}" />
		<input type="hidden" name="toDate" value="${toDate}" />
		<input type="hidden" name="totalPrice" value="${param.totalPrice}" />
		<input type="hidden" name="sitterId" value="${petsitterInfo.sitter.id}" />
		
		<div id="targetName">${petsitterInfo.sitter.id} 반려동물 돌보미</div>
		<div id="carePrice"><img src="/images/dollarImg.svg" id="dollarImg" />이용 요금 ${param.totalPrice}원</div>
		<div id="subPageTit">반려동물 선택하기</div>
		<div id="petPickWrap">
			<c:forEach var="pet" items="${userPets}">
				<div id="petCheckBoxWrap">
					<c:if test="${pet.images eq null}">
						<img src="/images/petCheckImg.svg" id="petCheckImg" />
					</c:if>
					<c:if test="${pet.images ne null}">
						<img src="${pet.images[0]}" id="petCheckImg" />
					</c:if>
					<div id="petInfoName">${pet.name}</div>
					<div id="petInfoBirth">${pet.birth}살</div>
					<div id="tagCheckWrap">
						<div id="petInfoKind">#${pet.kind.smallCategory}</div>
						<input type="checkbox" class="checkbox" name="pet" value="${pet.id}" />
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

	<div id="footerWrap">
		<div id="footerText">Copyrights © 2021 by 윤김구이. All Rights
			Reserved.</div>
	</div>
</body>
</html>
