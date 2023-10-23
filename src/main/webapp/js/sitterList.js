function search() {
	if (form.searchText.value == "") {
		alert("검색어를 입력하세요.");
		form.searchText.focus();
		return false;
	}
	form.submit();
}