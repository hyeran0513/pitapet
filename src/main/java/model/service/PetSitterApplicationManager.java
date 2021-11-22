package model.service;

import java.sql.SQLException;

import model.dto.PetSitterApplication;
import model.dao.PetSitterApplicationDAO;

public class PetSitterApplicationManager {
	private static PetSitterApplicationManager applycationMan = new PetSitterApplicationManager();
	private PetSitterApplicationDAO petSitterApplicationDAO;
	
	private PetSitterApplicationManager() {
		try {
			petSitterApplicationDAO = new PetSitterApplicationDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static PetSitterApplicationManager getInstance() {
		return applycationMan;
	}
	
	public String getApprovalStatus(String memberId) throws SQLException {
		return petSitterApplicationDAO.getApprovalStatus(memberId);
	}

}
