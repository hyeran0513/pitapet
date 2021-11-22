package controller.reservation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.service.LikeListManager;
import model.service.MemberManager;
import model.service.PetSitterManager;
import model.dto.PetSitter;
import model.dto.LikeList;

public class ListSitterController implements Controller {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		HttpSession session = request.getSession();
		MemberManager memberMan = MemberManager.getInstance();
		PetSitterManager sitterMan = PetSitterManager.getInstance();
		LikeListManager likelistMan = LikeListManager.getInstance();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int rpp = 2;
		
		// session에 id정보가 없으면 mainpage 호출 리다이렉션
		if(!UserSessionUtils.hasLogined(session)) {
			 return "redirect:/mainpage";
		}
		
		// 공개여부가 'Y'인 모든 돌보미 리스트 반환
		ArrayList<PetSitter> sitterList = sitterMan.findPetSitterList();
		
		if (sitterList != null) {
			// 페이지 정보 및 현재 페이지에 출력 될 돌보미 리스트를 전달
			int totalSitter = sitterList.size();
			int totalPage;
			if (totalSitter % 2 == 0)
				totalPage = totalSitter / rpp;
			else
				totalPage = totalSitter / rpp + 1;
			int startIndex = 0;
			for (int i = 1; i < currentPage; i++)
				startIndex += rpp;
			int endIndex;
			if (totalSitter - startIndex == 0)
				endIndex = startIndex;
			else if (totalSitter - startIndex == 1)
				endIndex = startIndex + 1;
			else
				endIndex = startIndex + rpp;
			ArrayList<Integer> pageInfo = new ArrayList<Integer>();
			pageInfo.add(totalPage);
			pageInfo.add(currentPage);
			request.setAttribute("pageInfo", pageInfo);
			
			List<PetSitter> pagingSitters = sitterList.subList(startIndex, endIndex);
			for (PetSitter sitter : pagingSitters) {
				String[] address = sitter.getSitter().getAddress().split(" ");
				String city = null;
				for (int j = 0; j < address.length; j++) {
					if (address[j].matches("(.*)로"))
						city = address[j].substring(0, address[j].length() - 1);
				}
				sitter.getSitter().setAddress(city);
				
				String profileImg = memberMan.findProfileAttachment(sitter.getSitter().getId());
				sitter.getSitter().setProfileImage(profileImg);
			}
			request.setAttribute("petSitterList", pagingSitters);

			// 지역 및 동물 맞춤 추천 돌보미 전달
			
			// 좋아요 누른 돌보미 id list 전달
			List<LikeList> likeSitters = likelistMan.findLikeListOfMember(UserSessionUtils.getLoginUserId(session));
			request.setAttribute("likeSitters", likeSitters);
			System.out.println(likeSitters);
		}
		
        return "/reservation/sitterList.jsp";
    }
}