$(document).ready(function() { // html이 로드되면 실행됨
	$("#memberBtn").click(searchMemberPageInfo);   
	$("#sitterBtn").click(searchSitterPageInfo); 
});

function searchMemberPageInfo() {
	location.href="/member/memberMyPage";
}

function searchSitterPageInfo() {
	if (sitterInfoJson == null) {
		alert("돌보미 정보 등록이 필요합니다");
		return;
	}
	
	$("#reservationInfoWrap").empty();
	$("#reservationInfoWrap").append("<img src='/images/sole.svg'/>  ");
	$("#reservationInfoWrap").append("예약 정보");
	$("#reservationInfoWrap").append("<br/><br/><br/>");
	var careTableElement = document.createElement("table");
	$(careTableElement).attr("id", "infoTable");
	var careTrElement = document.createElement("tr");
	var careThElement = document.createElement("th");
	$(careThElement).html("이름");
	$(careTrElement).append(careThElement);
	var careThElement2 = document.createElement("th");
	$(careThElement2).html("예약일");
	$(careTrElement).append(careThElement2);
	var careThElement3 = document.createElement("th");
	$(careThElement3).html("완료상태");
	$(careTrElement).append(careThElement3);
	var careThElement4 = document.createElement("th");
	$(careTrElement).append(careThElement4);
	$(careTableElement).append(careTrElement);
	
	for (key in sitterCareListJson) {
		if (sitterCareListJson[key].status == "X") {
			var careTrElement2 = document.createElement("tr");
			var careTdElement = document.createElement("td");
			$(careTdElement).html(sitterCareListJson[key].companion.name);
			$(careTrElement2).append(careTdElement);
			var careTdElement2 = document.createElement("td");
			$(careTdElement2).html(sitterCareListJson[key].startDate.split(" ")[0] + " ~ " + sitterCareListJson[key].endDate.split(" ")[0]);
			$(careTrElement2).append(careTdElement2);
			var careTdElement3 = document.createElement("td");
			$(careTdElement3).html("<button id=\"rezCompleteBtn\" onClick=\"location.href=\'/reservation/viewReservation?careId=" + sitterCareListJson[key].id +"'\">" + "예약완료" + "</button>");
			$(careTrElement2).append(careTdElement3);
			var careTdElement4 = document.createElement("td");
			// $(careTdElement4).html("<button id=\"cancelBtn\" onClick=\"/reservation/cancelReservation?careId=" + sitterCareListJson[key].id +"\">" + "취소하기" + "</button>");
			$(careTrElement2).append(careTdElement4);
			$(careTableElement).append(careTrElement2);
		}
		if (sitterCareListJson[key].status == "Y") {
			var careTrElement2 = document.createElement("tr");
			var careTdElement = document.createElement("td");
			$(careTdElement).html(sitterCareListJson[key].companion.name);
			$(careTrElement2).append(careTdElement);
			var careTdElement2 = document.createElement("td");
			$(careTdElement2).html(sitterCareListJson[key].startDate.split(" ")[0] + " ~ " + sitterCareListJson[key].endDate.split(" ")[0]);
			$(careTrElement2).append(careTdElement2);
			var careTdElement3 = document.createElement("td");
			$(careTdElement3).html("<button id=\"rezProceedingBtn\" onClick=\"location.href=\'/reservation/viewReservation?careId=" + sitterCareListJson[key].id +"'\">" + "진행중" + "</button>");
			$(careTrElement2).append(careTdElement3);
			var careTdElement4 = document.createElement("td");
			$(careTdElement4).html("<button id=\"careBtn\" onClick=\"location.href='/care/listCareDiary?careId=" + sitterCareListJson[key].id +"'\">" + "돌봄일지" + "</button>");
			$(careTrElement2).append(careTdElement4);
			$(careTableElement).append(careTrElement2);
		}
		if (sitterCareListJson[key].status == "Z") {
			var careTrElement2 = document.createElement("tr");
			var careTdElement = document.createElement("td");
			$(careTdElement).html(sitterCareListJson[key].companion.name);
			$(careTrElement2).append(careTdElement);
			var careTdElement2 = document.createElement("td");
			$(careTdElement2).html(sitterCareListJson[key].startDate.split(" ")[0] + " ~ " + sitterCareListJson[key].endDate.split(" ")[0]);
			$(careTrElement2).append(careTdElement2);
			var careTdElement3 = document.createElement("td");
			$(careTdElement3).html("<button id=\"careCompleteBtn\" onClick=\"location.href=\'/reservation/viewReservation?careId=" + sitterCareListJson[key].id +"'\">" + "돌봄완료" + "</button>");
			$(careTrElement2).append(careTdElement3);
			var careTdElement4 = document.createElement("td");
			$(careTdElement4).html("<button id=\"careBtn\" onClick=\"location.href='/care/listCareDiary?careId=" + sitterCareListJson[key].id +"'\">" + "돌봄일지" + "</button>");
			$(careTrElement2).append(careTdElement4);
			$(careTableElement).append(careTrElement2);
		}
	}
	$("#reservationInfoWrap").append(careTableElement);
	
	var publicStatus = sitterInfoJson.publicStatus;
	var ableDate = sitterInfoJson.ableDate;
	var calculatedPrice = sitterInfoJson.calculatedPrice;
	var tag = sitterInfoJson.tag;
	var notes = sitterInfoJson.notes;
	
	$("#reservationInfoWrap").append("<br/><br/><br/><br/><br/>");
	$("#reservationInfoWrap").append("<img src='/images/sole.svg'/>  ");
	$("#reservationInfoWrap").append("돌보미 정보");
	$("#reservationInfoWrap").append("<br/><br/><br/>");
	var newTableElement = document.createElement("table");
	$(newTableElement).attr("id", "infoTable");
	var newTrElement = document.createElement("tr");
	var newThElement = document.createElement("th");
	$(newThElement).html("정보 공개 상태");
	$(newTrElement).append(newThElement);
	var newThElement2 = document.createElement("th");
	$(newThElement2).html("가능 요일");
	$(newTrElement).append(newThElement2);
	var newThElement3 = document.createElement("th");
	$(newThElement3).html("데이케어, n박케어 요금");
	$(newTrElement).append(newThElement3);
	var newThElement4 = document.createElement("th");
	$(newThElement4).html("태그");
	$(newTrElement).append(newThElement4);
	var newThElement5 = document.createElement("th");
	$(newThElement5).html("소개글");
	$(newTrElement).append(newThElement5);
	$(newTableElement).append(newTrElement);
	
	var newTrElement2 = document.createElement("tr");
	var newTdElement = document.createElement("td");
	if (publicStatus == "Y") {
		$(newTdElement).html("공개");
	}
	else {
		$(newTdElement).html("비공개");
	}
	$(newTrElement2).append(newTdElement);
	var newTdElement2 = document.createElement("td");
	var ableDateBin = ableDate.charCodeAt(0).toString(2);
	console.log(ableDateBin);
	var ableDate = ""
	if (ableDateBin[0] == 1)
		ableDate += "월 ";
	if (ableDateBin[1] == 1)
		ableDate += "화 ";
	if (ableDateBin[2] == 1)
		ableDate += "수 ";
	if (ableDateBin[3] == 1)
		ableDate += "목 ";
	if (ableDateBin[4] == 1)
		ableDate += "금 ";
	if (ableDateBin[5] == 1)
		ableDate += "토 ";
	if (ableDateBin[6] == 1)
		ableDate += "일 ";
	$(newTdElement2).html(ableDate);
	$(newTrElement2).append(newTdElement2);
	var newTdElement3 = document.createElement("td");
	$(newTdElement3).html(calculatedPrice);
	$(newTrElement2).append(newTdElement3);
	var newTdElement4 = document.createElement("td");
	$(newTdElement4).html(tag);
	$(newTrElement2).append(newTdElement4);
	var newTdElement5 = document.createElement("td");
	$(newTdElement5).html(notes);
	$(newTrElement2).append(newTdElement5);
	$(newTableElement).append(newTrElement2);
	$("#reservationInfoWrap").append(newTableElement);
	
	// 돌봄 가능한 펫 정보
	$("#reservationInfoWrap").append("<br/><br/><br/><br/><br/>");
	$("#reservationInfoWrap").append("<img src='/images/sole.svg'/>  ");
	$("#reservationInfoWrap").append("돌봄 가능한 반려동물 정보");
	$("#reservationInfoWrap").append("<br/><br/><br/>");
	
	var newTableElement2 = document.createElement("table");
	$(newTableElement2).attr("id", "infoTable2");
	var newTrElement3 = document.createElement("tr");
	var newThElement6 = document.createElement("th");
	$(newThElement6).html("대분류");
	$(newTrElement3).append(newThElement6);
	var newThElement7 = document.createElement("th");
	$(newThElement7).html("소분류");
	$(newTrElement3).append(newThElement7);
	$(newTableElement2).append(newTrElement3);
	
	for (key in petKindJson) {
		var newTrElement4 = document.createElement("tr");
		var newTdElement6 = document.createElement("td");
		$(newTdElement6).html(petKindJson[key].largeCategory);
		$(newTrElement4).append(newTdElement6);
		var newTdElement7 = document.createElement("td");
		$(newTdElement7).html(petKindJson[key].smallCategory);
		$(newTrElement4).append(newTdElement7);
		$(newTableElement2).append(newTrElement4);
	}
	
	$("#reservationInfoWrap").append(newTableElement2);
	
	// 가능한 서비스 정보
	$("#reservationInfoWrap").append("<br/><br/><br/><br/><br/>");
	$("#reservationInfoWrap").append("<img src='/images/sole.svg'/>  ");
	$("#reservationInfoWrap").append("가능한 서비스 정보");
	$("#reservationInfoWrap").append("<br/><br/><br/>");
	
	var newTableElement3 = document.createElement("table");
	$(newTableElement3).attr("id", "infoTable3");
	var newTrElement4 = document.createElement("tr");
	var newThElement7 = document.createElement("th");
	$(newThElement7).html("서비스");
	$(newTrElement4).append(newThElement7);
	var newThElement8 = document.createElement("th");
	$(newThElement8).html("내용");
	$(newTrElement4).append(newThElement8);
	$(newTableElement3).append(newTrElement4);
	
	for (key in serviceListJson) {
		var newTrElement5 = document.createElement("tr");
		var newTdElement7 = document.createElement("td");
		$(newTdElement7).html(serviceListJson[key].title);
		$(newTrElement5).append(newTdElement7);
		var newTdElement8 = document.createElement("td");
		$(newTdElement8).html(serviceListJson[key].content);
		$(newTrElement5).append(newTdElement8);
		$(newTableElement3).append(newTrElement5);
	}
	
	$("#reservationInfoWrap").append(newTableElement3);
}