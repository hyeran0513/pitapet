package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.MemberManager;
import model.dto.Member;
import model.service.exception.MemberNotFoundException;
import model.service.exception.PasswordMismatchException;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	MemberManager memMan = MemberManager.getInstance();
    	String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		
		try {
			// 모델에 로그인 처리를 위임
			Member member = memMan.login(memberId, password);
	
			// 세션에 사용자 아이디, 등급 저장
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, memberId);
            session.setAttribute("identity", member.getIdentity());
            
            // 로그인한 회원이 매니저 등급일 경우
            if(member.getIdentity().equals("M"))
            	return "redirect:/manager/listSitterApply";
            else
            	return "redirect:/mainpage";			
		} catch (MemberNotFoundException e) { //입력한 id에 해당하는 회원이 없는 경우
            request.setAttribute("loginIdFailed", true);
			request.setAttribute("exception", e);
            
			return "/member/loginForm.jsp";			
		} catch (PasswordMismatchException e) { //입력한 id와 password가 맞지 않을 경우
            request.setAttribute("loginPwFailed", true);
			request.setAttribute("exception", e);
           
			return "/member/loginForm.jsp";			
		}		
    }
}

