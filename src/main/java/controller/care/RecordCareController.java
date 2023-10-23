package controller.care;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Care;
import model.dto.CareDetails;
import model.dto.CareRecord;
import model.service.CareManager;
import model.service.ServiceManager;

public class RecordCareController implements Controller {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ServiceManager serviceMan = ServiceManager.getInstance();
		CareManager careMan = CareManager.getInstance();
		
		// 돌봄일지 작성 form 이동
		if (request.getMethod().equals("GET")) {
			// session에 id정보가 없으면 mainpage 호출 리다이렉션
			if(!UserSessionUtils.hasLogined(session)) {
				return "redirect:/mainpage";
			}
			
			int careId = Integer.parseInt(request.getParameter("careId"));
			List<CareDetails> checkList = serviceMan.findReceiveServiceList(careId);
			request.setAttribute("checkList", checkList);
			
			return "/care/careRecordForm.jsp";
		}
		
		// 일지 추가 처리
		int careId = Integer.parseInt(request.getParameter("careId"));
		CareRecord careRecord = new CareRecord(-1, null, null, request.getParameter("title"), 
				request.getParameter("content"), new Care(careId), null);
		List<CareDetails> careDetails = new ArrayList<CareDetails>();
		String[] recvService = request.getParameterValues("recvService"); // 돌봄 받은 펫들의 id
		for (String recvId : recvService) {
			CareDetails careDetail = new CareDetails(recvId, new Care(careId), null, null);
			careDetails.add(careDetail);
		}
		careRecord.setCheckList(careDetails);
		
		int isCreated = careMan.createCareRecord(careRecord);
		
		if (isCreated == 0) { // care 레코드 생성 실패
			request.setAttribute("recordFailed", true);
			session.setAttribute("recordInfo", careRecord);
			return "redirect:/care/recordCare";
		}
		
		int isUpdated = careMan.updateCareStatusToZ(careId);
		
		if (isUpdated == 0) // 돌봄상태 업데이트 실패
			request.setAttribute("updateFailed", true);
			
		if (session.getAttribute("recordInfo") != null)
			session.removeAttribute("recordInfo");
		
		return "redirect:/care/listCareDiary?careId=" + careId;
	}
}
