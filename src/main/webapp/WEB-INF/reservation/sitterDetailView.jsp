<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
    <title>세부사항</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <script src="/js/sitterDetailView.js"></script>
    <link rel="stylesheet" href="/css/main.css"/>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
   	<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="/css/sitterDetailView.css"/>
	<script>
		var today = new Date();
		var year = today.getFullYear();
	    $(function() {
	        $("#fromDate,#toDate").datepicker({
	                dateFormat: 'yy-mm-dd' //달력 날짜 형태
	                ,yearRange: year + ":" + (year + 1)
	                ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
	                ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
	                ,changeYear: true //option값 년 선택 가능
	                ,changeMonth: true //option값  월 선택 가능                       
	                ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
	                ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
	                ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
	                ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
	                ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
	                ,minDate: "+1D" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
	                ,maxDate: "+30D" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
	        		,beforeShowDay: disableAllTheseDays
	            });                    
	            
	            //초기값을 오늘 날짜로 설정해줘야 합니다.
	            $('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)            
	        });
	    
	 	// 불가능한 날짜들 배열
	 	var schedules = JSON.parse('${schedules}');
	 	//console.log(schedules);
	    var disabledDays = [];
	    for (key in schedules) {
	    	disabledDays.push(schedules[key].split(' ')[0]);
	    }
	
	    // 특정일 선택막기
	    function disableAllTheseDays(date) {
	        var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();
	        m = cfSetAddZero(m + 1);
	        d = cfSetAddZero(d);
	        for (var i = 0; i < disabledDays.length; i++) {
	            if($.inArray(y + '-' + m + '-' + d,disabledDays) != -1) {
	                return [false];
	            }
	        }
	        return [true];
	    }
	    
	    function cfSetAddZero(target) {
	        if (target <= 9)
	        	return "0" + target;
	        return target;
	    }
	    
	    function getPrice() {
	    	var start = new Date($("#fromDate").val());
	    	var end = new Date($("#toDate").val());
	    	var dateDiff = Math.ceil((end.getTime() - start.getTime()) / (1000 * 3600 * 24));
	    	console.log(dateDiff);
	    	var price = 0;
	    	if (dateDiff == 0) {
	    		price = '${fn:split(sitterInfo.calculatedPrice,',')[1]}';
	    		$('#carePrice').text('데이케어 ${fn:split(sitterInfo.calculatedPrice,',')[1]}원');
	    	}
	    	else {
	    		price = '${fn:split(sitterInfo.calculatedPrice,',')[0]}';
	    		price = price * dateDiff
	    		$('#carePrice').text(dateDiff + '박케어 ' + price +'원');
	    		console.log(price);
	    	}
	    	
	    	var newInputElement = document.createElement("input");
			$(newInputElement).attr("type", "hidden");
			$(newInputElement).attr("name", "totalPrice");
			$(newInputElement).attr("value", price);
			$("#carePrice").append(newInputElement);
	    }
	    
	    function reserve() {
	    	if (form.fromDate.value == "") {
	    		alert("체크인 날짜를 선택하세요.");
	    		form.fromDate.focus();
	    		return false;
	    	}
	    	if (form.toDate.value == "") {
	    		alert("체크아웃 날짜를 선택하세요.");
	    		form.toDate.focus();
	    		return false;
	    	}
	    	form.submit();
	    }
	</script>
</head>

<body id="pageBg">
	<%@include file="../components/header.jsp" %>

    <div id="pageWrap">
    <div id="pageTit">돌보미 상세 정보</div>
        <div id="pageBody">
        <div id="petSitterInfoWrap">
            <div id="sitterInfo">
                <img src="${sitterInfo.sitter.profileImage}" id="sitterNewImg"/>
                <div id="sitterInfoInner">
                    <div id="sitterNameLikeWrap">
                        <div id="sitterName">${sitterInfo.sitter.id} 반려동물 돌보미</div>
                        <div id="likeCount">
                            <img src="/images/likeOn.svg" id="likeImg" />${sitterInfo.like}
                        </div>
                    </div>
                    <div id="locationWrap">
                        <img src="/images/location.svg" />
                        ${sitterInfo.sitter.address}
                    </div>
                    
                    <c:set var="tags" value="${fn:split(sitterInfo.tag,',')}" />
                    <div id="detailTit">
	                	<c:forEach var="tag" items="${tags}">
	                		<div id="tagWrap">#${tag}</div> 
	                    </c:forEach>
	                </div>
                </div>
            </div>
            
            <div id="sitterIntroTextWrap">
                <div id="subPageTit">돌보미 소개</div>
                <div id="sitterIntroText">${sitterInfo.notes}</div>
            </div>

            <div id="serviceWrap">
                <div id="subPageTit">이용 서비스</div>
                <div id="serviceInner">   
                    <c:forEach var="service" items="${sitterInfo.services}" varStatus="status">
	                    <div id="service"> 
	               			<c:choose>
	               				<c:when test="${service.title eq '산책하기'}"><img src="/images/wark.png" id="serviceImg" /></c:when>
	               				<c:when test="${service.title eq '목욕하기'}"><img src="/images/bath.png" id="serviceImg" /></c:when>
	               				<c:when test="${service.title eq '배식하기'}"><img src="/images/feed.png" id="serviceImg" /></c:when>
	               				<c:when test="${service.title eq '픽업가능'}"><img src="/images/pickup.png" id="serviceImg" /></c:when>
	               				<c:when test="${service.title eq '실내놀이'}"><img src="/images/play.png" id="serviceImg" /></c:when>
	               				<c:when test="${service.title eq '미용관리'}"><img src="/images/beauty.png" id="serviceImg" /></c:when>
	               			</c:choose>
	               				<div id="serviceTitContentWrap">
			                    	<div id="serviceTit">${service.title}</div>
			                    	<div id="serviceContent">${service.content}</div>
			                    </div>
			            </div>
                    </c:forEach>
                </div>
            </div>

            <div id="ablePetWrap">
                <div id="subPageTit">돌봄 가능 반려동물</div>
                <div id="ablePetInner">
                    <c:forEach var="kind" items="${sitterInfo.kinds}">
                        <div id="ablePet">${kind.smallCategory}</div>
                    </c:forEach>
                </div>
            </div>

            <div id="reviewWriteWrap">
            	<c:if test="${not empty careListOfReview}">
            		<form method="POST" action="<c:url value='/review/addReview'/>" enctype="multipart/form-data">
		                <div id="subPageWrap">
			                <div id="subPageTit">리뷰 작성</div>
			                <select name='reviewStar'>
							  <option selected>5.0</option>
							  <option>4.0</option>
							  <option>3.0</option>
							  <option>2.0</option>
							  <option>1.0</option>
							</select>
						</div>
		                <input type="hidden" name="careId" value="${careListOfReview.get(0).id}" />
		                <input type="hidden" name="sitterId" value="${sitterInfo.sitter.id}" />
		                <textarea placeholder="(내용)" id="reviewContent" name="content"></textarea>
		                <div id="reviewBtnWrap">
		                    <div>
		                        <img src="/images/fileImg.svg" />
		                        <input type="file" name="picture" />
		                    </div>
		                    <button id="reviewBtn">등록</button>
		                </div>
	                </form>
                </c:if>
            </div>

            <div id="reviewWriteWrap">
                <div id="reviewTitWrap">
                    <div id="reviewTit">이용 후기</div>
                    <div id="scopeWrap">
                        <img src="/images/star.svg"/>
                        <div id="scope">${sitterInfo.avgRate}</div>
                    </div>
                </div>

                <c:forEach var="review" items="${reviews}">
                    <div id="reviewerWrap">
                        <div id="reviewerInner">
                            <img src="/images/reviewerImg.svg" id="reviewerImg"/>
                            
                            <div id="reviewerInfo">
                                <div id="reviewerName">${review.careInfo.companion.id} 보호자님</div>
                                <div id="scopeWrap">
                                    <img src="/images/location.svg" />
                                    <div id="scope">${sitterInfo.sitter.address}</div>
                                </div>
                            </div>
                        
                            <div id="reviewDate">${review.writeDate}</div>
                        </div>
                        <div id="reviewText">${review.content}</div>
                        <div id="reviewImgScopeWrap">
                            <c:if test="${empty review.images}">
                                <img src="/images/reviewNewImg.svg" id="reviewImg"/>
                            </c:if>
                            <c:if test="${not empty review.images}">
                                <img src="${pageContext.request.contextPath}${review.images[0]}" id="reviewImg"/>
                            </c:if>
                            <div id="reviewScopeWrap">
                                <img src="/images/star.svg" />
                                <div id="scope">${review.rate}</div>
                            </div>
                        </div>
                        <div id="line"></div>   
                    </div>
                </c:forEach>
            </div>
        </div>

        <div id="periodWrap">
        	<div id="periodInner">
            <div id="askPeriodTit">언제 맡기시나요?</div>
            <form name="form" method="GET" action="<c:url value='/reservation/reserve'/>">
	            <input type="hidden" name="sitterId" value="${sitterInfo.sitter.id}" />
	            <div id="pickPeriodWrap">
	                <img src="/images/calendar.svg" id="calendarImg" />
	                <div id="pickPeriodInner">
	                    <input type="text" name="fromDate" id="fromDate" placeholder="체크인 날짜" onfocus="this.blur()" />
	                    <img src="/images/arrow.svg"/>
	                    <input type="text" name="toDate" id="toDate" placeholder="체크아웃 날짜" onfocus="this.blur()" onChange="getPrice()"/>
	                </div>
	            </div>
	            <div id="carePriceKind">*데이케어: ${fn:split(sitterInfo.calculatedPrice,',')[1]}원 / 1박케어: ${fn:split(sitterInfo.calculatedPrice,',')[0]}원 </div>
	            <div id="carePrice"></div>
	            <input type="button" value="예약하기" id="reservationBtn" onClick="reserve()"/>
        	</form>
        </div>
    </div>
    </div>
    
    <%@include file="../components/footer.jsp" %>
    </div>
</body>
</html>