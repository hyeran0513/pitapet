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
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="/css/header.css" />
	<link rel="stylesheet" href="/css/footer.css" />
	<link rel="stylesheet" href="/css/sitterList.css" />
	<script src="/js/sitterList.js"></script>
	<style>
		#reviewSubTit {
			margin-left: 10px;
			font-size: 12px;
			color: #535353;
		}
	</style>
</head>

<body id="pageBg">
	<%@include file="../components/header.jsp"%>

	<div id="sitterListWrap">
		<div id="searchToolsWrap">
			<div id="pageTit">돌보미 정보 조회</div>
				<form name="form" method="GET" action="/reservation/listSitter" onsubmit="return search()">
					<div id="searchTools">
					<select id="choiceBox" name="searchOption">
						<option value="city">지역</option>
						<c:choose>
							<c:when test="${sessionScope.searchOption.get(0) == 'tag'}">
								<option value="tag" selected>태그</option>
							</c:when>
							<c:otherwise>
								<option value="tag">태그</option>
							</c:otherwise>
						</c:choose>
					</select>
					<div id="searchWrap">
						<c:choose>
							<c:when test="${sessionScope.searchOption.get(0) == 'city'
								|| sessionScope.searchOption.get(0) == 'tag'}">
								<input type="text" placeholder="검색어를 입력하세요." id="searchText" 
									name="keyword" value="${sessionScope.searchOption.get(1)}"/>
							</c:when>
							<c:otherwise>
								<input type="text" placeholder="검색어를 입력하세요." id="searchText" name="keyword" />
							</c:otherwise>
						</c:choose>
						<input type="hidden" name="currentPage" value="1" />
						<input type="image" src="/images/search.svg" id="searchImg"/>
					</div>
					</div>
				</form>
		</div>
		<c:if test="${empty petSitterList}">
			검색 결과가 없습니다.
		</c:if>
		<c:forEach var="petsitter" items="${petSitterList}">
			<c:url value="/reservation/viewSitterDetail" var="viewUrl">
				<c:param name="sitterId" value="${petsitter.sitter.id}" />
			</c:url>
			<div id="petSitterInfoBox">
				<img src="${petsitter.sitter.profileImage}" id="petSitterImg" />
				<div id="likeCountWrap">
					<img src="/images/smallHeart.svg" is="likeCountImg" />
					<div id="likeCount">${petsitter.like}</div>
				</div>
				<div id="petSitterInfoInner">
					<c:url value='/like/changeLike' var='addLikeUrl'>
						<c:param name='status' value='add' />
						<c:param name='sitterId' value='${petsitter.sitter.id}' />
					</c:url>
					<c:url value='/like/changeLike' var='cancelLikeUrl'>
						<c:param name='status' value='remove' />
						<c:param name='sitterId' value='${petsitter.sitter.id}' />
					</c:url>
					<div id="petSitterNameLikeWrap">
						<div id="petSitterName" onClick="location.href='${viewUrl}'">${petsitter.sitter.id} 반려동물 돌보미</div>
						<c:if test = "${empty likeSitters}">
							<img src="/images/like.svg" id="likeImg" onClick="location.href='${addLikeUrl}'"/>
						</c:if>
						<c:if test = "${not empty likeSitters}">
							<c:set var="findCk" value="false" />
							<c:forEach var="likesitter" items="${likeSitters}" varStatus="status">
								<c:if test="${findCk == false}">
									<c:choose>
										<c:when
											test="${likesitter.likeSitter.sitter.id == petsitter.sitter.id}">
											<img src="/images/likeOn.svg" id="likeOnImg" onClick="location.href='${cancelLikeUrl}'"/>
											<c:set var="findCk" value="true" />
										</c:when>
										<c:otherwise>
											<c:if test="${status.last}">
												<img src="/images/like.svg" id="likeImg" onClick="location.href='${addLikeUrl}'"/>
											</c:if>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:forEach>
						</c:if>
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
			<div id="reviewTit">추천 <span id="reviewSubTit">회원의 지역(시/도) 및 반려동물을 기준으로 매칭한 추천 돌보미</span></div>
			<div id="reviewBoxWrap">
				<c:if test="${empty recoSitter}">
					매칭되는 추천 돌보미가 없습니다.
				</c:if>
				<c:if test="${!empty recoSitter}">
					<c:url value="/reservation/viewSitterDetail" var="viewUrl">
						<c:param name="sitterId" value="${recoSitter.sitter.id}" />
					</c:url>
					<div id="petSitterInfoBox">
						<img src="${recoSitter.sitter.profileImage}" id="petSitterImg" />
						<div id="likeCountWrap">
							<img src="/images/smallHeart.svg" is="likeCountImg" />
							<div id="likeCount">${recoSitter.like}</div>
						</div>
						<div id="petSitterInfoInner">
							<c:url value='/like/changeLike' var='addLikeUrl'>
								<c:param name='status' value='add' />
								<c:param name='sitterId' value='${recoSitter.sitter.id}' />
							</c:url>
							<c:url value='/like/changeLike' var='cancelLikeUrl'>
								<c:param name='status' value='remove' />
								<c:param name='sitterId' value='${recoSitter.sitter.id}' />
							</c:url>
							<div id="petSitterNameLikeWrap">
								<div id="petSitterName" onClick="location.href='${viewUrl}'">${recoSitter.sitter.id}
									반려동물 돌보미</div>
								<c:if test="${empty likeSitters}">
									<img src="/images/like.svg" id="likeImg"
										onClick="location.href='${addLikeUrl}'" />
								</c:if>
								<c:if test="${not empty likeSitters}">
									<c:set var="findCk" value="false" />
									<c:forEach var="likesitter" items="${likeSitters}"
										varStatus="status">
										<c:if test="${findCk == false}">
											<c:choose>
												<c:when
													test="${likesitter.likeSitter.sitter.id == recoSitter.sitter.id}">
													<img src="/images/likeOn.svg" id="likeOnImg"
														onClick="location.href='${cancelLikeUrl}'" />
													<c:set var="findCk" value="true" />
												</c:when>
												<c:otherwise>
													<c:if test="${status.last}">
														<img src="/images/like.svg" id="likeImg"
															onClick="location.href='${addLikeUrl}'" />
													</c:if>
												</c:otherwise>
											</c:choose>
										</c:if>
									</c:forEach>
								</c:if>
							</div>
							<div id="sitterLocationWrap">
								<img src="/images/location.svg" id="locationImg" />${recoSitter.sitter.address}
							</div>
							<div id="petSitterIntro">${recoSitter.notes}</div>
							<div id="serviceCaringWrap">
								<div id="caringDateWrap">
									<div id="caringPetsWrap">
										<c:set var="tags" value="${fn:split(recoSitter.tag,',')}" />
										<c:forEach var="tag" items="${tags}">
											<div id="caringPet">#${tag}</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	
	<%@include file="../components/footer.jsp" %>
	
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
			<c:set var="category" value="${petKindList[0].largeCategory}"/>
			<c:forEach var="petKind" items="${petKindList}" varStatus="status">
				<c:if test="${status.first || petKind.largeCategory != category}">
					<c:set var="category" value="${petKind.largeCategory}"/>
					<c:if test="${!status.first}">
						</ul></li>
					</c:if>
					<li><a href="#">${category}</a>
					<ul>
				</c:if>
				<li><a href="<c:url value='/reservation/listSitter'>
								<c:param name='searchOption' value='category'/>
								<c:param name='keyword' value='${petKind.id}'/>
								<c:param name='currentPage' value='1' />
								</c:url>">${petKind.smallCategory}</a></li>
				<c:if test="${status.last}">
					</ul></li>
				</c:if>	
			</c:forEach>
		</ul>
	</nav>
</body>
</html>
