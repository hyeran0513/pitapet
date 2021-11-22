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
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Righteous&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="/css/header.css" />
<link rel="stylesheet" href="/css/footer.css" />
<link rel="stylesheet" href="/css/likeList.css" />

	<style>
	    #logo {
		    z-index: 1;
		   	margin-left: 14px;
		}
	</style>
</head>

<body>
	<%@include file="../components/header.jsp"%>

	<div id="likeListWrap">
		<div id="pageTit">좋아요 목록</div>

		<c:forEach var="likeSitter" items="${likeSitterLists}">
			<div id="petSitterInfoBox">
				<img src="/images/petSitterNullImg.svg" id="petSitterImg" />
				<div id="petSitterLocation">${likeSitter.sitter.address}</div>
				<div id="likeCountWrap">
					<img src="/images/smallHeart.svg" is="likeCountImg" />
					<div id="likeCount">${likeSitter.like}</div>
				</div>
				<div id="petSitterInfoInner">
					<div id="petSitterNameLikeWrap">
						<div id="petSitterName">${likeSitter.sitter.id}</div>
						<img src="/images/likeOn.svg" is="likeOnImg" />
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
</body>
</html>