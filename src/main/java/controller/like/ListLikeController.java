package controller.like;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.UserSessionUtils;
import model.dto.Care;
import model.dto.LikeList;
import model.dto.PetSitter;
import model.service.LikeListManager;
import model.service.PetSitterManager;

public class ListLikeController implements Controller{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		LikeListManager likeman = LikeListManager.getInstance();
		PetSitterManager sitterman = PetSitterManager.getInstance();
		
		// session에 id정보가 없으면 mainpage 호출 리다이렉션
		if(!UserSessionUtils.hasLogined(session)) {
			 return "redirect:/mainpage";
		}
		
		// 사용자의 좋아요 목록을 검색해 전달
		List<LikeList> likeLists = likeman.findLikeListOfMember(UserSessionUtils.getLoginUserId(session));
		List<PetSitter> likeSitterLists = new ArrayList<PetSitter>();
		
		if (likeLists != null) {
			for (LikeList likeList : likeLists) {
				PetSitter likeSitter = sitterman.findPetSitter(likeList.getLikeSitter().getSitter().getId());
				String[] address = likeSitter.getSitter().getAddress().split(" ");
				String city = null;
				for (int j = 0; j < address.length; j++) {
					if (address[j].matches("(.*)로")) {
						city = address[j].substring(0, address[j].length() - 1);
					}
				}
				likeSitter.getSitter().setAddress(city);
				likeSitterLists.add(likeSitter);
			}
		}
		request.setAttribute("likeSitterLists", likeSitterLists);
		
		return "/like/likeList.jsp";
	}
}
