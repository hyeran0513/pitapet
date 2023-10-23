package model.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Pet implements Serializable {
	private String id;
	private String name;
	private String birth;
	private String gender;
	private PetKind kind;
	private Member myCompanion;
	private String petImage;
	
	public Pet() {
		super();
	}
	
	public Pet(String id) {
		super();
		this.id = id;
	}
	
	public Pet(String id, String name, String birth, String gender, PetKind kind, Member myCompanion) {
		super();
		this.id = id;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.kind = kind;
		this.myCompanion = myCompanion;
	}
	
	public Pet(String name, String birth, String gender, PetKind kind, String petImage) {
		super();
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.kind = kind;
		this.petImage = petImage;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public PetKind getKind() {
		return kind;
	}
	public void setKind(PetKind kind) {
		this.kind = kind;
	}
	public Member getMyCompanion() {
		return myCompanion;
	}
	public void setMyCompanion(Member myCompanion) {
		this.myCompanion = myCompanion;
	}
	public String getPetImage() {
		return petImage;
	}
	public void setPetImage(String petImage) {
		this.petImage = petImage;
	}
	
}
