package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.dto.Member;
import model.service.MemberManager;
import model.service.exception.PasswordMismatchException;

public class UpdateMemberController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberManager memMan = MemberManager.getInstance();
		
		String userId = UserSessionUtils.getLoginUserId(session);

		// 회원정보 수정 form 이동
		if (request.getMethod().equals("GET")) {
			Member memberInfo = memMan.findMember(userId);
			request.setAttribute("memberInfo", memberInfo);

			return "/member/memberUpdateForm.jsp";
		}
		
		// 회원정보 수정 처리
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		// 패스워드를 변경하는 경우와 안하는 경우를 나눠 수정 처리
		if (newPassword == null || "null".equals(newPassword) || newPassword.equals("")) {
			Member updateInfo = new Member(userId, email, phone, address);
			try {
				memMan.update(updateInfo, oldPassword);
			} catch (PasswordMismatchException e) {
				request.setAttribute("updateFailed", true);
				request.setAttribute("exception", e);
				request.setAttribute("updateInfo", updateInfo);
				return "/member/memberUpdateForm.jsp";
			}
		}
		else {
			Member updateInfo = new Member(userId, newPassword, email, phone, address);
			try {
				memMan.update(updateInfo, oldPassword);
			} catch (PasswordMismatchException e) {
				request.setAttribute("updateFailed", true);
				request.setAttribute("exception", e);
				request.setAttribute("updateInfo", updateInfo);
				return "/member/memberUpdateForm.jsp";
			}
		}
		
		return "redirect:/member/memberMyPage";
	}
}
