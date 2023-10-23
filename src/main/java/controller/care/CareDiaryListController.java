package controller.care;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Care;
import model.service.CareManager;

public class CareDiaryListController implements Controller {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		CareManager careMan = CareManager.getInstance();
		
		//session에 id정보가 없으면 mainpage 호출 리다이렉션
		if(!UserSessionUtils.hasLogined(session)) {
			return "redirect:/mainpage";
		}		
		
		int careId = Integer.valueOf(request.getParameter("careId")); 
		Care care = careMan.findCareRecordsByCare(careId);
		request.setAttribute("care", care);
		
		//해당 돌봄내역에 작성한 돌봄일지 개수
		if (care.getCareRecordList() != null)
			request.setAttribute("writeCount", care.getCareRecordList().size());
		
		return "/care/careDiary.jsp";
	}
}
