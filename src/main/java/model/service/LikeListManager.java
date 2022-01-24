package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.LikeListDAO;
import model.dao.PetSitterDAO;
import model.dto.LikeList;

public class LikeListManager {
	private static LikeListManager likelistMan = new LikeListManager();
	private LikeListDAO likeListDAO;
	private PetSitterDAO petSitterDAO;

	private LikeListManager() {
		try {
			likeListDAO = new LikeListDAO();
			petSitterDAO = new PetSitterDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static LikeListManager getInstance() {
		return likelistMan;
	}

	/* 특정 회원의 likeList 반환 */
	public List<LikeList> findLikeListOfMember(String memberId) throws SQLException {
		return likeListDAO.findLikeListOfMember(memberId);
	}

	/* like 추가 또는 삭제 */
	public boolean addOrRemove(String memberId, String sitterId, String status) throws SQLException {
		int count = 0;
		if (status.equals("add"))
			count = likeListDAO.add(memberId, sitterId);
		else if (status.equals("remove"))
			count = likeListDAO.remove(memberId, sitterId);
		if (count == 0)
			return false;
		
		count = petSitterDAO.updateLikes(sitterId, status);
		if (count == 0)
			return false;
		return true;
	}

}
