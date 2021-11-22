package model.service;

import java.sql.SQLException;

import model.dao.ReservationDAO;
import model.dto.Care;

public class ReservationManager {
	private static ReservationManager reservationMan = new ReservationManager();
	private ReservationDAO reservationDAO;
	
	private ReservationManager() {
	      try {
	    	  reservationDAO = new ReservationDAO();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }         
	   }

	public static ReservationManager getInstance() {
		return reservationMan;
	}

	public ReservationDAO getReservationDAO() {
		return this.reservationDAO;
	}
	
	public int createCare(Care care) throws SQLException {
		   return reservationDAO.createCare(care);
	}
}
