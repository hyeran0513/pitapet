<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
    <title>반려동물 정보</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <link rel="stylesheet" href="/css/petUpdateForm.css"/>
    
    <style>
	    #logo {
		    z-index: 1;
		   	margin-left: 14px;
		}
	</style>
</head>

<body>
	<%@include file="../components/header.jsp" %>

    <div id="pageWrap">
        <div id="pageTit">반려동물 정보</div>
        
        <table>
            <tr>
                <td id="tableTit">반려동물 정보</td>
                <td id="tableTit"></td>
            </tr>

            <tr>
                <td id="tdTit">종류</td>
                <td>
                    <select id="joinPetKind"> 
                        <option>대형견</option> 
                        <option>소형견</option> 
                        <option>고양이</option> 
                    </select>
                </td>
            </tr>

            <tr>
                <td id="tdTit">이름</td>
                <td><input type="text" placeholder="이름을 입력하세요." id="textDesign" /></td>
            </tr>

            <tr>
                <td id="tdTit">생년월일</td>
                <td><input type="text" placeholder="생년월일 6자리를 입력하세요. ex) 000513" id="textDesign" /></td>
            </tr>
        </table>

        <div id="btnWrap">
            <button id="cancelBtn">취소</button>
            <button id="addBtn">추가</button> 
        </div>
    </div>
    
    <div id="footerWrap">
        <div id="footerText">Copyrights © 2021 by 윤김구이. All Rights Reserved.</div>
    </div>
</body>
</html>