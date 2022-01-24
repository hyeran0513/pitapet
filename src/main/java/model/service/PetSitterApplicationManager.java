package model.service;

import java.sql.SQLException;
import java.util.List;

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
	
	/* 회원의 돌보미 지원 상태 반환 */
	public String getApprovalStatus(String memberId) throws SQLException {
		return petSitterApplicationDAO.getApprovalStatus(memberId);
	}
	
	/* 돌보미 지원자 리스트 반환 */
	public List<PetSitterApplication> findApplicationList() throws SQLException {
		return petSitterApplicationDAO.findApplicationList();
	}
	
	/* 특정 돌보미 지원자 정보 반환 */
	public PetSitterApplication findApplication(String applyId) throws SQLException {
		PetSitterApplication application = petSitterApplicationDAO.findApplication(applyId);
		String[] address = application.getApplicant().getAddress().split(" ");
		String city = null;
		for (int j = 0; j < address.length; j++) {
			if (address[j].matches("(.*)로")) {
				city = address[j].substring(0, address[j].length() - 1);
			}
		}
		application.getApplicant().setAddress(city);
		
		List<String> imgList = petSitterApplicationDAO.findApplyAttachments(application.getApplicant().getId());
		application.setImages(imgList);
		
		return application;
	}
	
	/* 돌보미 승인/거절 */
	public boolean updateStatus(String applyId, String memberId, String status) throws SQLException {
		int count = 0;
		
		if(status.equals("approval"))
			count = petSitterApplicationDAO.applyStatus(applyId);
	
		if(status.equals("refuse"))
			count = petSitterApplicationDAO.refuseStatus(applyId);
		
		if(count == 0)
			return false;
		return true;
	}
	
	/* 돌보미 지원 아이디 생성 */
	public String makeApplicationId() {
		int count = petSitterApplicationDAO.countAllApplication();
		String applicationId = null;
		if (count + 1 < 10) {
			applicationId = "aplyId0" + Integer.toString(count + 1);
		} else {
			applicationId = "aplyId" + Integer.toString(count + 1);
		}
		return applicationId;
	}
		
	/* 돌보미 지원 추가 */
	public boolean addApplication(String memberId, PetSitterApplication app) throws SQLException {
		app.setId(makeApplicationId());
		int count = petSitterApplicationDAO.addApplication(memberId, app);
		if (count == 0) 
			return false;
		return true;
	}
	
	/* 아이디로 돌보미 지원 내역 찾기 */
	public PetSitterApplication findApplicationByMemberId(String memberId) throws SQLException {
		PetSitterApplication application = petSitterApplicationDAO.findApplicationByMemberId(memberId);
		
		List<String> imgList = petSitterApplicationDAO.findApplyAttachments(application.getApplicant().getId());
		application.setImages(imgList);
			
		return application;
	}
	
	/* 돌보미 지원 취소 */
	public boolean cancelApplication(String applyId) throws SQLException {
		int count = petSitterApplicationDAO.cancelApplication(applyId);
		if(count == 0)
			return false;
		return true;
	}
	
	/* 돌보미 지원 정보 자기소개 수정 */
	public boolean updateApplcationIntroduction(String memberId, String introduction) throws SQLException {
		int count = petSitterApplicationDAO.updateApplcationIntroduction(memberId, introduction);
		if(count == 0)
			return false;
		return true;
	}
}
