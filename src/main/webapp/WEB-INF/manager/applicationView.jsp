<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>관리자 페이지</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/header.css"/>
    <link rel="stylesheet" href="../css/footer.css"/>
    <link rel="stylesheet" href="/css/applicationView.css"/>
</head>
<body>
    <c:if test="${updateFailed}">
        <script> alert('돌보미 등급 조정에 실패했습니다.'); </script>
    </c:if>
    <div id="headerWrap">
        <div id="headerInner">
            <div id="logoWrap">
                <c:url value="/manager/listSitterApply" var="managerMainUrl"/>
                <img src="/images/adminRank.svg" />
                <div id="logo" onClick="location.href='${managerMainUrl}'">
                    <img src="/images/logo.svg" />
                </div>
            </div>
            <div id="gnb">
                <c:url value='/member/logout' var="url"/>
                <button id="headerLoginBtn" onclick="location.href='${url}'">로그아웃</button>
            </div>
        </div>
    </div>
    <div id="pageWrap">
        <div id="pageTit">지원 정보</div>
        <div class="applicant-info">
            <c:if test="${empty applicantDetail.applicant.profileImage}">
                <img src="/images/applyImage.svg" class="applicant-image"/>
            </c:if>
            <c:if test="${not empty applicantDetail.applicant.profileImage}">
                <img src="${applicantDetail.applicant.profileImage}" class="applicant-image"/>
            </c:if>
            <div class="address-label">${applicantDetail.applicant.address}</div>
            <div class="applicant-details">
                <div class="applicant-details-top">
                  <div class="applicant-id">${applicantDetail.applicant.id}</div>
                  <div class="apply-date">${applicantDetail.applyDate}</div>
                </div>
                <div class="introduction-text">${applicantDetail.introduction}</div>
                <div class="applicant-tags">
                    <div class="tag"># 경력: ${applicantDetail.career}</div>
                    <div class="tag"># 자격증: ${applicantDetail.certification}</div>
                </div>
            </div>
        </div>
        <div class="applicant-actions">
            <div>
	            <div class="qualification-image-label">* 자격증 이미지</div>
	            <c:if test="${empty applicantDetail.images}">
	                <img src="/images/certImg.svg" class="qualification-image"/>
	            </c:if>
	            <c:if test="${not empty applicantDetail.images}">
	                <img src="${applicantDetail.images[0]}" class="qualification-image" />
	            </c:if>
            </div>
            <div id="btnWrap">
                <c:url value='/manager/updateStatus' var="refuseUrl">
                    <c:param name="applyId" value="${applicantDetail.id}" />
                    <c:param name="memberId" value="${applicantDetail.applicant.id}" />
                    <c:param name='status' value='refuse' />
                </c:url>
                <button id="refuseBtn" onclick="location.href='${refuseUrl}'">거절</button>
                <c:url value='/manager/updateStatus' var="approvalUrl">
                    <c:param name="applyId" value="${applicantDetail.id}" />
                    <c:param name="memberId" value="${applicantDetail.applicant.id}" />
                    <c:param name='status' value='approval' />
                </c:url>
                <button id="acceptBtn" onClick="location.href='${approvalUrl}'">승인</button>
            </div>
        </div>
    </div>
</body>
</html>