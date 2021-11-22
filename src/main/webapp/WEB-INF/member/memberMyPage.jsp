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
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <link rel="stylesheet" href="/css/myPage.css"/>
    <link rel="stylesheet" href="/css/memberMyPage.css"/>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    
    <style>
	    #logo {
		    z-index: 1;
		   	margin-left: 14px;
		}
	</style>
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
        </div>

        <div id="container">
            <div id="myInfoWrap">
                <div id="myInfoTitWrap">
                    <div id="myInfoTit">내 정보</div>
                    <img src="/images/infoEdit.svg" id="editBtn" />
                </div>
                <c:if test="${memberInfo.profileImage eq null}">
					<img src="/images/myPageNullImg.svg" id="profileImg" />
				</c:if>
				<c:if test="${memberInfo.profileImage ne null}">
					<img src="${memberInfo.profileImage}" id="profileImg" />
				</c:if>
                <div id="myInfo">
                    <div id="name">${memberInfo.name}</div>
                    <div id="phone">${memberInfo.phone}</div>
                    <div id="myPageBtnWrap">
                        <button id="petInfoBtn">반려동물 정보</button>
                        <c:if test="${applicationStatus eq null}">
                        	<c:url value='/member/applySitter' var="applySitterUrl"/>
                        	<button id="applySitterBtn" onclick="location.href='${applySitterUrl}'">돌보미 지원</button>
                        </c:if>
                        <c:if test="${applicationStatus eq 'X'}">
                        	<c:url value='/member/updateSitterApply' var="updateSitterApplyUrl"/>
                        	<button id="applySitterBtn" onclick="location.href='${updateSitterApplyUrl}'">돌보미 지원 정보 조회/수정</button>
                        </c:if>
                        <c:if test="${applicationStatus eq 'Y'}">
                        	<c:url value='/petSitter/sitterMyPage' var="sitterMyPageUrl"/>
                        	<button id="applySitterBtn" onclick="location.href='${sitterMyPageUrl}'">돌보미 마이페이지</button>
                        </c:if>
                        <c:if test="${applicationStatus eq 'Z'}">
                        	<button id="applySitterBtn">돌보미 지원 정보 조회</button>
                        </c:if>
                    </div>
                </div>
            </div>

            <div id="reservationInfoWrap">
                <table>
                    <tr>
                        <th>이름</th>
                        <th>예약일</th>
                        <th>완료상태</th>
                        <th></th>
                    </tr>
           
                    <c:forEach var="care" items="${careList}">            	
                   		<c:if test="${care.status eq 'X'}">
                   			<tr>
	                    		<td>${care.sitter.sitter.name}</td>
	                    		<td>${fn:split(care.startDate, ' ')[0]} ~ ${fn:split(care.endDate, ' ')[0]}</td>
	                   			<td>예약완료</td>
	                   			<td>
		                            <button id="cancelBtn">취소하기</button>
		                        </td>
	                        </tr>
                   		</c:if>
                   		
                   		<c:if test="${care.status eq 'Y'}">
                   			<tr>
	                    		<td>${care.sitter.sitter.name}</td>
	                    		<td>${fn:split(care.startDate, ' ')[0]} ~ ${fn:split(care.endDate, ' ')[0]}</td>
	                   			<td>진행중</td>
	                   			<td>
		                            <button id="careBtn">돌봄일지</button>
		                        </td>
	                        </tr>
                   		</c:if>
                   		
                   		<c:if test="${care.status eq 'Z'}">
            
                   			<tr>
	                    		<td>${care.sitter.sitter.name}</td>
	                    		<td>${fn:split(care.startDate, ' ')[0]} ~ ${fn:split(care.endDate, ' ')[0]}</td>
	                   			<td>돌봄완료</td>
	                   			<td>
		                            <button id="careBtn">돌봄일지</button>
		                            <button id="reviewBtn">리뷰작성</button>
		                        </td>
	                        </tr>
                   		</c:if>
                    </c:forEach>

                </table>
            </div>
        </div>
    </div>
</body>
</html>