<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/header.css"/>
    
    <script>
		function accessAlert() {
			alert('로그인 후 이용가능합니다.');    		
		}
    </script>
</head>

<body>
	<div id="headerWrap">
        <div id="headerInner">
            <div id="logoWrap">
            	<c:url value="/mainpage" var="mainUrl"/>
                <c:choose>
                	<c:when test="${sessionScope.identity == 'C'}">
                		<img src="/images/memberRank.svg" />
                	</c:when>
                	<c:when test="${sessionScope.identity == 'S'}">
                		<img src="/images/sitterRank.svg" />
                	</c:when>
                	<c:when test="${sessionScope.identity == 'M'}">
                		<img src="/images/adminRank.svg" />
                	</c:when>
                </c:choose>  
                <div id="logo" onClick="location.href='${mainUrl}'">PIT A PET</div>
            </div>
            
            <div id="gnb">    
                <c:url value='/review/listReview' var = "reviewListUrl"/>
            	<div id="reviewLink" onclick="location.href='${reviewListUrl}'">이용 후기</div>
                
                <c:if test="${!isNotLogined}">
                	<c:url value='/reservation/listSitter' var="sitterListUrl">
                		<c:param name="currentPage" value="1" />
                		<c:param name="searchOption" value="list" />
                	</c:url>
                	<div id="sitterListLink" onclick="location.href='${sitterListUrl}'">돌보미 조회</div>
                	
                	<c:url value='/like/listLike' var="likeListUrl"/>
                	<div id="likeLink" onclick="location.href='${likeListUrl}'">좋아요</div>
                	
                	<c:url value='/member/memberMyPage' var="memberMyPageUrl"/>
                	<div id="myPageLink" onclick="location.href='${memberMyPageUrl}'">마이페이지</div>
                	
                	<c:url value='/member/logout' var="url"/>
                	<button id="headerLoginBtn" onclick="location.href='${url}'">로그아웃</button>
                </c:if>
                <c:if test="${isNotLogined}">
                	<div id="sitterListLink" onClick="accessAlert();">돌보미 조회</div>
                	<div id="likeLink" onClick="accessAlert();">좋아요</div>
                	<div id="myPageLink" onClick="accessAlert();">마이페이지</div>
                	
                    <c:url value='/member/login/form' var="url"/>
                	<button id="headerLoginBtn" onclick="location.href='${url}'">로그인</button>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>