package model.dto;

import java.util.List;

public class Pet {
	private String id;
	private String name;
	private String birth;
	private String gender;
	private PetKind kind;
	private Member myCompanion;
	private List<String> images;
	
	public Pet(String id) {
		super();
		this.id = id;
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

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}	
	
}
