<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>좋아요</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="/css/header.css" />
	<link rel="stylesheet" href="/css/footer.css" />
	<link rel="stylesheet" href="/css/likeList.css" />
</head>

<body id="pageBg">
	<%@include file="../components/header.jsp"%>

	<div id="likeListWrap">
		<div id="pageTit">좋아요 목록</div>

		<c:forEach var="likeSitter" items="${likeSitterLists}">
			<c:url value="/reservation/viewSitterDetail" var="viewUrl">
				<c:param name="sitterId" value="${likeSitter.sitter.id}" />
			</c:url>
			
			<div id="petSitterInfoBox">
				<img src="${likeSitter.sitter.profileImage}" id="petSitterImg" />
				<div id="petSitterLocation">${likeSitter.sitter.address}</div>
				
				<div id="likeCountWrap">
					<img src="/images/smallHeart.svg" is="likeCountImg" />
					<div id="likeCount">${likeSitter.like}</div>
				</div>
				
				<div id="petSitterInfoInner">
					<c:url value='/like/changeLike' var='addLikeUrl'>
						<c:param name='status' value='add' />
						<c:param name='sitterId' value='${likeSitter.sitter.id}' />
					</c:url>
					
					<c:url value='/like/changeLike' var='cancelLikeUrl'>
						<c:param name='status' value='remove' />
						<c:param name='sitterId' value='${likeSitter.sitter.id}' />
					</c:url>
					
					<div id="petSitterNameLikeWrap">
						<div id="petSitterName" onClick="location.href='${viewUrl}'">${likeSitter.sitter.id}</div>
						<c:set var="likeCk" value="true" />
						<c:choose>
						  <c:when test="${likeCk eq 'true'}">
							<img src="/images/likeOn.svg" id="likeOnImg" onClick="location.href='${cancelLikeUrl}'"/>
							<c:set var="findCk" value="false" />
						  </c:when>	
						  <c:otherwise>
						    <img src="/images/like.svg" id="likeImg" onClick="location.href='${addLikeUrl}'"/>
							<c:set var="findCk" value="true" />
						  </c:otherwise>
						</c:choose>
					</div>
					
					<div id="petSitterIntro">${likeSitter.notes}</div>
					
					<div id="serviceCaringWrap">
						<div id="caringDateWrap">
							<div id="caringPetsWrap">
								<c:set var="tags" value="${fn:split(likeSitter.tag,',')}" />
								<c:forEach var="tag" items="${tags}">
									<div id="caringPet">#${tag}</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	
	<%@include file="../components/footer.jsp" %>
</body>
</html>