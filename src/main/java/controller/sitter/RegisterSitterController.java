package controller.sitter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.PetKind;
import model.dto.PetSitter;
import model.dto.PetSitterApplication;
import model.dto.Service;
import model.service.PetManager;
import model.service.PetSitterApplicationManager;
import model.service.PetSitterManager;
import model.service.ServiceManager;

public class RegisterSitterController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		PetSitterApplicationManager appMan = PetSitterApplicationManager.getInstance();
		PetSitterManager sitterMan = PetSitterManager.getInstance();
		ServiceManager servMan = ServiceManager.getInstance();
		PetManager petMan = PetManager.getInstance();
		
		String memberId = UserSessionUtils.getLoginUserId(session);
		PetSitter sitter = null;
		
		// 돌보미 등록 form 이동
		if (request.getMethod().equals("GET")) {
			 PetSitterApplication applicationInfo = appMan.findApplicationByMemberId(memberId);
			 List<Service> serviceList = servMan.findAllServiceList();
			 List<PetKind> petKindList = petMan.findAllPetKindList();
			 
			 request.setAttribute("petKindList", petKindList);
			 request.setAttribute("serviceList", serviceList);
			 request.setAttribute("applicationInfo", applicationInfo);
			 
			 return "/sitter/sitterRegisterForm.jsp";
		}
		
		// 돌보미 등록 처리
		try {
			// 지원정보 자기소개 업데이트
			appMan.updateApplcationIntroduction(memberId, request.getParameter("introduction"));
			
			// petSitter 레코드 생성
			PetSitterApplication applicationInfo = appMan.findApplicationByMemberId(memberId);
			
			sitter = new PetSitter(request.getParameter("publicStatus"), null, request.getParameter("calculatedPrice")
					, request.getParameter("tag"), request.getParameter("notes"));
			
	        boolean isRegister = sitterMan.createSitter(memberId, sitter, applicationInfo.getId(), request.getParameterValues("ableDate"));
	        if (isRegister) {
	        	// 서비스 추가
	        	String[] service = request.getParameterValues("serviceCheck");
	 			int isServiceAdded = servMan.addProvideService(service, memberId);
	 			if (isServiceAdded > 0) {
	 				// 돌봄 가능 반려동물 추가
	 				String[] ablePetKind = request.getParameterValues("ablePetKind");
	 				int isAblePetKindAdded = petMan.addAblePetKind(memberId, ablePetKind);
	 				if (isAblePetKindAdded > 0)
	 					return "redirect:/member/memberMyPage";
	 				else 
	 					return "redirect:/petSitter/registerSitter";
	 			}
	 			else
	 				return "redirect:/petSitter/registerSitter";
	        }
	            
	        else
	        	return "redirect:/petSitter/registerSitter";
	      } catch (Exception e) {
		        request.setAttribute("registerFailed", true);         
		        return "redirect:/petSitter/registerSitter";         
	      }
		
	}
}
