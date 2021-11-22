package controller.member;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dto.Member;
import model.service.MemberManager;
import model.service.PetSitterManager;
import model.service.exception.ExistingIdException;
import model.service.exception.MemberNotFoundException;
import model.service.exception.PasswordCkMismatchException;
import model.service.exception.PasswordMismatchException;

public class RegisterController implements Controller{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			MemberManager memMan = MemberManager.getInstance();
			Member member = null;

			member = new Member(request.getParameter("id"), request.getParameter("password"),
					request.getParameter("name"), request.getParameter("birth"), request.getParameter("gender"),
					request.getParameter("email"), request.getParameter("phone"), request.getParameter("address"));
			
			memMan.createMember(member);	
			return "/member/loginForm.jsp";
		} catch (ExistingIdException e) {
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
            
			return "/member/registerForm.jsp";			
		}
	}
}


