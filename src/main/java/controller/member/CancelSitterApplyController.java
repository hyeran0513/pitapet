package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.PetSitterApplicationManager;

public class CancelSitterApplyController implements Controller{
	 
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PetSitterApplicationManager appMan = PetSitterApplicationManager.getInstance();
		String applyId = request.getParameter("applyId");
		
		//돌보미 지원 취소의 성공 여부
		boolean isCancel = appMan.cancelApplication(applyId);
		
		try {
			if(isCancel) {
	            return "redirect:/member/memberMyPage"; 
			} 
	         else
	            return "redirect:/member/viewSitterApply";
	      } catch (Exception e) {
	         request.setAttribute("cancelFailed", true);         
	         return "redirect:/member/viewSitterApply";         
	      }
	}
}
