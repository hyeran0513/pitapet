<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
    <title>관리자 페이지</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/header.css"/>
    <link rel="stylesheet" href="../css/footer.css"/>
    <link rel="stylesheet" href="../css/sitterApply.css"/>
    
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
        <div id="pageTit">지원사항 목록</div>

        <table>
            <tr>
                <th>아이디</th>
                <th>신청일</th>
                <th></th>
            </tr>
            <tr>
                <td>pitapet123</td>
                <td>2021-09-19 ~ 2021-09-20</td>
                <td><button id="applyDetailBtn">자세히 보기</button></td>
            </tr>
            <tr>
                <td>pitapet123</td>
                <td>2021-09-19 ~ 2021-09-20</td>
                <td><button id="applyDetailBtn">자세히 보기</button></td>
            </tr>
            <tr>
                <td>pitapet123</td>
                <td>2021-09-19 ~ 2021-09-20</td>
                <td><button id="applyDetailBtn">자세히 보기</button></td>
            </tr>
        </table>
    </div>
</body>
</html>