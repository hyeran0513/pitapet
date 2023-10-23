<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
    <title>관리자 페이지</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/header.css"/>
    <link rel="stylesheet" href="../css/footer.css"/>
    <link rel="stylesheet" href="../css/sitterApplyList.css"/>
</head>

<body>
	<div id="headerWrap">
       	<div id="headerInner">
        	<div id="logoWrap">
            	<c:url value="/manager/listSitterApply" var="managerMainUrl"/>
            	<img src="/images/adminRank.svg" />
        		<div id="logo" onClick="location.href='${managerMainUrl}'">PIT A PET</div>
        	</div>
        
         	<div id="gnb">
         		<c:url value='/member/logout' var="url"/>
                <button id="headerLoginBtn" onclick="location.href='${url}'">로그아웃</button>
           	</div>
       	</div>
   	</div>

    <div id="pageWrap">
        <div id="pageTit">지원사항 목록</div>

        <table>
            <tr>
                <th>아이디</th>
                <th>신청일</th>
                <th></th>
            </tr>
	            <c:forEach var="applicant" items="${applicantList}"> 
	            	<c:url value="/manager/viewApply" var="viewUrl">
						<c:param name="applyId" value="${applicant.id}" />
					</c:url>
		            <tr>
		                <td>${applicant.applicant.id}</td>
		                <td>${applicant.applyDate}</td>
		                <td><button id="applyDetailBtn" onClick="location.href='${viewUrl}'">자세히 보기</button></td>
		            </tr> 
	            </c:forEach>
        </table>
    </div>
    
    <%@include file="../components/footer.jsp" %>
</body>
</html>