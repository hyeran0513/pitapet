<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                 <div id="logo" onClick="location.href='${managerMainUrl}'">PIT A PET</div>
              </div>
         
               <div id="gnb">
                  <c:url value='/member/logout' var="url"/>
                   <button id="headerLoginBtn" onclick="location.href='${url}'">로그아웃</button>
               </div>
           </div>
    </div>

    <div id="pageWrap">
        <div id="pageTit">지원 정보</div>
        
        <div id="pageInner">
            <div id="introPage">
                <c:if test="${empty applicantDetail.applicant.profileImage}">
					<img src="/images/applyImage.svg" id="applyImg" />
				</c:if>
				<c:if test="${not empty applicantDetail.applicant.profileImage}">
					<img src="${applicantDetail.applicant.profileImage}" id="applyImg" />
				</c:if>
                <div id="introTit">${applicantDetail.introduction}</div>
                <div id="introText">등록 날짜: ${applicantDetail.applyDate}</div>
            </div>
            <div id="infoPage">
                <div id="sitterName">${applicantDetail.applicant.id}</div>
                <div id="locationWrap">
                    <img src="/images/location.svg" id="locationImg"/>
                    ${applicantDetail.applicant.address}
                </div>

                <div id="subTit">▪ 경력</div>
                <div id="service">${applicantDetail.career}</div>

                <div id="subTit">▪ 자격증</div>
                	<c:if test="${empty applicantDetail.images}">
                    	<img src="/images/certImg.svg" id="certImg"/>
                    </c:if>
                    <c:if test="${not empty applicantDetail.images}">
                    	<img src="${applicantDetail.images[0]}" id="certImg"/>
                    </c:if>
                <div id="certName">${applicantDetail.certification}</div>

                <div id="btnWrap">
                    <c:url value='/manager/updateStatus' var="approvalUrl">
                        <c:param name="applyId" value="${applicantDetail.id}" />
                        <c:param name="memberId" value="${applicantDetail.applicant.id}" />
                        <c:param name='status' value='approval' />
                    </c:url>
                    <button id="acceptBtn" onClick="location.href='${approvalUrl}'">승인</button>
                    <c:url value='/manager/updateStatus' var="refuseUrl">
                        <c:param name="applyId" value="${applicantDetail.id}" />
                        <c:param name="memberId" value="${applicantDetail.applicant.id}" />
                        <c:param name='status' value='refuse' />
                    </c:url>
                    <button id="refuseBtn" onclick="location.href='${refuseUrl}'">거절</button>
                </div>
            </div>
        </div>
    </div>
    
    <%@include file="../components/footer.jsp" %>
</body>
</html>