package controller.mainpage;

import java.util.*;
import com.fasterxml.jackson.databind.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Review;
import model.dto.Care;
import model.dto.Member;
import model.service.CareManager;
import model.service.ReviewManager;

public class MainPageController implements Controller{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		HttpSession session = request.getSession();
		CareManager careMan = CareManager.getInstance();
		ReviewManager reviewMan = ReviewManager.getInstance();
		
		// session에 id정보가 없는지 확인
		if(UserSessionUtils.hasLogined(session)) {
			// 로그인 상태면 돌봄 스케줄 정보 검색해 전달
			Member member = new Member(UserSessionUtils.getLoginUserId(session));
			member.setIdentity((String) session.getAttribute("identity"));
			Map<Integer, Care> scheduleMap = careMan.getCareScheduleMap(member);
			if (scheduleMap != null) {
				ObjectMapper mapper = new ObjectMapper();   
				String schedules = mapper.writeValueAsString(scheduleMap);					
				request.setAttribute("careSchedules", schedules);
			 }
		} else {
			// 로그인 상태가 아니면 방문자인 상태를 전달
			request.setAttribute("isNotLogined", true);
		}
		
		// 메인페이지에 띄울 3개의 랜덤 리뷰 전달
		List<Review> randomReviews = reviewMan.getRandomReviews();
		request.setAttribute("reviews", randomReviews);
		
        return "/mainPage/mainPage.jsp";
    }
}
