package model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.Care;
import model.dao.CareDAO;
import model.dao.ReviewDAO;

public class CareManager {
	private static CareManager careMan = new CareManager();
	private CareDAO careDAO;
	private ReviewDAO reviewDAO;
	
	private CareManager() {
		try {
			careDAO = new CareDAO();
			reviewDAO = new ReviewDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static CareManager getInstance() {
		return careMan;
	}
	
	public List<Care> findCareSchedules(String memberId) throws SQLException {
		return careDAO.findCareSchedules(memberId);
	}
	
	public List<Care> findCareSchedulesOfSitter(String sitterId) throws SQLException {
		return careDAO.findCareSchedulesOfSitter(sitterId);
	}

	public List<Care> findCareOfDoNotReview(String memberId, String sitterId) throws SQLException {
		List<Care> careList = careDAO.findCareList(memberId, sitterId);
		List<Care> needReviewCareList = new ArrayList<Care>();
		if (careList != null) {
			for (Care care : careList) {
				boolean isExist = reviewDAO.isExistReview(care.getId());
				if (!isExist)
					needReviewCareList.add(care);
			}
		}
		return needReviewCareList;
	}
	
	public int deleteCare(int careId) throws SQLException {
		return careDAO.deleteCare(careId);
	}
}
