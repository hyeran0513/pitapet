package controller.reservation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.service.LikeListManager;
import model.service.PetManager;
import model.service.PetSitterManager;
import model.dto.PetSitter;
import model.dto.LikeList;
import model.dto.PetKind;

public class ListSitterController implements Controller {

	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		HttpSession session = request.getSession();
		PetManager petMan = PetManager.getInstance();
		PetSitterManager sitterMan = PetSitterManager.getInstance();
		LikeListManager likelistMan = LikeListManager.getInstance();
		
		// session에 id정보가 없으면 mainpage 호출 리다이렉션
		if(!UserSessionUtils.hasLogined(session)) {
			return "redirect:/mainpage";
		}
		String memberId = UserSessionUtils.getLoginUserId(session);
		
		// page정보, option정보를 파라미터로 가져옴
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		List<String> options = new ArrayList<>();
		options.add((String) request.getParameter("searchOption"));
		options.add((String) request.getParameter("keyword"));
		
		// 파라미터로 가져온 option정보가 없다면 세션에서 option정보를 가져옴(페이지만 선택했을 때)
		if (options.get(0) == null)
			options = (List<String>) session.getAttribute("searchOption");
		else
			session.setAttribute("searchOption", options);

		// 동물 종 카테고리 리스트 전달(돌보미들이 선택한 돌봄 가능 돌물종으로 제한)
		List<PetKind> petKindList = petMan.findAllAblePetKindList();
		request.setAttribute("petKindList", petKindList);
		
		// 페이지 정보 및 현재 페이지에 출력 될 돌보미 리스트를 전달
		Map<Integer, List<PetSitter>> sitterMap = sitterMan.getPetSittersOfPage(options, currentPage);
		if (sitterMap != null) {
			Iterator<Integer> iterator = sitterMap.keySet().iterator();
	        if (iterator.hasNext()) {
	        	Integer totalPage = iterator.next();        	
	        	ArrayList<Integer> pageInfo = new ArrayList<Integer>();
				pageInfo.add(totalPage);
				pageInfo.add(currentPage);
				request.setAttribute("pageInfo", pageInfo);
				request.setAttribute("petSitterList", sitterMap.get(totalPage));
	        }
			// 현재 로그인한 사용자의 likeList 전달
			List<LikeList> likeSitters = likelistMan.findLikeListOfMember(memberId);
			request.setAttribute("likeSitters", likeSitters);
		}
		
		// 지역 및 동물 맞춤 추천 돌보미 전달
		PetSitter recommendSitter = sitterMan.getRecommendPetSitter(memberId);
		request.setAttribute("recoSitter", recommendSitter);
		
        return "/reservation/sitterList.jsp";
    }
}