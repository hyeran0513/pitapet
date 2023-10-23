$(function() {
	$(".checkbox").click(function() {
		var checkbox = this;
		var petName = $(checkbox).parent().parent().children("#petInfoName").text();
		var petId = "";

		for (key in userPetsJson) {
			if (petName == userPetsJson[key].name) {
				petId = key;
			}
		}

		// alert(petName);

		if (document.getElementById(petName)) {
			var title = document.getElementById(petName);
			title.parentNode.removeChild(title);
		}
		else {
			// <반려동물명> 서비스 리스트 출력
			var newDivElement = document.createElement("div");
			$(newDivElement).html("[ " + petName + " ]의 서비스 리스트 <br>");
			$(newDivElement).attr("id", petName);
			$(newInputElement).css("margin-bottom", "20px");

			// 가능 서비스 리스트 출력
			for (key in ableService) {
				var newInputElement = document.createElement("input");
				$(newInputElement).attr("type", "checkbox");
				$(newInputElement).attr("id", "service");
				$(newInputElement).attr("name", petId);
				$(newInputElement).attr("value", key);
				$(newInputElement).css("margin-bottom", "20px");
				$(newInputElement).css("margin-right", "10px");

				$(newDivElement).append(newInputElement);

				var newTextElement = ableService[key].title + " ";
				$(newDivElement).append(newTextElement);
			}

			$("#petSitterService").append(newDivElement);
		}
	});
});

function reservation() {
	if ( $("input:checkbox[name=pet]:checked").length == 0) {
		alert("돌봄을 원하는 반려동물을 선택해 주세요.");
		return false;
	}
	
	if ( $("input:checkbox[id=service]:checked").length == 0) {
		alert("원하는 서비스를 선택해 주세요.");
		return false;
	}
	
	form.submit();
}