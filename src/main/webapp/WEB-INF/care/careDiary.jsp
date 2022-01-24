<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
    <link rel="stylesheet" href="/css/careDiary.css"/>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
	    window.onload = function(){
	    	var sitter = "${care.sitter.sitter.id}";
	    	if (sitter == "${sessionScope.id}") {
		    	var start = new Date("${care.startDate}");
		    	var end = new Date("${care.endDate}");
		    	var needCount =  end.getDate() - start.getDate() + 1;
		    	var writeCount = "${writeCount}";
		    	if (writeCount == null || writeCount == '')
		    		writeCount = 0;
		    	else
		    		writeCount = parseInt(writeCount);   	
		
				if ((needCount - writeCount) > 0) {
					var newInputElement = document.createElement("img");
					$(newInputElement).attr("id", "writeBtn");
					$(newInputElement).attr("src", "/images/writeBtn.svg");
					$(newInputElement).attr("onClick", "location.href='/care/recordCare?careId=' + ${care.id}");
					$("#recordBtnDiv").append(newInputElement);
				}
	    	}
	    }
    </script>
</head>

<body id="pageBg">
    <%@include file="../components/header.jsp" %>

    <div id="pageWrap">
        <div id="pageTit">돌봄일지</div>
        
	    <div id="careViewWrap">
	        <div id="careViewHeader">
	            <div id="careViewTit">
	                <c:forEach var="pet" items="${care.carePetList}" varStatus="status">
	                    ${pet.name}
	                    <c:if test="${!status.last}">
	                    , 
	                    </c:if>
	                </c:forEach>
	                <span id="period">(보호자: ${care.companion.name}) [${fn:split(care.startDate, ' ')[0]} ~ ${fn:split(care.endDate, ' ')[0]}]</span></div>
	            <div id="recordBtnDiv"></div>
	        </div>
	        
	        <div id="careViewBody">
	            <c:if test="${care.careRecordList[0].writeDate == null}">
	                돌봄일지 목록이 비어있습니다.
	            </c:if>
	            <c:if test="${care.careRecordList[0].writeDate != null}">
	                <c:forEach var="careRecord" items="${care.careRecordList}" varStatus="status">
	                    <div id="writeDateWrap">
	                        <c:set var="writeDate" value="${fn:split(careRecord.writeDate, ' ')[0]}"/>
	                        <c:set var="writeTime" value="${fn:split(careRecord.writeDate, ' ')[1]}"/>
	                        <div id="writeDate">${fn:split(writeDate, "-")[0]}년 ${fn:split(writeDate, "-")[1]}월 ${fn:split(writeDate, "-")[2]}일</div>
	                    </div>
	
	                    <div id="careViewBoxWrap">
	                        <div id="careViewBox">
	                            <img src="/images/careNullImg.svg" id="careImg"/>
	                            <div id="careViewBoxInner">
	                                <div id="careViewBoxTitWrap">
	                                <div id="careViewBoxTit">${careRecord.title}</div>
	                                    <div class="dropdown">
	                                        <img src="/images/update.svg" />
	                                        <div class="dropdown-content">
	                                        <a href="#">수정하기</a>
	                                        </div>
	                                    </div>
	                                </div>
	                                <div id="careViewBoxContent">${careRecord.content}</div>
	                                <div id="diaryServiceInfo">
	                                    ★ 돌봄 체크 리스트 <br/>
	                                    <c:forEach var="careDetail" items="${careRecord.checkList}" varStatus="status">
	                                        ${careDetail.carePet.name} - ${careDetail.serviceInfo.title} 
	                                        <c:if test="${careDetail.check == 'Y'}">
	                                            ▣
	                                        </c:if>
	                                        <c:if test="${careDetail.check == 'N'}">
	                                            □
	                                        </c:if>
	                                        <br/> 
	                                    </c:forEach>
	                                </div>
	                                <div id="sitterNameWrap">
	                                    <div id="sitterName">by. ${care.sitter.sitter.name} 반려동물 돌보미</div>
	                                </div>
	                            </div>
	                        </div>
	                        <div id="writeTimeWrap">
	                            <div id="writeTime">${fn:split(writeTime, ':')[0]}시 ${fn:split(writeTime, ':')[0]}분</div>
	                        </div>
	                    </div>
	                </c:forEach>
	            </c:if>
			</div>
		</div>
	</div>
    
    <%@include file="../components/footer.jsp" %>
</body>
</html>