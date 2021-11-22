package controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Care;
import model.dto.Member;
import model.service.CareManager;
import model.service.MemberManager;
import model.service.PetSitterApplicationManager;

public class MemberMyPageController implements Controller{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		HttpSession session = request.getSession();
		MemberManager memberMan = MemberManager.getInstance();
		PetSitterApplicationManager applicationMan = PetSitterApplicationManager.getInstance();
		CareManager careMan = CareManager.getInstance();
		
		// session에 id정보가 없으면 mainpage 호출 리다이렉션
		if(!UserSessionUtils.hasLogined(session)) {
			 return "redirect:/mainpage";
		}
		
		// 마이페이지에 보일 정보 전달
		String userId = UserSessionUtils.getLoginUserId(session);
		
		Member memberInfo = memberMan.findMember(userId);
		String profileImg = memberMan.findProfileAttachment(userId);
		memberInfo.setProfileImage(profileImg);
		request.setAttribute("memberInfo", memberInfo);
		
		String applicationStatus = applicationMan.getApprovalStatus(userId);
		request.setAttribute("applicationStatus", applicationStatus);
		
		List<Care> careList = careMan.findCareSchedules(userId);
		request.setAttribute("careList", careList);
		// System.out.println(careList);
		
		// Sitter에 member 정보 매핑
		if (careList != null) {
			for (Care care:careList) {
				String sitterId = care.getSitter().getSitter().getId();
				care.getSitter().setSitter(memberMan.findMember(sitterId));
			}	
		}
		
        return "/member/memberMyPage.jsp";
    }
}