<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
    <title>돌보미 정보 등록</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <link rel="stylesheet" href="/css/sitterRegisterForm.css"/>
    
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
        <div id="pageTit">돌보미 정보 등록</div>
        <table>
            <tr>
                <td id="tableTit">인증</td>
                <td id="tableTit"></td>
            </tr>

            <tr>
                <td id="tdTit">이름</td>
                <td><input type="text" placeholder="이름을 입력하세요." id="textDesign" /></td>
            </tr>

            <tr>
                <td id="tdTit">생년월일</td>
                <td><input type="text" placeholder="생년월일 6자리를 입력하세요. ex) 000513" id="textDesign" /></td>
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
                <td><input type="text" placeholder="전화번호를 입력하세요. (숫자만)" id="textDesign" /></td>
            </tr>

            <tr>
                <td id="tdTit">주소</td>
                <td><input type="text" placeholder="주소를 입력하세요." id="textDesign" /></td>
            </tr>

            <tr>
                <td id="tdTit">돌보미 경력</td>
                <td><input type="text" placeholder="돌보미 경력을 입력하세요." id="textDesign" /></td>
            </tr>

            <tr>
                <td id="tdTit">제공 서비스</td>
                <td><input type="text" placeholder="제공 서비스를 입력하세요." id="textDesign" /></td>
            </tr>

            <tr>
                <td id="tdTit">자격증 여부</td>
                <td>
                    <input type="radio" value="0" checked /> 유
                    <input type="radio" value="1" id="radioBox" /> 무
                </td>
            </tr>
        </table>

        <table id="introduceWrap">
            <tr>
                <td id="tableTit">자기소개</td>
            </tr>

            <tr>
                <td>
                    <textarea placeholder="내용을 작성하세요." id="introduceText"></textarea>
                </td>
            </tr>
        </table>

        <div id="fileReviewBtnWrap">
            <div id="fileWrap">
                <img src="../images/fileImg.svg" id="fileImg"/>
                <input type="file" id="fileBtn"/>
            </div>
        </div>

        <table id="infoRegisterWrap">
            <tr>
                <td id="tableTit">정보 등록</td>
                <td id="tableTit"></td>
            </tr>

            <tr>
                <td id="tdTit">정보 공개</td>
                <td>
                    <input type="radio" value="0" checked /> 공개
                    <input type="radio" value="1" id="radioBox" /> 비공개
                </td>
            </tr>

            <tr>
                <td id="tdTit">돌봄 가능한 날짜</td>
                <td><input type="text" placeholder="돌봄 가능한 날짜를 입력하세요." id="textDesign" /></td>
            </tr>

            <tr>
                <td id="tdTit">이용 금액</td>
                <td>
                    <input type="text" placeholder="이용금액(1박케어, 데이케어)을 입력하세요. 예) 32000, 22000" id="textDesign" />
                </td>
            </tr>

            <tr>
                <td id="tdTit">돌봄 가능 반려동물</td>
                <td>
                    <input type="radio" value="0" checked /> 대형견
                    <input type="radio" value="1" id="radioBox" /> 소형견
                    <input type="radio" value="2" id="radioBox" /> 고양이
                </td>
            </tr>

            <tr>
                <td id="tdTit">원하는 검색 태그</td>
                <td><input type="text" placeholder="원하는 검색 태그를 입력하세요." id="textDesign" /></td>
            </tr>

            <tr>
                <td id="tdTit">기타 사항</td>
                <td><input type="text" placeholder="기타 사항을 입력하세요." id="textDesign" /></td>
            </tr>
        </table>

        <div id="btnWrap">
            <button id="sitterRegisterBtn">저장</button>
        </div>
    </div>
    
    <div id="footerWrap">
        <div id="footerText">Copyrights © 2021 by 윤김구이. All Rights Reserved.</div>
    </div>
</body>
</html>