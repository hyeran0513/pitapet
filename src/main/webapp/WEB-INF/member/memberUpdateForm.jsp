<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
    <title>내 정보</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <link rel="stylesheet" href="/css/memberUpdateForm.css"/>
    
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
        <div id="pageTit">내 정보</div>
        <table>
            <tr>
                <td id="tableTit">내 정보</td>
                <td id="tableTit"></td>
            </tr>

            <tr>
                <td id="tdTit">이름</td>
                <td><input type="text" placeholder="이름을 입력하세요." id="textDesign" /></td>
            </tr>

            <tr>
                <td id="tdTit">나이</td>
                <td><input type="text" placeholder="나이를 입력하세요." id="textDesign" /></td>
            </tr>

            <tr>
                <td id="tdTit">성별</td>
                <td>
                    <input type="radio" value="0" checked /> 남
                    <input type="radio" value="1" id="radioBox" /> 여
                    <input type="radio" value="2" id="radioBox" /> 비공개
                </td>
            </tr>

            <tr>
                <td id="tdTit">전화번호</td>
                <td><input type="text" placeholder="나이를 입력하세요." id="textDesign" /></td>
            </tr>

            <tr>
                <td id="tdTit">주소</td>
                <td><input type="text" placeholder="주소를 입력하세요." id="textDesign" /></td>
            </tr>
        </table>

        <div id="btnWrap">
            <button id="myInfoSaveBtn">저장</button>
        </div>
    </div>
    
    <div id="footerWrap">
        <div id="footerText">Copyrights © 2021 by 윤김구이. All Rights Reserved.</div>
    </div>
</body>
</html>