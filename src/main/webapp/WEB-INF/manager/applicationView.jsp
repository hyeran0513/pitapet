<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
    <title>지원 정보</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/header.css"/>
    <link rel="stylesheet" href="../css/footer.css"/>
    <link rel="stylesheet" href="../css/applyView.css"/>
    
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
        <div id="pageTit">지원 정보</div>
        <div id="applyBox">
            <div id="applyImgWrap">
                <img src="../images/applyNullImg.svg" />
                <div id="targetLocation">상월곡</div>
            </div>

            <div id="applyInfo">
                <div id="sitterName">###반려동물 돌보미</div>
                <div id="detailTit">(제목)</div>
                <div id="detailIntro">(소개)</div>
                <div id="serviceCaringWrap">
                    <div id="petSitterServiceWrap">
                        <div id="serviceTit">제공 서비스</div> 
                        <div id="petSitterService">(제공서비스)</div>
                    </div>
                    <div id="caringDateWrap">
                        <div id="caringPetsWrap">
                            <div id="caringTit">돌봄 가능 반려동물</div>
                            <div id="caringPet">대형견</div>
                            <div id="caringPet">소형견</div>
                            <div id="caringPet">고양이</div>
                        </div>
                        <div id="lookUpDate">2021-11-01</div>
                    </div>
                </div>
            </div>
        </div>
        <div id="acceptBtnWrap">
            <button id="acceptBtn">승인하기</button>
        </div>
    </div>
</body>
</html>