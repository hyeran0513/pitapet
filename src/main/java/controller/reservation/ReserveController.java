package controller.reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Care;
import model.dto.CareDetails;
import model.dto.Member;
import model.dto.Pet;
import model.dto.PetSitter;
import model.dto.Service;
import model.service.CareManager;
import model.service.PetManager;
import model.service.PetSitterManager;
import model.service.ServiceManager;

public class ReserveController implements Controller {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		ServiceManager serviceMan = ServiceManager.getInstance();
		
		String sitterId = (String) request.getParameter("sitterId");
		String memberId = UserSessionUtils.getLoginUserId(session);

		// 예약 form 이동
		if (request.getMethod().equals("GET")) {
			// session에 id정보가 없는지 확인
			if (!UserSessionUtils.hasLogined(session)) {
				// 로그인 상태가 아니면 방문자인 상태를 전달
				request.setAttribute("isNotLogined", true);
			}
			
			PetSitterManager sitterMan = PetSitterManager.getInstance();
			PetManager petMan = PetManager.getInstance();
			
			// 펫시터 정보
			PetSitter petsitterInfo = sitterMan.findPetSitter(sitterId);
			request.setAttribute("petsitterInfo", petsitterInfo);
			List<Service> ableService = petsitterInfo.getServices();
			if (ableService != null) {
				Map<String, Service> serviceMap = serviceMan.getServiceMap(ableService);
				ObjectMapper mapper = new ObjectMapper();
				String services = mapper.writeValueAsString(serviceMap);
				request.setAttribute("ableService", services);
			}
			
			// 로그인한 유저의 반려동물 리스트 js에서 사용하기 위해 JSON 객체로 저장
			Map<String, Pet> ablePetMap = petMan.getAbleCarePetMap(memberId, sitterId);
			System.out.println(ablePetMap);
			if (ablePetMap != null) {
				ObjectMapper mapper = new ObjectMapper();
				String pets = mapper.writeValueAsString(ablePetMap);
				request.setAttribute("userPetsMap", ablePetMap);
				request.setAttribute("userPetsJson", pets);
			}

			return "/reservation/reservationForm.jsp";
		}

		// 예약 처리
		CareManager careMan = CareManager.getInstance();

		// 예약 정보 저장
		Care care = new Care(request.getParameter("fromDate"), request.getParameter("toDate"), 
				Integer.parseInt(request.getParameter("totalPrice")), request.getParameter("cautionText"), 
				"X", null, new Member(memberId), new PetSitter(new Member(sitterId)));
		List<CareDetails> careDetails = new ArrayList<CareDetails>();
		String[] pets = request.getParameterValues("pet"); // 돌봄 받을 펫들의 id
		for (String petId : pets) {
			String[] services = request.getParameterValues(petId); // 돌봄 받을 펫의 요청 서비스들의 id
			for (String serviceId : services) {
				CareDetails careDetail = new CareDetails(null, new Care(), 
						new Service(serviceId), new Pet(petId));
				careDetails.add(careDetail);
			}
		}
		care.setCareList(careDetails);
		
		// 예약 정보 추가 처리
		int isCreated = careMan.createCare(care);
			
		if (isCreated == 0) { // care 레코드 생성 실패
			request.setAttribute("reservationFailed", true);
			session.setAttribute("reserveInfo", care);
			return "redirect:/reservation/reserve";
		} 
			
		if (session.getAttribute("reserveInfo") != null)
			session.removeAttribute("reserveInfo");

		return "redirect:/member/memberMyPage";
	}
}
