package controller.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.PetSitterApplicationManager;
import model.service.PetSitterManager;

public class UpdateStatusController implements Controller{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    	PetSitterApplicationManager psApplicationMan = PetSitterApplicationManager.getInstance();
    	PetSitterManager sitterMan = PetSitterManager.getInstance();
    	
    	String applyId = (String) request.getParameter("applyId");
    	String memberId = (String) request.getParameter("memberId");
    	String status = (String) request.getParameter("status");
    	
    	//돌보미 승인 또는 거절 성공여부
    	boolean updateSuccess = psApplicationMan.updateStatus(applyId, memberId, status);
    	
    	if(updateSuccess) {
    		//돌보미 승인
    		if(status.equals("approval")) {
        		int upgradeResult = sitterMan.upgradeSitter(memberId);
        		if(upgradeResult > 0) {
        			return "redirect:/manager/listSitterApply";
        		} else {
        			return "redirect:/manager/viewApply";
        		}
    		} else { //돌보미 거절
    			return "redirect:/manager/listSitterApply";
    		}
    	} else {
    		request.setAttribute("updateFailed", true);
    		return "redirect:/manager/viewApply";
    	}
    }
}
