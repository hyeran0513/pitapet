<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>반려동물 정보</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
	<link rel="stylesheet" href="/css/petView.css"/>
	<script src="/js/petView.js"></script>
</head>

<body>
	<%@include file="../components/header.jsp" %>

    <div id="pageWrap">
    	<c:if test="${param.deleteFailed}">
			<script> alert("예약 정보가 존재하는 반려동물은 삭제 불가능합니다."); </script>
		</c:if>
        <div id="pageTit">반려동물 정보</div>
        
        <table>
            <tr>
                <td id="tableTit">반려동물 정보 추가하기</td>
                <td id="petInfoAddBtnWrap">
                	<c:url value='/pet/addPet' var="addPetUrl"/>
                	<button id="petInfoAddBtn" onclick="location.href='${addPetUrl}'">추가하기+</button>
                </td>
            </tr>
			
			<c:forEach var="pet" items="${petList}">
	            <tr id="petInfoBoxWrap">
	                <td id="petInfoBox">
		                <c:if test="${pet.petImage eq null}">
							<img src="/images/petImg.svg" id="petImg"/>
						</c:if>
						<c:if test="${pet.petImage ne null}">
							<img src="${pet.petImage}" id="petImg"/>
						</c:if>
	                    <div id="petInfoWrap">
	                        <div id="petName">${pet.name}</div>
	                        <div id="petBirth">${pet.birth}살</div>
	                        <div id="petKind">#${pet.kind.smallCategory}</div>
	                    </div>
	                </td>
	                <td>	
	                    <img src="/images/delete.svg" id="deleteImg" onclick="delete_check('${pet.id}')" />
	                </td>
	            </tr>
         	</c:forEach>
        </table>
    </div>
    
    <%@include file="../components/footer.jsp" %>
</body>
</html>