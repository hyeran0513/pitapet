package controller.reservation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dto.Care;
import model.service.CareManager;
import model.service.ServiceManager;

public class CancelReservationController implements Controller  {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ServiceManager serviceMan = ServiceManager.getInstance();
		CareManager careMan = CareManager.getInstance();
		
		int careId = Integer.parseInt(request.getParameter("careId"));
		Care care = careMan.findReservation(careId);
		
		Calendar today = Calendar.getInstance();
		today.setTime(new Date()); //금일 날짜
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(care.getStartDate());
		Calendar cmpDate = Calendar.getInstance();
		cmpDate.setTime(date); //특정 일자
		
		// 돌봄 시작일이 하루 이상 남고, 예약상태가 '예약완료'이면 예약 내역 삭제 처리
		long diffSec = (cmpDate.getTimeInMillis() - today.getTimeInMillis()) / 1000;
		long diffDays = diffSec / (24*60*60); //일자수 차이
		if (diffDays >= 1 && care.getStatus().equals("X")) {
			serviceMan.deleteReceiveService(careId); // 삽입된 receive_service 레코드 삭제
			careMan.deleteCare(careId); // 삽입된 care 레코드 삭제
			return "redirect:/member/memberMyPage";
		}
		else {
			return "redirect:/member/memberMyPage?cancelFailed=true";
		}
	}

}
