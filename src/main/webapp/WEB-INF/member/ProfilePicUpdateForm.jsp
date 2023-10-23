<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
    <title>프로필 사진 변경</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <link rel="stylesheet" href="/css/petUpdateForm.css"/>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
</head>

<body>
	<%@include file="../components/header.jsp" %>

    <div id="pageWrap">
        <div id="pageTit">프로필 사진 변경</div>
        
        <c:if test="${param.updateFailed}">
			<script> alert("프로필 사진 변경에 실패했습니다."); </script>
		</c:if>
        
        <form name="form" method="POST" action="<c:url value='/member/updateProfilePic'/>" enctype="multipart/form-data">
	        <table>
	            <tr>
	            	<td id="tdTit">프로필 사진</td>
	            	<td><input type="file" name="picture" /></td>
	            </tr>
	        </table>
	        
	        <div id="btnWrap">
	            <button type="submit" id="addBtn">변경</button> 
	        </div>
        </form>
    </div>
    
    <%@include file="../components/footer.jsp" %>
</body>
</html>