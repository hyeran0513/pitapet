var today = null;
var year = null;
var month = null;
var firstDay = null;
var lastDay = null;
var $tdDay = null;
var $tdSche = null;
var jsonData = null;

$(document).ready(function() {
    drawCalendar();
    initDate();
    drawDays();
    drawSche();
    $("#movePrevMonth").on("click", function(){movePrevMonth();});
    $("#moveNextMonth").on("click", function(){moveNextMonth();});
});

//Calendar 그리기
function drawCalendar(){
    var setTableHTML = "";
    setTableHTML+='<table class="calendar">';
    setTableHTML+='<tr><th id="calDayName" style="color: #FB7D71;">일</th><th id="calDayName">월</th><th id="calDayName">화</th><th id="calDayName">수</th><th id="calDayName">목</th><th id="calDayName">금</th><th id="calDayName" style="color: #89A0F2;">토</th></tr>';
    for(var i=0;i<6;i++){
        setTableHTML+='<tr height="90">';
        for(var j=0;j<7;j++){
            setTableHTML+='<td style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap">';
            setTableHTML+='    <div class="cal-day"></div>';
            setTableHTML+='    <div class="cal-schedule"></div>';
            setTableHTML+='</td>';
        }
        setTableHTML+='</tr>';
    }
    setTableHTML+='</table>';
    $("#cal_tab").html(setTableHTML);
}

//날짜 초기화
function initDate(){
    week = new Array('일', '월', '화', '수', '목', '금', '토');
    $tdDay = $("td div.cal-day")
    $tdSche = $("td div.cal-schedule")
    dayCount = 0;
    today = new Date();
    year = today.getFullYear();
    month = today.getMonth()+1;
    date = today.getDate();
    dayName = week[today.getDay()];
    if(month < 10){month = "0"+month;}
    firstDay = new Date(year,month-1,1);
    lastDay = new Date(year,month,0);
}

//calendar 날짜표시
function drawDays(){
    $("#cal_top_year").text(year);
    $("#cal_top_month").text(month);
    $("#cal_top_date").text(date);
    $("#cal_top_dayName").text(dayName);
    for(var i=firstDay.getDay();i<firstDay.getDay()+lastDay.getDate();i++){
        $tdDay.eq(i).text(++dayCount);
    }
    for(var i=0;i<42;i+=7){
        $tdDay.eq(i).css("color","#FB7D71");
    }
    for(var i=6;i<42;i+=7){
        $tdDay.eq(i).css("color","#89A0F2");
    }
}

//calendar 월 이동
function movePrevMonth(){
    month--;
    if(month<=0){
        month=12;
        year--;
    }
    if(month<10){
        month=String("0"+month);
    }
    getNewInfo();
    }

function moveNextMonth(){
    month++;
    if(month>12){
        month=1;
        year++;
    }
    if(month<10){
        month=String("0"+month);
    }
    getNewInfo();
}

//정보갱신
function getNewInfo(){
    for(var i=0;i<42;i++){
        $tdDay.eq(i).text("");
        $tdSche.eq(i).text("");
    }
    dayCount=0;
    firstDay = new Date(year,month-1,1);
    lastDay = new Date(year,month,0);
    drawDays();
    drawSche();
}

//2019-08-27 추가본

//데이터 등록
function setData(){
    jsonData = 
    {
        "2019":{
            "07":{
                "17":"제헌절"
            }
            ,"08":{
                "7":"칠석"
                ,"15":"광복절"
                ,"23":"처서"
            }
            ,"09":{
                "13":"추석"
                ,"23":"추분"
            }
        }
    }
}

//스케줄 그리기
function drawSche(){
    setData();
    var dateMatch = null;
    for(var i=firstDay.getDay();i<firstDay.getDay()+lastDay.getDate();i++){
        var txt = "";
        txt =jsonData[year];
        if(txt){
            txt = jsonData[year][month];
            if(txt){
                txt = jsonData[year][month][i];
                dateMatch = firstDay.getDay() + i -1; 
                $tdSche.eq(dateMatch).text(txt);
            }
        }
    }
}

$(function() { 
    var lastScrollTop = 0, delta = 15; $(window).scroll(
        function(event){
            var st = $(this).scrollTop(); if(Math.abs(lastScrollTop - st) <= delta) return; 
            if ((st > lastScrollTop) && (lastScrollTop>0)) { 
                $("#headerWrap").css("border-bottom","1px solid rgba(150, 150, 150, 0.2)"); 
            } else {
                $("#headerWrap").css("border-bottom","none");
				$("#headerWrap").css("background-color","none");
            }
            lastScrollTop = st; 
        }
    ); 
});