<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
    <title>돌봄 일지</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <link rel="stylesheet" href="/css/careRecordForm.css"/>
    <script>
	    function recordCare() {
	    	if (form.title.value == "") {
	    		alert("일지 제목을 입력해주세요.");
	    		form.toDate.focus();
	    		return false;
	    	}
	    	if (form.content.value == "") {
	    		alert("일지 내용을 입력해주세요.");
	    		form.fromDate.focus();
	    		return false;
	    	}
	    	form.submit();
	    }
    </script>
</head>

<body>
	<%@include file="../components/header.jsp" %>

    <div id="careDiaryPageWrap">
        <div id="pageTit">돌봄 일지</div>
        
        <form name="form" method="POST" action="<c:url value='/care/recordCare'>
        	<c:param name='careId' value='${checkList[0].careInfo.id}'/></c:url>">
			<c:forEach var="pet" items="${checkList[0].careInfo.carePetList}">
				<div id="activeWrap">
		            <div id="activeTit"><b>${pet}</b>&nbsp;돌봄 체크 리스트</div>
		            <div id="activeContentWrap">
		   				<c:forEach var="check" items="${checkList}">
		   					<c:if test="${check.carePet.name == pet}">
				                <div id="activeContentInner">
				                    <div id="activeContentTit">${check.serviceInfo.title}</div>
				                    <div id="activeContent">
				                        <input type="checkbox" name="recvService" value="${check.id}" id="service">
				                    </div>
				                </div>
			                </c:if>
		                </c:forEach>
		            </div>
		        </div>	        
			</c:forEach>

	        <div id="diaryWrap">
	            <div id="diaryTit">
	                <input type="text" placeholder="제목을 입력하세요." id="diaryTitText" name="title"/>
	            </div>
	            <div id="diaryContent">
	                <textarea placeholder="내용을 입력하세요." id="diaryContentText" style="resize: none;" name="content"></textarea>
	            </div>
	        </div>
	
	        <div id="fileReviewBtnWrap">
	            <div id="fileWrap">
	                <img src="/images/fileImg.svg" id="fileImg"/>
	                <input type="file" id="fileBtn" name="picture"/>
	            </div>
	            <button type="button" id="detailReviewBtn" onClick="recordCare()">일지 추가</button>
	        </div>
        </form>
    </div>
    
    <%@include file="../components/footer.jsp" %>
</body>
</html>