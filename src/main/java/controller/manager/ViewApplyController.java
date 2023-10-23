package controller.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dto.PetSitterApplication;
import model.service.PetSitterApplicationManager;

public class ViewApplyController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	PetSitterApplicationManager psApplicationMan = PetSitterApplicationManager.getInstance();
    	String applyId = (String) request.getParameter("applyId");
    	
    	//돌보미 상세 지원내역 가져오기
    	PetSitterApplication applicantDetail = psApplicationMan.findApplication(applyId);
    	request.setAttribute("applicantDetail", applicantDetail);
    	
    	return "/manager/applicationView.jsp";
    }
}
