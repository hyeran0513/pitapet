package model.service;

import java.util.Map;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import model.dao.PetDAO;
import model.dto.Pet;
import model.dto.PetKind;

public class PetManager {
	private static PetManager petMan = new PetManager();
	private PetDAO petDAO;

	private PetManager() {
		try {
			petDAO = new PetDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static PetManager getInstance() {
		return petMan;
	}

	/* 특정 회원의 반려동물 리스트 반환 */
	public ArrayList<Pet> findPetListOfMember(String memberId) throws SQLException {
		ArrayList<Pet> userPets = petDAO.findPetListOfMember(memberId);		
		if (userPets != null) 
			for (Pet pet : userPets) {
				pet.setPetImage(petDAO.findPetAttachment(memberId, pet.getId()));
				
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
				String[] today = sdf.format(new Date()).split("-");
				String[] birth = pet.getBirth().split(" ")[0].split("-");
				int year = Integer.valueOf(today[0]) - Integer.valueOf(birth[0]) + 1;
				pet.setBirth(String.valueOf(year));
			}
		
		return userPets;
	}
	
	/* 특정 돌보미의 돌봄 가능 종 리스트 검색 */
	public ArrayList<PetKind> findAblePetKindList(String sitterId) throws SQLException {
		return petDAO.findAblePetKindList(sitterId);
	}
	
	/* 전체 반려동물 종 리스트 검색 */
	public ArrayList<PetKind> findAllPetKindList() throws SQLException {
		return petDAO.findAllPetKindList();
	}
	
	/* 전체 돌보미들의 돌봄 가능 종 리스트 반환 */
	public ArrayList<PetKind> findAllAblePetKindList() throws SQLException {
		return petDAO.findAllAblePetKindList();
	}

	/* 보호자의 반려동물 중 특정 돌보미가 돌봄 가능한 동물을 맵으로 반환 */
	public Map<String, Pet> getAbleCarePetMap(String memberId, String sitterId) throws SQLException {
		ArrayList<Pet> userPets = (ArrayList<Pet>) petDAO.findAbleCarePetList(memberId, sitterId);
		if (userPets != null) {
			for (Pet pet : userPets)
				pet.setPetImage(petDAO.findPetAttachment(memberId, pet.getId()));
		
			Iterator<Pet> iterator = userPets.iterator();
			Map<String, Pet> petMap = new HashMap<String, Pet>();
			while (iterator.hasNext()) {
				Pet pet = iterator.next();
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
				String[] today = sdf.format(new Date()).split("-");
				String[] birth = pet.getBirth().split(" ")[0].split("-");
				int year = Integer.valueOf(today[0]) - Integer.valueOf(birth[0]) + 1;
				pet.setBirth(String.valueOf(year));
	
				petMap.put(pet.getId(), pet);
			}
			return petMap;
		}
		return null;
	}
	
	/* 반려동물 아이디 생성 */
	public String makePetId() {
		int count = petDAO.countAllPet();
		String petId = null;
		if (count + 1 < 10) {
			petId = "petId0" + Integer.toString(count + 1);
		} else {
			petId = "petId" + Integer.toString(count + 1);
		}
		return petId;
	}
	
	/* 반려동물 추가 */
	public boolean addPet(String memberId, Pet pet) throws SQLException {
		pet.setId(makePetId());
		int count = petDAO.addPet(memberId, pet);
		if (count == 0) return false;
		
		String image = pet.getPetImage();
		image = "/upload/" + image;
		count = petDAO.addAttachment(image, memberId);
			if (count == 0) return false;

		return true;
	}	
	
	/* 반려동물 정보 검색 */
	public Pet findPetInfo(String petId) throws SQLException {
		return petDAO.findPetInfo(petId);
	}
	
	/* 반려동물 삭제 */
	public int remove(String petId) throws SQLException {
		ServiceManager serviceMan = ServiceManager.getInstance();
		if (serviceMan.countReceiveServiceByPetId(petId) > 0) // 해당 반려동물 관련 예약 정보가 없으면
			// 반려동물 사진 삭제과 반려동물 삭제
			return petDAO.removePetAttachments(petId) + petDAO.remove(petId);
		return 0;
	}
	
	/* 돌봄 가능 반려동물 추가 */
	public int addAblePetKind(String memberId, String[] petKinds) throws SQLException {
		int count = 0;
		
		for(int i = 0; i < petKinds.length; i++) {
			count += petDAO.addAblePetKind(memberId, petKinds[i]);
		}
		
		return count;
	}	
}	
