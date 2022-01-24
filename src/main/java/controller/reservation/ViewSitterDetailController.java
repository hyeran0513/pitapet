package controller.reservation;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Care;
import model.dto.PetSitter;
import model.dto.Review;
import model.service.CareManager;
import model.service.PetSitterManager;
import model.service.ReviewManager;

public class ViewSitterDetailController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		PetSitterManager sitterMan = PetSitterManager.getInstance();
		CareManager careMan = CareManager.getInstance();
		ReviewManager reviewMan = ReviewManager.getInstance();
		
		String memberId = UserSessionUtils.getLoginUserId(session);
		String sitterId = (String) request.getParameter("sitterId");

		// session에 id정보가 없으면 mainpage 호출 리다이렉션
		if (!UserSessionUtils.hasLogined(session)) {
			return "redirect:/mainpage";
		}

		// 돌보미 상세 정보 전달
		PetSitter sitter = sitterMan.findPetSitter(sitterId);
		request.setAttribute("sitterInfo", sitter);
		boolean isUpdated = sitterMan.updateViews(sitterId);
		if (!isUpdated)
			request.setAttribute("updateFailed", true);

		// 예약 불가능 일자 전달
		Map<String, String> scheduleMap = careMan.getDisableDays(memberId, sitter);
		if (scheduleMap != null) {
			ObjectMapper mapper = new ObjectMapper();
			String schedules = mapper.writeValueAsString(scheduleMap);

			request.setAttribute("schedules", schedules);
		}	

		// 돌보미 후기 전달
		List<Review> reviews = reviewMan.findReviewListOfSitter(sitterId);
		request.setAttribute("reviews", reviews);
		
		// 돌보미와의 돌봄완료 내역 중 리뷰 작성을 안한 내역 전달
		List<Care> careList = careMan.findCareOfDoNotReview(memberId, sitterId);
		request.setAttribute("careListOfReview", careList);

		return "/reservation/sitterDetailView.jsp";
	}
}
