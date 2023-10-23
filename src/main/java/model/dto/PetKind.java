package model.dto;

public class PetKind {
	private String id;
	private String largeCategory;
	private String smallCategory;
	
	public PetKind(String id) {
		super();
		this.id = id;
	}
	
	public PetKind(String id, String largeCategory, String smallCategory) {
		super();
		this.id = id;
		this.largeCategory = largeCategory;
		this.smallCategory = smallCategory;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLargeCategory() {
		return largeCategory;
	}
	public void setLargeCategory(String largeCategory) {
		this.largeCategory = largeCategory;
	}
	public String getSmallCategory() {
		return smallCategory;
	}
	public void setSmallCategory(String smallCategory) {
		this.smallCategory = smallCategory;
	}
}
