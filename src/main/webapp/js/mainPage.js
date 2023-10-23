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


document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendar');
	var calendar = new FullCalendar.Calendar(calendarEl, {
		initialView: 'dayGridMonth'
	});
	calendar.render();
	
	var schedules = JSON.parse('${careSchedules}');
	console.log(schedules);
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

    	calendar.addEvent({
    		title: name + status,
    		start: schedules[key].startDate,
    		end: schedules[key].endDate,
    		allDay: true,
    		color: color
    	})
	}
});