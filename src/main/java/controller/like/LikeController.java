package controller.like;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.service.LikeListManager;

public class LikeController implements Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		LikeListManager likeListMan = LikeListManager.getInstance();
		
		String status = (String) request.getParameter("status");
		String sitterId = (String) request.getParameter("sitterId");
		String memberId = UserSessionUtils.getLoginUserId(session);

		// 좋아요 레코드 추가 또는 삭제
		boolean isSuccessed = likeListMan.addOrRemove(memberId, sitterId, status);
		if (!isSuccessed) {
			request.setAttribute(status + "Failed", true);
		}
		
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
