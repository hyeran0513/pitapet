<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
    <title>마이페이지</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <link rel="stylesheet" href="/css/myPage.css"/>
    <link rel="stylesheet" href="/css/memberMyPage.css"/>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script src="/js/memberMyPage.js"></script>
    <script>
	    var memberInfoJson = JSON.parse('${memberInfoJson}');
	    var applicationStatus = '${applicationStatus}';
	    var careListJson = JSON.parse('${careListJson}');
	    var sitterInfoJson = JSON.parse('${sitterInfoJson}');
	    var serviceListJson = JSON.parse('${serviceListJson}');
	    var petKindJson = JSON.parse('${petKindJson}');
	    var sitterCareListJson = JSON.parse('${sitterCareListJson}');
    </script>
</head>

<body>
	<%@include file="../components/header.jsp" %>

    <div id="pageWrap">
        <div id="pageTitWrap">
            <div id="pageTit">마이페이지</div>
            <c:if test="${sessionScope.identity == 'S'}">
            	<div id="changeBtnWrap">
	                <button id="memberBtn">보호자</button>
	                <button id="sitterBtn">돌보미</button>
            	</div>
            </c:if>
            <c:if test="${param.cancelFailed}">
				<script> alert("예약 취소에 실패했습니다. 예약 취소는 24시간 전까지 가능합니다."); </script>
			</c:if>
        </div>

        <div id="container">
            <div id="myInfoWrap">
                <div id="myInfoTitWrap">
                    <div id="myInfoTit">내 정보</div>
                    <c:url value='/member/updateMember' var="updateMemberUrl"/>
                    <img src="/images/infoEdit.svg" id="editBtn" onclick="location.href='${updateMemberUrl}'" />
                </div>
	                <c:if test="${empty memberInfo.profileImage}">
						<img src="/images/myPageNullImg.svg" id="profileImg" />
					</c:if>
					<c:if test="${not empty memberInfo.profileImage}">
						<img src="${memberInfo.profileImage}" id="profileImg" />
					</c:if>
                <div id="myInfo">
                	<div id="cameraWrap">
	                    <div id="name">${memberInfo.name}</div>
	                    <c:url value='/member/updateProfilePic' var="updateProfilePicUrl"/>
	                    <button id="changeImg" onclick="location.href='${updateProfilePicUrl}'">사진 변경</button>
                    </div>
                    <div id="phone">${memberInfo.phone}</div>
                    <div id="myPageBtnWrap">
                    	<c:url value='/pet/listPet' var="listPetUrl"/>
                        <button id="petInfoBtn" onclick="location.href='${listPetUrl}'">반려동물 정보</button>
                        
                        <c:choose>
	                        <c:when test="${applicationStatus eq 'X'}">
	                        	<c:url value='/member/viewSitterApply' var="viewSitterApplyUrl"/>
	                        	<button id="applySitterBtn" onclick="location.href='${viewSitterApplyUrl}'">돌보미 지원 정보 조회/삭제</button>
	                        </c:when>
	                        <c:when test="${applicationStatus eq 'Y' && sitterInfo eq null}">
	                        	<c:url value='/petSitter/registerSitter' var="sitterRegisterUrl"/>
	                        	<button id="applySitterBtn" onclick="location.href='${sitterRegisterUrl}'">돌보미 정보 등록</button>
	                        </c:when>
	                        <c:when test="${applicationStatus eq 'Y' && sitterInfo ne null}">
	                        	<c:url value='/member/updateSitter' var="updateSitterUrl"/>
	                        	<button id="applySitterBtn" onclick="location.href='${updateSitterUrl}'">돌보미 정보 수정</button>
	                        </c:when>
	                        <c:when test="${applicationStatus eq 'Z'}">
	                        	<button id="applySitterBtn">돌보미 지원 정보 조회</button>
	                        </c:when>
	                        <c:otherwise>
	                        	<c:url value='/member/applySitter' var="applySitterUrl"/>
	                        	<button id="applySitterBtn" onclick="location.href='${applySitterUrl}'">돌보미 지원</button>
	                        </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>

            <div id="reservationInfoWrap">
                <table id="infoTable">
                    <tr>
                        <th>이름</th>
                        <th>예약일</th>
                        <th>완료상태</th>
                        <th></th>
                    </tr>
           
                    <c:forEach var="care" items="${careList}">     
                    	<c:url value='/reservation/viewReservation?careId=${care.id}' var="viewReservationUrl"/>
           				<c:url value='/care/listCareDiary' var='careDiaryUrl'>
                   			<c:param name='careId' value='${care.id}' />
                   		</c:url>
                   		<c:if test="${care.status eq 'X'}">                 			
                   			<tr>
	                    		<td>${care.sitter.sitter.name}</td>
	                    		<td>${fn:split(care.startDate, ' ')[0]} ~ ${fn:split(care.endDate, ' ')[0]}</td>
	                   			<td><button id="rezCompleteBtn" onClick="location.href='${viewReservationUrl}'">예약완료</button></td>
	                   			<td>
	                   				<c:url value='/reservation/cancelReservation?careId=${care.id}' var="cancelReservationUrl"/>
		                            <button id="cancelBtn" onClick="location.href='${cancelReservationUrl}'">취소하기</button>
		                        </td>
	                        </tr>
                   		</c:if>
                   		
                   		<c:if test="${care.status eq 'Y'}">
							<c:url value='/care/listCareDiary?careId=${care.id}' var="careDiaryUrl"/>
                   			<tr>
	                    		<td>${care.sitter.sitter.name}</td>
	                    		<td>${fn:split(care.startDate, ' ')[0]} ~ ${fn:split(care.endDate, ' ')[0]}</td>
	                   			<td><button id="rezProceedingBtn" onClick="location.href='${viewReservationUrl}'">진행중</button></td>
	                   			<td>
		                            <button id="careBtn" onClick="location.href='${careDiaryUrl}'">돌봄일지</button>
		                        </td>
	                        </tr>
                   		</c:if>
                   		
                   		<c:if test="${care.status eq 'Z'}">
							<c:url value='/reservation/viewReservation?careId=${care.id}' var="viewReservationUrl"/>
							<c:url value='/reservation/viewSitterDetail?sitterId=${care.sitter.sitter.id}' var="viewSitterUrl"/>
                   			<tr>
	                    		<td>${care.sitter.sitter.name}</td>
	                    		<td>${fn:split(care.startDate, ' ')[0]} ~ ${fn:split(care.endDate, ' ')[0]}</td>
	                   			<td><button id="careCompleteBtn" onClick="location.href='${viewReservationUrl}'">돌봄완료</button></td>
	                   			<td>
		                            <button id="careBtn" onClick="location.href='${careDiaryUrl}'">돌봄일지</button>
		                            <button id="reviewBtn" onClick="location.href='${viewSitterUrl}'">리뷰작성</button>
		                        </td>
	                        </tr>
                   		</c:if>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    
    <%@include file="../components/footer.jsp" %>
</body>
</html>
