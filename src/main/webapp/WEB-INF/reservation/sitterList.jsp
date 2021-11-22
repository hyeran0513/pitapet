<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>돌보미 조회</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Righteous&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="/css/header.css" />
<link rel="stylesheet" href="/css/footer.css" />
<style>
@charset "UTF-8";

#headerWrap {
	border-bottom: 1px solid rgba(150, 150, 150, 0.2);
}

#sitterListWrap {
	margin: 100px auto 0px auto;
	width: 1194px;
}

#searchToolsWrap {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 100px;
	width: 1194px;
}

#pageTit {
	font-size: 24px;
}

#searchTools {
	display: flex;
	justify-content: space-between;
	align-items: center;
	width: 560px;
}

#choiceBox {
	padding-left: 10px;
	width: 130px;
	height: 40px;
	border-radius: 50px;
	box-shadow: rgb(204, 219, 232) 3px 3px 6px 0px inset,
		rgba(255, 255, 255, 0.5) -3px -3px 6px 1px inset;
	cursor: pointer;
	background: white;
}

#choiceBox:focus {
	outline: none;
}

#searchWrap {
	display: flex;
	justify-content: space-between;
	align-items: center;
	width: 300px;
	height: 40px;
	border-radius: 50px;
	box-shadow: rgb(204, 219, 232) 3px 3px 6px 0px inset,
		rgba(255, 255, 255, 0.5) -3px -3px 6px 1px inset;
	background-color: white;
	border: none;
}

#searchText {
	margin-left: 20px;
	border: none;
}

#searchText:focus {
	outline: none;
}

#searchImg {
	margin-right: 20px;
	width: 16px;
	height: 16px;
	cursor: pointer;
}

#hamburgerBarImg {
	cursor: pointer;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: white;
	min-width: 160px;
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
	transition: all 0.3s cubic-bezier(.25, .8, .25, 1);
	border-radius: 5px;
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	text-align: center;
	display: block;
}

.dropdown-content a:hover {
	background-color: #F1F1F1;
}

.dropdown:hover .dropdown-content {
	display: block;
}

#reviewBox {
	margin-right: 50px;
	width: 364px;
	height: 424px;
	border-radius: 5px;
	position: relative;
	background: white;
	box-shadow: rgba(33, 35, 38, 0.1) 0px 10px 10px -10px;
}

#reviewBoxInner {
	display: flex;
	flex-direction: column;
	width: 364px;
	height: 200px;
	border-radius: 10px;
}

* {
	padding: 0px;
	margin: 0px;
}

#reviewerDateWrap {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

#reviewer {
	font-size: 18px;
}

#reviewDate {
	font-size: 12px;
	color: #c4c4c4;
}

#review {
	font-size: 14px;
	color: #757575;
}

#reviewerTargetWrap {
	display: flex;
}

#reviewBoxWrap {
	display: flex;
	width: 1194px;
}

#reviewMoreBtn {
	margin: 60px 0px;
	display: flex;
	justify-content: flex-end;
	color: #7AAFFF;
	cursor: pointer;
}

#logo {
	z-index: 1;
	margin-left: 14px;
}

#reviewImg {
	width: 364px;
	height: 190px;
	object-fit: cover;
	border-radius: 5px 5px 0px 0px;
}

#targetScopeWrap {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 8px 20px;
	width: 324px;
	border-top: 1px solid #EAEAEA;
}

#scopeWrap {
	display: flex;
	margin-top: auto;
}

#scope {
	margin-left: 4px;
}

#reviewBoxContent {
	padding: 20px;
}

#review {
	height: 96px;
}

#reviewTarget {
	color: #C4C4C4;
	font-size: 12px;
}

#recommendTit {
	margin: 160px 0px -20px 0px;
	font-size: 24px;
}

#petSitterInfoBox {
	position: relative;
	display: flex;
	margin-bottom: 60px;
	padding: 20px;
	width: 1154px;
	box-shadow: rgba(33, 35, 38, 0.1) 0px 10px 10px -10px;
	background: white;
	border-radius: 10px;
	cursor: pointer;
}

#petSitterLocation {
	position: absolute;
	top: 11.6%;
	left: 4.8%;
	transform: translate(-50%, -50%);
	display: flex;
	justify-content: center;
	align-items: center;
	margin-right: 10px;
	width: 70px;
	height: 24px;
	background-color: #757575;
	color: white;
	border-radius: 5px 0px 5px 0px;
	font-size: 12px;
}

#petSitterInfoInner {
	display: flex;
	flex-direction: column;
	margin-left: 20px;
	width: 800px;
	height: 242px;
}

#likeCountWrap {
	position: absolute;
	top: 86%;
	left: 28.6%;
	transform: translate(-50%, -50%);
	display: flex;
	justify-content: center;
	align-items: center;
	height: 14px;
}

#likeCount {
	padding-left: 4px;
	font-size: 12px;
}

#petSitterNameLikeWrap {
	display: flex;
	justify-content: space-between;
	margin-bottom: 4px;
	width: 780px;
	height: 20px;
}

#petSitterName {
	font-size: 18px;
}

#petSitterImg {
	width: 400px;
    height: 300px;
}

#petSitterIntro {
	font-size: 14px;
}

#petSitterService {
	font-size: 14px;
	color: #535353;
}

#caringDateWrap {
	display: flex;
	justify-content: space-between;
	width: 780px;
}

#caringPetsWrap {
	display: flex;
	align-items: center;
	font-size: 14px;
}

#caringPet {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-right: 20px;
	padding: 4px 8px;
	color: #E3C1A2;
	border: 1px solid #E3C1A2;
	border-radius: 50px;
}

#serviceCaringWrap {
	display: flex;
	flex-direction: column;
	margin-top: auto;
}

#petSitterServiceWrap {
	display: flex;
	margin-bottom: 20px;
}

#petSitterService {
	margin-left: 20px;
	font-size: 14px;
	color: #E3C1A2;
}

#serviceTit {
	font-size: 14px;
}

#caringTit {
	font-size: 14px;
	color: #535353;
}

#caringTit {
	font-size: 14px;
	color: #535353;
}

body, ul, li {
	margin: 0;
	padding: 0;
	list-style: none;
}

body {
	position: relative;
}

a {
	color: inherit;
	text-decoration: none;
}

.menu-box-1 {
	position: fixed;
	width: 25.8%;
	height: 19%;
	top: 12%;
	right: -26%;
	border: 1px solid #EAEAEA;
	background: white;
	border-radius: 0px 0px 0px 10px;
	transition: right 1s;
}

.menu-box-1:hover {
	right: 0;
	transition: right 1s;
}

.menu-box-1>ul {
	position: relative;
	width: 100%;
	top: 2%;
}

.menu-box-1 ul>li {
	position: relative;
}

.menu-box-1>ul>li:hover {
	background-color: #F1F1F1;
	color: #535353;
}

.menu-box-1 ul>li>a {
	display: block;
	text-align: center;
	padding: 10px;
}

.menu-box-1 ul>li:hover>a {
	background-color: #F1F1F1;
	color: #535353;
}

.menu-box-1 ul {
	border: 1px solid #EAEAEA;
	background: white;
}

.menu-box-1 ul ul {
	display: none;
	position: absolute;
	width: 50%;
	top: 0%;
	right: 100%;
}

.menu-box-1 ul>li:hover>ul {
	display: block;
}

.button {
	position: absolute;
	top: 1%;
	left: 2%;
	color: white;
	font-size: 20px;
}

#btn {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-left: -50px;
	width: 40px;
	height: 100px;
	background: #7AAFFF;
	border-radius: 5px 0px 0px 5px;
	cursor: pointer;
}

#category {
	display: flex;
	justify-content: center;
	align-items: center;
	width: 500px;
	height: 50px;
	font-size: 18px;
}

#paging {
	display: flex;
	justify-content: center;
	margin-top: 40px;
}

#pagingInner {
	display: flex;
	align-items: center;
}

#pre {
	margin-right: 60px;
}

#numOn {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-right: 40px;
	width: 40px;
	height: 40px;
	border-radius: 50px;
	color: #89A0F2;
	border: 1px solid #89A0F2;
}

#num {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-right: 40px;
	width: 40px;
	height: 40px;
	border-radius: 50px;
	color: #C4C4C4;
	border: 1px solid #C4C4C4;
}

#next {
	margin-left: 20px;
}

#logo {
	z-index: 1;
	margin-left: 14px;
}

#pageBg {
	background: #F8F9FA;
}

#reviewTit {
	margin: 100px 0px 100px 0px;
	font-size: 24px;
}

#locationWrap {
	display: flex;
	align-items: center;
	margin: 4px 0px 10px 0px;
	color: #C4C4C4;
	font-size: 12px;
}

#sitterLocationWrap {
	display: flex;
	align-items: center;
	margin: 4px 0px 20px 0px;
	color: #C4C4C4;
	font-size: 12px;
}

select {
	border: none;
	background: white;
	background: url(../) no-repeat 80% 50%;
}

#petSitterImg {
	width: 354px;
	height: 242px;
	object-fit: cover;
	border-radius: 10px;
}
</style>
</head>

<body id="pageBg">
	<%@include file="../components/header.jsp"%>

	<div id="sitterListWrap">
		<div id="searchToolsWrap">
			<div id="pageTit">돌보미 정보 조회</div>
			<div id="searchTools">
				<select id="choiceBox">
					<option value="city">지역</option>
					<option value="tag">태그</option>
				</select>
				<div id="searchWrap">
					<input type="text" placeholder="검색어를 입력하세요." id="searchText" /> <img
						src="/images/search.svg" id="searchImg" />
				</div>
				<div class="dropdown">
					<img src="/images/hamburgerBar.svg" id="hamburgerBarImg"
						class="dropbtn" />
					<div class="dropdown-content">
						<a href="#">인기순</a> <a href="#">조회순</a>
					</div>
				</div>
			</div>
		</div>
		<c:forEach var="petsitter" items="${petSitterList}">
			<c:url value="/reservation/viewSitterDetail" var="viewUrl">
				<c:param name="sitterId" value="${petsitter.sitter.id}" />
			</c:url>
			<div id="petSitterInfoBox" onClick="location.href='${viewUrl}'">
				<c:if test="${petsitter.sitter.profileImage eq null}">
					<img src="/images/petSitterNullImg.svg" id="petSitterImg" />
				</c:if>
				<c:if test="${petsitter.sitter.profileImage ne null}">
					<img src="${petsitter.sitter.profileImage}" id="petSitterImg" />
				</c:if>
				<div id="likeCountWrap">
					<img src="/images/smallHeart.svg" is="likeCountImg" />
					<div id="likeCount">${petsitter.like}</div>
				</div>
				<div id="petSitterInfoInner">
					<div id="petSitterNameLikeWrap">
						<div id="petSitterName">${petsitter.sitter.id} 반려동물 돌보미</div>

						<c:set var="findCk" value="false" />
						<c:forEach var="likesitter" items="${likeSitters}"
							varStatus="status">
							<c:if test="${findCk == false}">
								<c:choose>
									<c:when
										test="${likesitter.likeSitter.sitter.id == petsitter.sitter.id}">
										<img src="/images/likeOn.svg" is="likeOnImg" />
										<c:set var="findCk" value="true" />
									</c:when>
									<c:otherwise>
										<c:if test="${status.last}">
											<img src="/images/like.svg" is="likeImg" />
										</c:if>
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>
					</div>
					<div id="sitterLocationWrap">
						<img src="/images/location.svg" id="locationImg" />${petsitter.sitter.address}
					</div>
					<div id="petSitterIntro">${petsitter.notes}</div>
					<div id="serviceCaringWrap">
						<div id="caringDateWrap">
							<div id="caringPetsWrap">
								<c:set var="tags" value="${fn:split(petsitter.tag,',')}" />
								<c:forEach var="tag" items="${tags}">
									<div id="caringPet">#${tag}</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>

		<div id="paging">
			<div id="pagingInner">
				<c:set var="currentPage" value="${pageInfo.get(1)}" />
				<c:set var="totalPage" value="${pageInfo.get(0)}" />
				<c:if test="${currentPage > 1}">
					<c:url value='/reservation/listSitter' var="preUrl">
						<c:param name="currentPage" value="${currentPage - 1}" />
					</c:url>
					<a href="${preUrl}" id="pre">이전</a>
				</c:if>
				<c:forEach var="i" begin="1" end="${totalPage}">
					<c:choose>
						<c:when test="${i == currentPage}">
							<div id="numOn">${i}</div>
						</c:when>
						<c:otherwise>
							<c:url value='/reservation/listSitter' var="url">
								<c:param name="currentPage" value="${i}" />
							</c:url>
							<div id="num" onClick="location.href='${url}'">${i}</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${currentPage < totalPage}">
					<c:url value='/reservation/listSitter' var="postUrl">
						<c:param name="currentPage" value="${currentPage + 1}" />
					</c:url>
					<a href="${postUrl}" id="next">다음</a>
				</c:if>
			</div>
		</div>

		<div id="reviewWrap">
			<div id="reviewTit">추천</div>

			<div id="reviewBoxWrap">

				<div id="reviewBox">
					<img src="/images/recommendNullImg.svg" id="reviewImg" />
					<div id="reviewBoxInner">
						<div id="reviewBoxContent">
							<div id="reviewerDateWrap">
								<div id="reviewer">###보호자님</div>
								<div id="reviewDate">2021-11-01</div>
							</div>
							<div id="locationWrap">
								<img src="/images/location.svg" id="locationImg" /> 상월곡
							</div>
							<div id="review">(리뷰 작성)</div>
						</div>
						<div id="targetScopeWrap">
							<div id="reviewTarget">about ###반려동물 돌보미</div>
							<div id="scopeWrap">
								<img src="../images/star.svg" />
								<div id="scope">5.0</div>
							</div>
						</div>
					</div>
				</div>

				<div id="reviewBox">
					<img src="/images/recommendNullImg.svg" id="reviewImg" />
					<div id="reviewBoxInner">
						<div id="reviewBoxContent">
							<div id="reviewerDateWrap">
								<div id="reviewer">###보호자님</div>
								<div id="reviewDate">2021-11-01</div>
							</div>
							<div id="locationWrap">
								<img src="/images/location.svg" id="locationImg" /> 상월곡
							</div>
							<div id="review">(리뷰 작성)</div>
						</div>
						<div id="targetScopeWrap">
							<div id="reviewTarget">about ###반려동물 돌보미</div>
							<div id="scopeWrap">
								<img src="../images/star.svg" />
								<div id="scope">5.0</div>
							</div>
						</div>
					</div>
				</div>

				<div id="reviewBox">
					<img src="/images/recommendNullImg.svg" id="reviewImg" />
					<div id="reviewBoxInner">
						<div id="reviewBoxContent">
							<div id="reviewerDateWrap">
								<div id="reviewer">###보호자님</div>
								<div id="reviewDate">2021-11-01</div>
							</div>
							<div id="locationWrap">
								<img src="/images/location.svg" id="locationImg" /> 상월곡
							</div>
							<div id="review">(리뷰 작성)</div>
						</div>
						<div id="targetScopeWrap">
							<div id="reviewTarget">about ###반려동물 돌보미</div>
							<div id="scopeWrap">
								<img src="../images/star.svg" />
								<div id="scope">5.0</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footerWrap">
		<div id="footerText">Copyrights © 2021 by 윤김구이. All Rights
			Reserved.</div>
	</div>
	<nav class="menu-box-1">
		<div class="button">
			<div id="btn">
				<img src="/images/leftSlide.svg" />
			</div>
		</div>
		<div id="category">
			<div>카테고리</div>
		</div>
		<ul>
			<li><a href="#">대분류</a>
				<ul>
					<li><a href="#">소분류</a></li>
					<li><a href="#">소분류</a></li>
					<li><a href="#">소분류</a></li>
				</ul></li>
			<li><a href="#">대분류</a>
				<ul>
					<li><a href="#">소분류</a></li>
					<li><a href="#">소분류</a></li>
					<li><a href="#">소분류</a></li>
				</ul></li>
			<li><a href="#">대분류</a>
				<ul>
					<li><a href="#">소분류</a></li>
					<li><a href="#">소분류</a></li>
					<li><a href="#">소분류</a></li>
				</ul></li>
		</ul>
	</nav>
</body>
</html>
