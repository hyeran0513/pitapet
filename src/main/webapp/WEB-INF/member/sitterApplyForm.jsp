<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>돌보미 지원</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
	<link rel="stylesheet" href="/css/sitterApplyForm.css"/>
</head>

<body>
	<%@include file="../components/header.jsp" %>
    
    <div id="pageWrap">
        <div id="pageTit">돌보미 지원</div>
        <c:if test="${applyFailed}">
			<script> alert('돌보미 지원에 실패했습니다. 다시 지원해주세요.'); </script>
		</c:if>
     
     <form name="form" method="POST" action="<c:url value='/member/applySitter' />">    
        <div id="introTit">자기소개</div>
        <div id="introTextWrap">
            <textarea placeholder="내용을 입력하세요." id="introText" name="introduction"></textarea>
        </div>

        <div id="infoTit">돌보미 경력</div>
        <div id="infoContent">
        	<input type="text" placeholder="경력(O년 O개월)" name="career"/>
        </div>

        <div id="infoTit">자격증</div>
        <div id="infoContent">
        	<input type="text" placeholder="OO 자격증" name="certification"/>
        </div>
        <div id="fileWrap"><img src="../images/fileImg.svg" id="fileImg" /><input type="file"/></div> 
        <div id="fileWarning">* 자격증 사진을 첨부하세요.</div>

        <div id="btnWrap">
            <button type="submit" id="applyBtn">지원</button>
        </div>
       </form> 
    </div>
    
    <div id="footerWrap">
        <div id="footerText">Copyrights © 2021 by 윤김구이. All Rights Reserved.</div>
    </div>
</body>
</html>
