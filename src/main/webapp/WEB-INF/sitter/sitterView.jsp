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
    <link rel="stylesheet" href="/css/sitterView.css"/>
    
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
                <td>(이름)</td>
            </tr>

            <tr>
                <td id="tdTit">생년월일</td>
                <td>(생년월일)</td>
            </tr>

            <tr>
                <td id="tdTit">성별</td>
                <td>(성별)</td>
            </tr>

            <tr>
                <td id="tdTit">전화번호</td>
                <td>(전화번호)</td>
            </tr>

            <tr>
                <td id="tdTit">주소</td>
                <td>(주소)</td>
            </tr>

            <tr>
                <td id="tdTit">돌보미 경력</td>
                <td>(돌보미 경력)</td>
            </tr>

            <tr>
                <td id="tdTit">제공 서비스</td>
                <td>(제공 서비스)</td>
            </tr>

            <tr>
                <td id="tdTit">자격증 여부</td>
                <td>(자격증 여부)</td>
            </tr>
        </table>

        <table id="introduceWrap">
            <tr>
                <td id="tableTit">자기소개</td>
            </tr>

            <tr>
                <td>(내용)</td>
            </tr>
        </table>

        <div id="fileReviewBtnWrap">
            <div id="fileWrap">
                <img src="/images/fileImg.svg" id="fileImg"/>
                (파일 다운링크 출력)
            </div>
        </div>

        <table id="infoRegisterWrap">
            <tr>
                <td id="tableTit">정보 등록</td>
                <td id="tableTit"></td>
            </tr>

            <tr>
                <td id="tdTit">정보 공개</td>
                <td>(공개 여부)</td>
            </tr>

            <tr>
                <td id="tdTit">돌봄 가능한 날짜</td>
                <td>(돌봄 가능한 날짜)</td>
            </tr>

            <tr>
                <td id="tdTit">이용 금액</td>
                <td>(이용 금액)</td>
            </tr>

            <tr>
                <td id="tdTit">돌봄 가능 반려동물</td>
                <td>(돌봄 가능 반려동물)</td>
            </tr>

            <tr>
                <td id="tdTit">원하는 검색 태그</td>
                <td>(원하는 검색 태그)</td>
            </tr>

            <tr>
                <td id="tdTit">기타 사항</td>
                <td>(기타 사항)</td>
            </tr>
        </table>

        <div id="btnWrap">
            <button id="reviseBtn">수정</button>
        </div>
    </div>
    
    <div id="footerWrap">
        <div id="footerText">Copyrights © 2021 by 윤김구이. All Rights Reserved.</div>
    </div>
</body>
</html>