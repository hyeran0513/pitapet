package controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import model.dto.Care;
import model.dto.Member;
import model.dto.PetKind;
import model.dto.PetSitter;
import model.dto.Service;
import model.service.CareManager;
import model.service.MemberManager;
import model.service.PetManager;
import model.service.PetSitterApplicationManager;
import model.service.PetSitterManager;
import model.service.ServiceManager;

public class MemberMyPageController implements Controller{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		HttpSession session = request.getSession();
		MemberManager memberMan = MemberManager.getInstance();
		PetSitterApplicationManager applicationMan = PetSitterApplicationManager.getInstance();
		CareManager careMan = CareManager.getInstance();
		PetSitterManager sitterMan = PetSitterManager.getInstance();
		PetManager petMan = PetManager.getInstance();
		ServiceManager serviceMan = ServiceManager.getInstance();
		
		// session에 id정보가 없으면 mainpage 호출 리다이렉션
		if(!UserSessionUtils.hasLogined(session)) {
			 return "redirect:/mainpage";
		}
		
		// 마이페이지에 보일 정보 전달
		String userId = UserSessionUtils.getLoginUserId(session);
		
		Member memberInfo = memberMan.findMember(userId);
		if (memberInfo != null) {
			ObjectMapper mapper = new ObjectMapper();
			String memberInfoJson = mapper.writeValueAsString(memberInfo);
			request.setAttribute("memberInfo", memberInfo);
			request.setAttribute("memberInfoJson", memberInfoJson);
		}
		
		String applicationStatus = applicationMan.getApprovalStatus(userId);
		request.setAttribute("applicationStatus", applicationStatus);
		
		List<Care> careList = careMan.getCareSchedules(userId, null);
		if (careList != null) {
			// 돌봄 진행 상태 변경
			careList = careMan.updateCareSchedules(userId, careList);
			
			// JSON 객체 저장
			ObjectMapper mapper = new ObjectMapper();
			String careListJson = mapper.writeValueAsString(careList);
			request.setAttribute("careList", careList);
			request.setAttribute("careListJson", careListJson);
		}
		
		// 돌보미 정보 전달
		PetSitter sitterInfo = sitterMan.findPetSitter(userId);
		System.out.println(sitterInfo);
		if (memberInfo.getIdentity().equals("S") && sitterInfo != null) {
			if (sitterInfo != null) {
				ObjectMapper mapper = new ObjectMapper();
				String sitterInfoJson = mapper.writeValueAsString(sitterInfo);
				request.setAttribute("sitterInfo", sitterInfo);
				request.setAttribute("sitterInfoJson", sitterInfoJson);
			}
			
			//돌봄 가능 종 리스트 검색
			List<PetKind> petKind = petMan.findAblePetKindList(userId);
			if (petKind != null) {
				ObjectMapper mapper = new ObjectMapper();
				String petKindJson = mapper.writeValueAsString(petKind);
				request.setAttribute("petKind", petKind);
				request.setAttribute("petKindJson", petKindJson);
			}
			
			//제공 가능 서비스 리스트 검색
			List<Service> serviceList = serviceMan.findProvideServiceList(userId);
			if (serviceList != null) {
				ObjectMapper mapper = new ObjectMapper();
				String serviceListJson = mapper.writeValueAsString(serviceList);
				request.setAttribute("serviceList", serviceList);
				request.setAttribute("serviceListJson", serviceListJson);
			}
			
			List<Care> sitterCareList = careMan.getSitterCareSchedules(null, userId);
			if (sitterCareList != null) {
				// JSON 객체 저장
				ObjectMapper mapper = new ObjectMapper();
				String sitterCareListJson = mapper.writeValueAsString(sitterCareList);

				request.setAttribute("sitterCareList", sitterCareList);
				request.setAttribute("sitterCareListJson", sitterCareListJson);
			}
		}
		
        return "/member/memberMyPage.jsp";
    }
}