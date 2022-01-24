function delete_check(petId) {
	if (confirm("해당 반려동물을 정말 삭제하시겠습니까?") == true) {    //확인
		location.href="/pet/deletePet?petId=" + petId;
	} else {   //취소
		return;
	}	
}