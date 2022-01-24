package model.service;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dao.MemberDAO;
import model.dao.PetDAO;
import model.dao.PetSitterDAO;
import model.dao.mybatis.ServiceDAO;
import model.dto.Member;
import model.dto.PetKind;
import model.dto.PetSitter;
import model.dto.Service;

public class PetSitterManager {
	private static PetSitterManager sitterMan = new PetSitterManager();
	private MemberDAO memberDAO;
	private PetSitterDAO sitterDAO;
	private ServiceDAO serviceDAO;
	private PetDAO petDAO;

	private PetSitterManager() {
		try {
			memberDAO = new MemberDAO();
			sitterDAO = new PetSitterDAO();
			serviceDAO = new ServiceDAO();
			petDAO = new PetDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static PetSitterManager getInstance() {
		return sitterMan;
	}
	
	/* 페이지에 출력될 펫시터 리스트 및 페이지 정보 반환 */
	public Map<Integer, List<PetSitter>> getPetSittersOfPage(List<String> options, int currentPage) throws SQLException {
		ArrayList<PetSitter> sitterList = null;
		if (options.get(0).equals("list"))
			sitterList = sitterDAO.findPetSitterList();
		else {
			sitterList = sitterDAO.findPetSitterListByKeyword(options);
			if (sitterList == null)
				sitterList = new ArrayList<PetSitter>(); // 검색 결과를 찾지 못했을 때를 구분하기 위해 비어있는 리스트 생성
		}
		
		if (sitterList != null) {
			int rpp = 2;
			int totalSitter = sitterList.size();
			int totalPage;
			if (totalSitter % 2 == 0)
				totalPage = totalSitter / rpp;
			else
				totalPage = totalSitter / rpp + 1;
			
			int startIndex = 0;
			for (int i = 1; i < currentPage; i++)
				startIndex += rpp;
			int endIndex;
			if (totalSitter - startIndex == 0)
				endIndex = startIndex;
			else if (totalSitter - startIndex == 1)
				endIndex = startIndex + 1;
			else
				endIndex = startIndex + rpp;
			
			List<PetSitter> pagingSitters = sitterList.subList(startIndex, endIndex);
			if (pagingSitters != null)
				for (PetSitter sitter : pagingSitters)
					sitter = updateSitterProperties(sitter);
			Map<Integer, List<PetSitter>> sitterMap = new HashMap<Integer, List<PetSitter>>();
			sitterMap.put(totalPage, pagingSitters);
			
			return sitterMap;
		}
		return null;
	}
	
	/* 추천 돌보미 반환 */
	public PetSitter getRecommendPetSitter(String memberId) throws SQLException {
		ArrayList<PetSitter> sitters = sitterDAO.findPetSitterListOfRecommend(memberId);
		if (sitters != null) {
			Collections.shuffle(sitters);
			
			Member memberInfo = memberDAO.findMember(memberId);
			String[] address = memberInfo.getAddress().split(" ");
			String city = null;
			for (int j = 0; j < address.length; j++) {
				if (address[j].matches("(.*)[시|도]")) {
					city = address[j].substring(0, address[j].length() - 1);
					break;
				}
			}
			
			for (PetSitter sitter : sitters) {
				if(sitter.getSitter().getAddress().contains(city)) {
					sitter = updateSitterProperties(sitter);
					return sitter;
				}
			}
			sitters.set(0, updateSitterProperties(sitters.get(0)));
			return sitters.get(0);
		}
		return null;
	}
	
	/* 특정 회원이 찜한  돌보미 리스트 반환 */
	public List<PetSitter> findLikeSitterOfMember(String memberId) throws SQLException {
		List<PetSitter> likeSitterLists = sitterDAO.findPetSitterListOfLike(memberId);

		if (likeSitterLists != null)
			for (PetSitter sitter : likeSitterLists)
				sitter = updateSitterProperties(sitter);
	
		return likeSitterLists;
	}
	
	/* 돌보미 상세정보 반환 (제공서비스, 돌봄 가능 종 리스트 포함) */
	public PetSitter findPetSitter(String sitterId) throws SQLException {
		PetSitter sitter = sitterDAO.findPetSitter(sitterId);
		
		if (sitter != null) {
			List<Service> prvdServiceList = serviceDAO.findProvideServiceList(sitterId);
			List<PetKind> ablePetKindList = petDAO.findAblePetKindList(sitterId);
			
			sitter.setServices(prvdServiceList);
			sitter.setKinds(ablePetKindList);	
			sitter = updateSitterProperties(sitter);
		}
		
		return sitter;
	}

	/* 돌보미 조회수 추가 */
	public boolean updateViews(String sitterId) throws SQLException {
		int count = sitterDAO.updateViews(sitterId);
		if (count == 0)
			return false;
		return true;
	}
	
	/*보호자에서 돌보미로 등급 조정*/
	public int upgradeSitter(String memberId) throws SQLException {
		return sitterDAO.upgradeSitter(memberId);
	}

	/* 돌보미 멤버변수 내용 수정 */
	public PetSitter updateSitterProperties(PetSitter sitter) throws SQLException {
		String[] address = sitter.getSitter().getAddress().split(" ");
		String city = null;
		for (int j = 0; j < address.length; j++) {
			if (address[j].matches("(.*)로")) {
				city = address[j].substring(0, address[j].length() - 1);
				break;
			}
		}
		sitter.getSitter().setAddress(city);
		
		return sitter;
	}
	
	/* 돌보미 생성 */
	public boolean createSitter(String memberId, PetSitter sitter, String applyId, String[] ableDate) throws SQLException {
		for (String d : ableDate) {
			System.out.println(d);
		}
		int count = 0;
		
		//돌봄 가능 요일 처리
		String ableDateBin = "";
		for (int i = 0; i < 7; i++) { // 0 1 3 4 : 월 화 목 금 : 1101100
			if (count < ableDate.length) {
				if (Integer.parseInt(ableDate[count]) == i) {
					ableDateBin += "1";
					count++;
				}
				else {
					ableDateBin += "0";
				}
			}
			else {
				ableDateBin += "0";
			}
		}
		String ascii = Character.toString((char)Integer.parseInt(ableDateBin, 2));
		
		sitter.setAbleDate(ascii);
		
		int rslt = sitterDAO.createSitter(memberId, sitter, applyId);
		if (rslt == 0)
			return false;
		return true;
	}
}
