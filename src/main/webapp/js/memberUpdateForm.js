function update() {
	if (!$("input[name=oldPassword]").val()) {
		alert("기존 비밀번호를 입력해 주세요.");
		return false;
	}
	
	if (!$("input[name=address]").val()) {
		alert("주소를 입력해 주세요.");
		return false;
	}
	
	if (!$("input[name=phone]").val()) {
		alert("번호를 입력해 주세요.");
		return false;
	}
	
	if (!$("input[name=email]").val()) {
		alert("이메일을 입력해 주세요.");
		return false;
	}
	
	form.submit();
}