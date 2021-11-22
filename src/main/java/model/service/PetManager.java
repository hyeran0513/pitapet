package model.service;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.PetDAO;
import model.dto.CareDetails;
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

	public ArrayList<Pet> findPetListOfMember(String memberId) throws SQLException {
		return petDAO.findPetListOfMember(memberId);
	}
	
	public ArrayList<PetKind> findAblePetKindLsit(String sitterId) throws SQLException {
		return petDAO.findAblePetKindList(sitterId);
	}
	
	public ArrayList<String> findPetAttachments(String memberId, String petId) throws SQLException {
		return (ArrayList<String>) petDAO.findPetAttachments(memberId, petId);
	}
	
	public ArrayList<CareDetails> findCarePetList(Integer careId) throws SQLException {
		ArrayList<Pet> petList = petDAO.findCarePetList(careId);
		ArrayList<CareDetails> careDetailList = new ArrayList<CareDetails>();
		if(petList != null) {
			for (Pet pet : petList) {
				careDetailList.add(new CareDetails(pet));
				System.out.println(pet.getName());
			}
		}
		return careDetailList;
	}
}
