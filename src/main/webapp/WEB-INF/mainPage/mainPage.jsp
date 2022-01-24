<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
    <title>홈</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <link rel="stylesheet" href="/css/main.css"/>
    <link rel="stylesheet" href="/css/mainPage.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
   	<meta http-equiv="X-UA-Compatible" content="ie=edge"> 
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"> 	
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="/js/mainPage.js"></script>
    <script src="/js/main.js"></script>
    
    <script>
	    document.addEventListener('DOMContentLoaded', function() {
	    	var calendarEl = document.getElementById('calendar');
	    	var calendar = new FullCalendar.Calendar(calendarEl, {
	    		initialView: 'dayGridMonth'
	    	});
	    	calendar.render();
	    	
	    	var schedules = JSON.parse('${careSchedules}');
	    	// console.log(schedules);
	    	for (key in schedules) {
	    		var name = '';
	    		var color = '';
	    		if ('${sessionScope.id}' == schedules[key].companion.id) {
	        		for (var i = 0; i < schedules[key].careList.length; i++) {
	        			name += schedules[key].careList[i].carePet.name;
	        			if (i == schedules[key].careList.length - 1)
	        				break;
	        			name += ", ";
	        		}        
	        		color = '#FFA07A';
	    		}
	    		else {
	    			name = schedules[key].companion.id + "님";
	    			color = '#FF4500';
	    		}
	
	    		var status = '';
	    		if (schedules[key].status == 'X')
	    			status = ' 돌봄 예약';
	    		else if (schedules[key].status == 'Y')
	    			status = ' 돌봄 진행';
	    		else
	    			status = ' 돌봄 완료';
	
	    		let endDate = new Date(schedules[key].endDate);
	        	calendar.addEvent({
	        		title: name + status,
	        		start: schedules[key].startDate,
	        		end: endDate.setDate(endDate.getDate() + 1),
	        		allDay: true,
	        		color: color
	        	})
	    	}
	    });
    </script>
</head>

<body>
	<%@include file="../components/header.jsp" %>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous">
	</script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script> 
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script> 
	<script> 
	    $('.carousel').carousel({ 
	        interval: 500 //기본 5초 
	    }) 
	</script>
        
	<div id="demo" class="carousel slide" data-ride="carousel">
       	<div class="carousel-inner"> <!-- 슬라이드 쇼 -->
            <div class="carousel-item active"> <!--가로--> 
	            <img class="d-block w-100" src="/images/bannerImg1.svg?auto=compress&cs=tinysrgb&h=650&w=940" alt="First slide"> 
			 	<div id="bannerText">
		        	<div id="bannerSubTit">핏어펫</div> 
		           	<div id="bannerTit">PIT A PET</div>
		           	<div id="bannerInfo">반려동물 돌보미 매칭 서비스</div> 
				</div>
            </div> 
            
            <div class="carousel-item"> 
           		<img class="d-block w-100" src="/images/bannerNew2.svg?auto=compress&cs=tinysrgb&h=650&w=940" alt="Second slide"> 
           	</div>
                          
            <div class="carousel-item"> 
            	<img class="d-block w-100" src="/images/bannerNew3.svg?auto=compress&cs=tinysrgb&h=650&w=940" alt="Third slide"> 
            </div> <!-- / 슬라이드 쇼 끝 --> <!-- 왼쪽 오른쪽 화살표 버튼 --> 
            
            <a class="carousel-control-prev" href="#demo" data-slide="prev"> <span class="carousel-control-prev-icon" aria-hidden="true"></span> 
                                <!-- <span>Previous</span> -->
            </a> 
            <a class="carousel-control-next" href="#demo" data-slide="next"> 
            <span class="carousel-control-next-icon" aria-hidden="true"></span> 
                                 <!-- <span>Next</span> --> 
            </a> <!-- / 화살표 버튼 끝 --> <!-- 인디케이터 --> 
            <ul class="carousel-indicators"> 
            	<li data-target="#demo" data-slide-to="0" class="active"></li>
               	<!--0번부터시작--> <li data-target="#demo" data-slide-to="1"></li> 
                <li data-target="#demo" data-slide-to="2"></li> 
            </ul> <!-- 인디케이터 끝 --> 
    	</div>
    </div>
       
	<div id="mainPageWrap">                             
        <div id="scheduleWrap">
            <div id="scheduleTit">일정 <span>schedule</span></div>
        	<div id="calendar"></div>
        </div>

        <div id="reviewWrap">
            <div id="reviewTit">이용 후기 <span>review</span></div>
            <div id="reviewMoreBtnWrap">
            	<c:url value="/review/listReview" var="reviewUrl"/>
                <div id="reviewMoreBtn" onClick="location.href='${reviewUrl}'">더 보기+</div>
            </div>
       

            <div id="reviewBoxWrap">
            	<c:forEach var="review" items="${reviews}">
            		<c:url value="/reservation/viewSitterDetail" var="viewUrl">
						<c:param name="sitterId" value="${review.careInfo.sitter.sitter.id}" />
					</c:url>
            		<div id="reviewBox">
                    	<c:if test="${empty review.images}">
                    		<img src="../images/reviewNullImg.svg" id="reviewImg"/>
                    	</c:if>
                    	<c:if test="${not empty review.images}">
                    		<img src="${review.images[0]}" id="reviewImg"/>
                    	</c:if>
                    	<div id="reviewBoxInner">
                    		<div id="reviewBoxContent">
	                        	<div id="reviewerDateWrap">
	                               	<div id="reviewer">${review.careInfo.companion.id} 님</div>
	                            	<div id="reviewDate">${fn:split(review.writeDate, ' ')[0]}</div>
	                        	</div>
	                        	<div id="locationWrap">
	                        		<img src="/images/location.svg" id="locationImg"/>
	                        		${review.careInfo.sitter.sitter.address}
	                        	</div>
	                        	<div id="review">${review.content}</div>
                        	</div>
                        	<div id="targetScopeWrap">
                            	<div id="reviewTarget" onClick="location.href='${viewUrl}'">about ${review.careInfo.sitter.sitter.id}</div>
                            	<div id="scopeWrap">
                                	<img src="../images/star.svg"/>
                                	<div id="scope">${review.rate}</div>
                            	</div>
                        	</div>
                    	</div>
                	</div>
            	</c:forEach>
            </div>
        </div>
    </div>
    
    <%@include file="../components/footer.jsp" %>
</body>
</html>