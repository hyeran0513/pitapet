package controller.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Review;
import model.service.ReviewManager;

public class ListReviewController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ReviewManager reviewMan = ReviewManager.getInstance();
		
		// session에 id정보가 없는지 확인
		if (!UserSessionUtils.hasLogined(session)) {
			// 로그인 상태가 아니면 방문자인 상태를 전달
			request.setAttribute("isNotLogined", true);
		}	
		
		// 모든 리뷰 정보 전달
		List<Review> reviews = reviewMan.findReviewList();
		request.setAttribute("reviews", reviews);
		
		return "/review/reviewList.jsp";
	}
	
}
