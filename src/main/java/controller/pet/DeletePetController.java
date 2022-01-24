package controller.pet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.PetManager;

public class DeletePetController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String petId = request.getParameter("petId");
		PetManager petMan = PetManager.getInstance();
		
		// 반려동물 삭제 처리
		int delete = petMan.remove(petId);
		if (delete > 1) {
			return "redirect:/pet/listPet";
		}
		
		return "redirect:/pet/listPet?deleteFailed=true";
	}

}
