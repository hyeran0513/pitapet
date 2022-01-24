package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.dto.PetSitterApplication;
import model.service.PetSitterApplicationManager;

public class ViewSitterApplyController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PetSitterApplicationManager appMan = PetSitterApplicationManager.getInstance();
		HttpSession session = request.getSession();
		
		String memberId = UserSessionUtils.getLoginUserId(session);
		
		// 돌보미 지원정보 전달
		PetSitterApplication applicationInfo = appMan.findApplicationByMemberId(memberId);
		request.setAttribute("applicationInfo", applicationInfo);
		
		return "/member/sitterApplyView.jsp";
	}
}
