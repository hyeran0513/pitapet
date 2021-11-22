package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dto.Review;
import model.dao.ReviewDAO;

public class ReviewManager {
	private static ReviewManager reviewMan = new ReviewManager();
	private ReviewDAO reviewDAO;

	private ReviewManager() {
		try {
			reviewDAO = new ReviewDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ReviewManager getInstance() {
		return reviewMan;
	}

	public List<Review> findReviewList() throws SQLException {
		return reviewDAO.findReviewList();
	}

	public List<Review> findReviewListOfSitter(String sitterId) throws SQLException {
		return reviewDAO.findReviewListOfSitter(sitterId);
	}

	public List<String> findReviewAttachments(String memberId, int careId) throws SQLException {
		return reviewDAO.findReviewAttachments(memberId, careId);
	}
}
