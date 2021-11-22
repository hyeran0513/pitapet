<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>돌봄 일지</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <link rel="stylesheet" href="/css/careUpdate.css"/>
    
    <style>
	    #logo {
		    z-index: 1;
		   	margin-left: 14px;
		}
	</style>
</head>

<body>
	<%@include file="../components/header.jsp" %>

    <div id="careDiaryPageWrap">
        <div id="pageTit">돌봄 일지</div>

        <div id="activeWrap">
            <div id="activeTit">활동</div>
            <div id="activeContentWrap">
                <div id="activeContentInner">
                    <div id="activeContentTit">산책</div>
                    <div id="activeContent">
                        <input type="radio" value="1" id="radioText" checked>1번
                        <input type="radio" value="2" id="radioText">2번
                        <input type="radio" value="3" id="radioText">3번 이상
                    </div>
                </div>

                <div id="activeContentInner">
                    <div id="activeContentTit">밥</div>
                    <div id="activeContent">
                        <input type="radio" value="1" id="radioText" checked>적게
                        <input type="radio" value="2" id="radioText">보통
                        <input type="radio" value="3" id="radioText">많이
                    </div>
                </div>

                <div id="activeContentInner">
                    <div id="activeContentTit">배식여부</div>
                    <div id="activeContent">
                        <input type="radio" value="1" id="radioText" checked>유
                        <input type="radio" value="2" id="radioText">무
                    </div>
                </div>

                <div id="activeContentInner">
                    <div id="activeContentTit">목욕여부</div>
                    <div id="activeContent">
                        <input type="radio" value="1" id="radioText" checked>유
                        <input type="radio" value="2" id="radioText">무
                    </div>
                </div>
            </div>
        </div>

        <div id="diaryWrap">
            <div id="diaryTit">
                <input type="text" placeholder="제목을 입력하세요." id="diaryTitText"/>
            </div>
            <div id="diaryContent">
                <textarea placeholder="내용을 입력하세요." id="diaryContentText" style="resize: none;"></textarea>
            </div>
        </div>

        <div id="fileReviewBtnWrap">
            <div id="fileWrap">
                <img src="/images/fileImg.svg" id="fileImg"/>
                <input type="file" id="fileBtn"/>
            </div>
            <button id="detailReviewBtn">리뷰 등록</button>
        </div>
    </div>
    <div id="footerWrap">
        <div id="footerText">Copyrights © 2021 by 윤김구이. All Rights Reserved.</div>
    </div>
</body>
</html>