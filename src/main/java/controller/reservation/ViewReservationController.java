package controller.reservation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dto.Care;
import model.service.CareManager;

public class ViewReservationController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		CareManager careMan = CareManager.getInstance();
		String careId = (String) request.getParameter("careId");
		
		// 예약 상세 정보 전달
		Care care = careMan.findReservation(Integer.parseInt(careId));
		request.setAttribute("reservationInfo", care);
		
		return "/reservation/reservationView.jsp";
	}

}
