package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.dto.PetSitterApplication;
import model.service.PetSitterApplicationManager;


public class ApplySitterController implements Controller{
	 
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 HttpSession session = request.getSession();
		 PetSitterApplication applicationInfo = null;
		 String memberId = UserSessionUtils.getLoginUserId(session);
		
		 if (request.getMethod().equals("GET")) {
				return "/member/sitterApplyForm.jsp";
		}
		 
		 try {
	         PetSitterApplicationManager appMan = PetSitterApplicationManager.getInstance();

	         //돌보미 지원을 위한 객체
	         applicationInfo = new PetSitterApplication(
	               request.getParameter("career"), request.getParameter("certification"), 
	               request.getParameter("introduction"), "X");
	         
	         //돌보미 지원 성공 여부
	         boolean isApply = appMan.addApplication(memberId, applicationInfo);
	         if(isApply)
	            return "redirect:/member/memberMyPage";
	         else
	            return "redirect:/member/applySitter";
	      } catch (Exception e) {
	         request.setAttribute("applyFailed", true);         
	         return "redirect:/member/applySitter";         
	      }
	 }
	
}

