function login() {
	if (form.memberId.value == "") {
		alert("사용자 ID를 입력하십시오.");
		form.memberId.focus();
		return false;
	} 
	if (form.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.password.focus();
		return false;
	}		
	form.submit();
}