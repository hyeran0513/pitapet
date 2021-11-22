package controller.reservation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import model.dto.PetKind;
import model.dto.PetSitter;
import model.dto.Service;
import model.service.CareManager;
import model.service.PetManager;
import model.service.PetSitterManager;
import model.service.ReservationManager;
import model.service.ServiceManager;
import model.service.exception.UnavailableReservationTimeException;

public class ReserveController implements Controller {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String userId = UserSessionUtils.getLoginUserId(session);

		// 예약 form 이동
		if (request.getMethod().equals("GET")) {
			String sitterId = (String) request.getParameter("sitterId");

			PetSitterManager sitterMan = PetSitterManager.getInstance();
			PetManager petMan = PetManager.getInstance();
			CareManager careMan = CareManager.getInstance();
			ServiceManager srvcMan = ServiceManager.getInstance();

			// 펫시터 정보
			PetSitter petsitterInfo = sitterMan.findPetSitter(sitterId);
			request.setAttribute("petsitterInfo", petsitterInfo);
			
			// 돌봄 시작일, 종료일 정보
			String fromDate = request.getParameter("fromDate");
			request.setAttribute("fromDate", fromDate);
			String toDate = request.getParameter("toDate");
			request.setAttribute("toDate", toDate);

			// 돌봄 가능 동물종 리스트
			List<PetKind> ablePetKinds = petMan.findAblePetKindLsit(sitterId);
			request.setAttribute("ablePetKinds", ablePetKinds);

			// 로그인한 유저의 반려동물 리스트
			List<Pet> userPets = petMan.findPetListOfMember(userId);
			request.setAttribute("userPets", userPets);
			
			// 로그인한 유저의 반려동물 리스트 js에서 사용하기 위해 JSON 객체로 저장
			if (userPets != null) {
				Iterator<Pet> iterator = userPets.iterator();
				Map<String, Pet> petMap = new HashMap<String, Pet>();
				while (iterator.hasNext()) {
					Pet pet = iterator.next();
					SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
					String[] today = sdf.format(new Date()).split("-");
					String[] birth = pet.getBirth().split(" ")[0].split("-");
					int year = Integer.valueOf(today[0]) - Integer.valueOf(birth[0]) + 1;
					pet.setBirth(String.valueOf(year));
					
					petMap.put(pet.getId(), pet);
				}
				ObjectMapper mapper = new ObjectMapper();
				String pets = mapper.writeValueAsString(petMap);
	
				request.setAttribute("userPetsJson", pets);
			}

			// 로그인한 유저의 반려동물 별 사진 리스트
			for (Pet pet : userPets) {
				List<String> imgList = petMan.findPetAttachments(userId, pet.getId());
				pet.setImages(imgList);
			}

			// 가능 서비스
			List<Service> ableService = srvcMan.findProvideServiceList(sitterId);
			
			if (ableService != null) {
				Iterator<Service> iterator = ableService.iterator();
				Map<String, Service> serviceMap = new HashMap<String, Service>();
				while (iterator.hasNext()) {
					Service service = iterator.next();
					serviceMap.put(service.getId(), service);
				}
				ObjectMapper mapper = new ObjectMapper();
				String services = mapper.writeValueAsString(serviceMap);

				request.setAttribute("ableService", services);
			}

			return "/reservation/reservationForm.jsp";
		}

		// 예약 처리
		CareManager careMan = CareManager.getInstance();
		ReservationManager reservationMan = ReservationManager.getInstance();
		ServiceManager serviceMan = ServiceManager.getInstance();

		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
		String toDate = request.getParameter("toDate") + " 00:00:01";
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// String 타입을 Date 타입으로 변환
		Date formatDate = dtFormat.parse(toDate);
		// Date타입의 변수를 새롭게 지정한 포맷으로 변환
		toDate = newDtFormat.format(formatDate);
		
		Care care = new Care(request.getParameter("fromDate"), toDate, totalPrice,
				request.getParameter("cautionText"), "X", null, new Member(userId),
				new PetSitter(new Member(request.getParameter("sitterId"))));
		
		int careId = reservationMan.createCare(care);
		
		if (careId == 0) { // care 레코드 생성 실패
			request.setAttribute("reservationFailed", true);
			request.setAttribute("care", care);
			return "redirect:/reservation/reserve";
		}
		else {
			String[] pets = request.getParameterValues("pet"); // 돌봄 받을 펫들의 id
			List<CareDetails> careDetails = new ArrayList<CareDetails>();
			
			for (String pet:pets) {
				String[] services = request.getParameterValues(pet); // 돌봄 받을 펫의 요청 서비스들
				
				for (String service:services) {
					String receiveServiceId = serviceMan.createReceiveService(careId, pet, service);
					System.out.println(service + " " + receiveServiceId);
					
					if (receiveServiceId == null) { // receiveService 레코드 생성 실패
						serviceMan.deleteReceiveService(careId); // 삽입된 receive_service 레코드 삭제
						careMan.deleteCare(careId); // 삽입된 care 레코드 삭제
						
						request.setAttribute("reservationFailed", true);
						request.setAttribute("care", care);
						return "redirect:/reservation/reserve";
					}
					else {
						String recvId = Integer.toString(careId) + pet.replaceAll("[^0-9]", "") + service.replaceAll("[^0-9]", "");
						CareDetails cd = new CareDetails(recvId, care, new Service(service), new Pet(pet), "N");
						careDetails.add(cd);
					}
				}
			}
			care.setCareList(careDetails);
			
			request.setAttribute("reservationFailed", false);
			request.setAttribute("care", care);
			
			return "redirect:/member/memberMyPage";
		}
	}
}
