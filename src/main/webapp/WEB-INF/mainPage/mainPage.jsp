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
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <link rel="stylesheet" href="/css/main.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
   	<meta http-equiv="X-UA-Compatible" content="ie=edge"> 
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"> 	
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <!--  <script src="/js/mainPage.js"></script> -->
    <script src="/js/main.js"></script>
    
    <script>
		document.addEventListener('DOMContentLoaded', function() {
			var calendarEl = document.getElementById('calendar');
        	var calendar = new FullCalendar.Calendar(calendarEl, {
				initialView: 'dayGridMonth'
			});
        	calendar.render();
        	
        	var schedules = JSON.parse('${careSchedules}');
        	console.log(schedules);
        	for (key in schedules) {
        		var petNames = '';
        		console.log(schedules[key].careList.length);
        		for (var i = 0; i < schedules[key].careList.length; i++) {
        			petNames += schedules[key].careList[i].carePet.name;
        			if (i == schedules[key].careList.length - 1)
        				break;
        			petNames += ", ";
        		}
        		var color = '';
        		var status = '';
        		if (schedules[key].status == 'X') {
        			color = '#FF4500';
        			status = ' 돌봄 예약';
        		}
        		else if (schedules[key].status == 'Y') {
        			color ='#FF6347';
        			status = ' 돌봄 진행';
        		}
        		else {
        			color = '#FFA07A';
        			status = ' 돌봄 완료';
        		}
            	calendar.addEvent({
            		title: petNames + status,
            		start: schedules[key].startDate,
            		end: schedules[key].endDate,
            		allDay: true,
            		color: color
            	})
        	}
		});
    </script>
 
    <style> 
    	@charset "UTF-8";

		#headerWrap {
		    z-index: 10; 
		    top: 0px; 
		    position: fixed; 
		    box-sizing: border-box; 
		    transition: All 0.2s ease; 
		    -webkit-transition: All 0.2s ease; 
		    -moz-transition: All 0.2s ease; 
		    -o-transition: All 0.2s ease;
		}
		
		#mainPageWrap {
		    margin: 0 auto;
		    width: 1194px;
		}
		
		#bannerWrap {
		    position: relative;
		}
		
		#banner {
		    display: flex;
		    justify-content: center;
		}
		
		#bannerText {
		    position: absolute;
		    top: 500px;
		    left: 42%;
		    transform: (-50%, 0%);
		}
		
		#bannerTit {
		    font-family: 'Righteous', cursive;
		    font-size: 48px;
		}
		
		#bannerSubTit {
		    padding-left: 68px;
		    color: #FB7D71;
		    font-size: 24px;
		}
		
		#bannerInfo {
		    margin: 10px 0px 0px 4px;
		}
		
		#scheduleTit,
		#reviewTit {
		    font-size: 24px;
		}
		
		#scheduleWrap,
		#reviewWrap {
		    margin-top: 80px;
		}
		
		#scheduleTit span,
		#reviewTit span {
		    margin-left: 6px;
		    font-size: 18px;
		    color: #89A0F2;
		}
		
		table {
		    border-collapse: collapse;
		}
		
		.cal_top{
		    display: flex;
		    justify-content: space-between;
		    margin-bottom: 60px;
		    font-size: 30px;
		}
		
		table.calendar{
		    display: inline-table;
		    text-align: left;
		}
		
		table.calendar td{
		    vertical-align: top;
		    border: 1px solid #EAEAEA;
		    width: 116px;
		}
		
		#cal_top_date {
		    font-size: 36px;
		}
		
		#calendarDay {
		    margin-top: 8px;
		    font-size: 18px;
		    color: #535353;
		}
		
		#calendarWrap {
		    display: flex;
		    margin-top: 100px;
		}
		
		#calendarDateWrap {
		    margin-right: 54px;
		    width: 320px;
		    text-align: center;
		}
		
		#calDayName {
		    padding-bottom: 20px;
		}
		
		#reviewBox {
		    margin-right: 50px;
		    width: 364px;
		    height: 424px;
		    border-radius: 5px;
		    position: relative;
		    background: white;
		    box-shadow: rgba(33, 35, 38, 0.1) 0px 10px 10px -10px;
		}
		
		#reviewBoxInner {
		    display: flex;
		    flex-direction: column;
		    width: 364px;
		    height: 200px;
		    border-radius: 10px;
		}
		
		* {
			padding: 0px;
			margin: 0px;
		}
		
		#reviewerDateWrap {
		    display: flex;
		    justify-content: space-between;
		    align-items: center;
		}
		
		#reviewer {
		    font-size: 18px;
		}
		
		#reviewDate {
		    font-size: 12px;
		    color: #c4c4c4;
		}
		
		#review {
		    font-size: 14px;
		    color: #757575;
		}
		
		#reviewerTargetWrap {
		    display: flex;
		}
		
		#reviewBoxWrap {
		    display: flex;
		    width: 1194px;
		}
		
		#reviewMoreBtn {
		    margin: 60px 0px;
		    display: flex;
		    justify-content: flex-end;
		    color:#7AAFFF;
		    cursor: pointer;
		}
		    	
        #logo {
		    z-index: 1;
		   	margin-left: 14px;
		}
		
    	#reviewImg {
			width: 364px;
			height: 190px;
			object-fit: cover;
			border-radius: 5px 5px 0px 0px;
		}
		
		#targetLocation {
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    margin-right: 10px;
		    width: 70px;
		    height: 24px;
		    background-color: #757575;
		    color: white;
		    border-radius: 10px 0px 10px 0px;
		    font-size: 12px;
		    position: absolute;
		    top:2.8%;
		    left:10%;
		    transform: translate(-50%, -50%);
		}
		
		#scheduleTit {
			margin: 80px 0px;
		}
		
		#scheduleWrap {
			margin: 60px 0px;
		}
		
		#demo {
			z-index: 0;
		}
        	
       	.carousel-inner > .carousel-item > img{ 
           	width: 300px; height: 800px;
           	object-fit: cover;
        }
           
        #bannerText {
		    position: absolute;
		    top: 620px;
		    left: 45%;
		    transform: (-50%, 0%);
		}
		
		#bannerTit {
		    font-family: 'Righteous', cursive;
		    font-size: 48px;
		    color: black;
		}
		
		#bannerSubTit {
		    padding-left: 68px;
		    color: #FB7D71;
		    font-size: 24px;
		}
		
		#bannerInfo {
		    margin: 10px 0px 0px 4px;
		    color: #535353;
		}
		
		#targetScopeWrap {
       		display: flex;
       		justify-content: space-between;
       		align-items: center;
       		padding: 8px 20px;
       		width: 100%;
       		border-top: 1px solid #EAEAEA;
       	}
       	
       	#scopeWrap {
       		display: flex;
       		margin-top: auto;
       	}
       	
       	#scope {
       		margin-left: 4px;
       	}
       	
       	#reviewBoxContent {
       		padding: 20px;
       	}
       	
       	#locationWrap {
       		display: flex;
       		align-items: center;
       		margin-bottom: 10px;
       		color: #C4C4C4;
       		font-size: 12px;
       	}
       	
       	#locationImg {
       		margin-right: 4px;
       	}
       	
       	#review {
       		height: 96px;
       	}
       	
       	#reviewTarget {
       		color: #C4C4C4;
       		font-size: 12px;
       	}
       	
	</style> 
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
            		<div id="reviewBox">
                    	<c:if test="${review.images eq null}">
                    		<img src="../images/reviewNullImg.svg" id="reviewImg"/>
                    	</c:if>
                    	<c:if test="${review.images ne null}">
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
                            	<div id="reviewTarget">about ${review.careInfo.sitter.sitter.id}</div>
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
    <div id="footerWrap">
        <div id="footerText">Copyrights © 2021 by 윤김구이. All Rights Reserved.</div>
    </div>
</body>
</html>