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
import model.dto.CareDetails;
import model.dto.Member;
import model.dto.Pet;
import model.service.CareManager;
import model.service.MemberManager;
import model.service.PetManager;
import model.service.ReviewManager;

public class MainPageController implements Controller{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		HttpSession session = request.getSession();
		CareManager careMan = CareManager.getInstance();
		ReviewManager reviewMan = ReviewManager.getInstance();
		MemberManager memMan = MemberManager.getInstance();
		PetManager petMan = PetManager.getInstance();
		
		// session에 id정보가 없는지 확인
		if(UserSessionUtils.hasLogined(session)) {
			// 로그인 상태면 돌봄 스케줄 정보 검색해 전달
			 List<Care> careSchedules = careMan.findCareSchedules(UserSessionUtils.getLoginUserId(session));
			 if(careSchedules != null) {
					Iterator<Care> iterator = careSchedules.iterator();
					Map<Integer, Care> scheduleMap = new HashMap<Integer, Care>();
					while(iterator.hasNext()) {
						Care care = iterator.next();
						ArrayList<CareDetails> CareDetailList = petMan.findCarePetList(care.getId());
						System.out.println(CareDetailList);
						if (CareDetailList != null) {
							care.setCareList(CareDetailList);
						}
						scheduleMap.put(care.getId(), care);
					}
					ObjectMapper mapper = new ObjectMapper();   
					String schedules = mapper.writeValueAsString(scheduleMap);
					
					request.setAttribute("careSchedules", schedules);
			 }
		} else {
			// 로그인 상태가 아니면 방문자인 상태를 전달
			request.setAttribute("isNotLogined", true);
		}
		
		// 메인페이지에 띄울 3개의 랜덤 리뷰 전달
		List<Review> reviews = reviewMan.findReviewList();
		Collections.shuffle(reviews);
		
		List<Review> randomReviews = reviews.subList(0, 3);
		
		if (randomReviews != null) {
			for (Review review : randomReviews) {
				Member sitterMemInfo = memMan.findMember(review.getCareInfo().getSitter().getSitter().getId());
				String[] address = sitterMemInfo.getAddress().split(" ");
				String city = null;
				for (int j = 0; j < address.length; j++) {
					if (address[j].matches("(.*)로")) {
						city = address[j].substring(0, address[j].length() - 1);
					}
				}
				review.getCareInfo().getSitter().getSitter().setAddress(city);
				
				// 파일 이미지 경로 set
				List<String> imgList = reviewMan.findReviewAttachments(review.getCareInfo().getCompanion().getId(), review.getCareInfo().getId());
				review.setImages(imgList);
				// System.out.println(imgList.get(0));
			}
		}
		request.setAttribute("reviews", randomReviews);
		
        return "/mainPage/mainPage.jsp";
    }
}
