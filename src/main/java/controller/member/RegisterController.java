package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dto.Member;
import model.service.MemberManager;
import model.service.exception.ExistingIdException;

public class RegisterController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
	   Member member = null;
	   MemberManager memMan = MemberManager.getInstance();
	   
      try {
         // 전달한 가입 정보 저장
         member = new Member(request.getParameter("id"), request.getParameter("password"),
               request.getParameter("name"), request.getParameter("birth"), request.getParameter("gender"),
               request.getParameter("email"), request.getParameter("phone"), request.getParameter("address"),
               "C", "/images/detailNulImg.svg");
         
         // 새로운 회원 추가
         int createResult = memMan.createMember(member);
         if(createResult > 0)
            return "/member/loginForm.jsp";
         else
            return "/member/registerForm.jsp";
         
      } catch (ExistingIdException e) {
            request.setAttribute("registerFailed", true);
         request.setAttribute("exception", e);
         request.setAttribute("registerInfo", member);            
         return "/member/registerForm.jsp";         
      }
   }
	
}


