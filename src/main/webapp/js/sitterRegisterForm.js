function sitterRegister() {
	if ( $("input:checkbox[name=serviceCheck]:checked").length == 0) {
		alert("제공 서비스를 선택해 주세요.");
		return false;
	}
	
	if (document.getElementById("introduceText").value.length == 0) {
		alert("자기소개를 입력해 주세요.");
		return false;
	}
	
	if ( $("input:checkbox[name=ableDate]:checked").length == 0) {
		alert("돌봄 가능 요일을 선택해 주세요.");
		return false;
	}
	
	if (!$("input[name=calculatedPrice]").val()) {
		alert("이용금액을 입력해 주세요.");
		return false;
	}
	
	if ( $("input:checkbox[name=ablePetKind]:checked").length == 0) {
		alert("돌봄 가능 반려동물 종을 선택해 주세요.");
		return false;
	}
	
	if (!$("input[name=tag]").val()) {
		alert("검색 태그를 입력해 주세요.");
		return false;
	}
	
	form.submit();
}