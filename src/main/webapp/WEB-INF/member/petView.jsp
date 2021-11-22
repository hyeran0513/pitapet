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
    <link rel="stylesheet" href="/css/petView.css"/>
    
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
                <td id="tableTit">반려동물 정보 추가하기</td>
                <td id="petInfoAddBtnWrap"><button id="petInfoAddBtn">추가하기+</button></td>
            </tr>

            <tr id="petInfoBoxWrap">
                <td id="petInfoBox">
                    <img src="/images/petImg.svg"/>
                    <div id="petInfoWrap">
                        <div id="petName">이름</div>
                        <div id="petBirth">생년월일</div>
                        <div id="petKind">#종류</div>
                    </div>
                </td>
                <td>
                    <img src="/images/delete.svg" id="deleteImg" />
                </td>
            </tr>

            <tr id="petInfoBoxWrap">
                <td id="petInfoBox">
                    <img src="/images/petImg.svg"/>
                    <div id="petInfoWrap">
                        <div id="petName">이름</div>
                        <div id="petBirth">생년월일</div>
                        <div id="petKind">#종류</div>
                    </div>
                </td>
                <td>
                    <img src="/images/delete.svg" id="deleteImg" />
                </td>
            </tr>
        </table>
    </div>
    
    <div id="footerWrap">
        <div id="footerText">Copyrights © 2021 by 윤김구이. All Rights Reserved.</div>
    </div>
</body>
</html>