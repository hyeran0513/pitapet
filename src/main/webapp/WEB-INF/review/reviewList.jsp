<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
    <title>이용 후기</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Righteous&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    
    <style>
    	@charset "UTF-8";

#headerWrap {
    border-bottom: 1px solid rgba(150, 150, 150, 0.2);
}

#reviewPageWrap {
    margin: 100px auto 0px auto;
    width: 1194px;
}

#pageTit {
    margin-bottom: 100px;
    font-size: 24px;
}

#reviewBox {
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
		height: 96px;
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

#reviewBoxWrap {
    display: flex;
    width: 1460px;
    flex-wrap: wrap;
}

	    #reviewImg {
			width: 364px;
		height: 190px;
		object-fit: cover;
		border-radius: 5px 5px 0px 0px;
		}
		
	
	    #logo {
		    z-index: 1;
		   	margin-left: 14px;
		}
		
		#reviewBoxContent {
    	padding: 20px;
    }
    
    #locationWrap {
     	display: flex;
     	align-items: center;
     	margin: 4px 0px 10px 0px;
     	color: #C4C4C4;
    	font-size: 12px;
    }

 	#locationImg {
       	margin-right: 4px;
   	}
   	
   	#reviewTarget {
      	color: #C4C4C4;
      	font-size: 12px;
    }
    
    #reviewBg {
    	background: #F8F9FA;
    }
    
    #reviewBoxWrap {
    	display: inline-flex;
	        flex-wrap: wrap;
	        width: 100%;
	        border-radius: 5px;
	        gap: 50px;
    }
	</style>
</head>

<body id="reviewBg">
	<%@include file="../components/header.jsp" %>

    <div id="reviewPageWrap">
        <div id="pageTit">이용 후기</div>
        <div id="reviewBoxWrap">
           <c:forEach var="review" items="${reviews}"> 
            <div id="reviewBox">
                <c:if test="${review.images eq null}">
                    	<img src="/images/reviewNullImg.svg" id="reviewImg"/>
                </c:if>
                 <c:if test="${review.images ne null}">
                    	<img src="${review.images[0]}" id="reviewImg"/>
                </c:if>
                <div id="reviewBoxInner">
               		<div id="reviewBoxContent">
                    	<div id="reviewerDateWrap">
                           	<div id="reviewer">${review.careInfo.companion.id} 님</div>
                        	<div id="reviewDate">${fn:split(review.writeDate, ' ')[0]}</div>
                    	</div>
                    	<div id="locationWrap">
                    		<img src="/images/location.svg" id="locationImg"/>
                    		${review.careInfo.sitter.sitter.address}
                    	</div>
                    	<div id="review">${review.content}</div>
                   	</div>
                   	<div id="targetScopeWrap">
                       	<div id="reviewTarget">about ${review.careInfo.sitter.sitter.id}</div>
                       	<div id="scopeWrap">
                           	<img src="../images/star.svg"/>
                           	<div id="scope">${review.rate}</div>
                       	</div>
                   	</div>
                </div>
            </div>
		  </c:forEach>	
        </div>
    </div>
</body>
</html>