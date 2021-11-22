package model.dto;

public class PetSitter {
	private Member sitter;
	private String publicStatus;
	private String ableDate;
	private String calculatedPrice;
	private String tag;
	private String notes;
	private float avgRate;
	private int like;
	private int view;
	private PetSitterApplication myApplyInfo;
	
	public PetSitter(Member sitter) {
		super();
		this.sitter = sitter;
	}
	
	public PetSitter(Member sitter, String tag, String notes, int like, int view) {
		super();
		this.sitter = sitter;
		this.tag = tag;
		this.notes = notes;
		this.like = like;
		this.view = view;
	}
	
	public PetSitter(Member sitter, String publicStatus, String ableDate, String calculatedPrice, String tag, String notes,
			float avgRate, int like, int view, PetSitterApplication myApplyInfo) {
		super();
		this.sitter = sitter;
		this.publicStatus = publicStatus;
		this.ableDate = ableDate;
		this.calculatedPrice = calculatedPrice;
		this.tag = tag;
		this.notes = notes;
		this.avgRate = avgRate;
		this.like = like;
		this.view = view;
		this.myApplyInfo = myApplyInfo;
	}

	public Member getSitter() {
		return sitter;
	}
	public void setSitter(Member sitter) {
		this.sitter = sitter;
	}
	public String getPublicStatus() {
		return publicStatus;
	}
	public void setPublicStatus(String publicStatus) {
		this.publicStatus = publicStatus;
	}
	
	public String getAbleDate() {
		return ableDate;
	}

	public void setAbleDate(String ableDate) {
		this.ableDate = ableDate;
	}

	public String getCalculatedPrice() {
		return calculatedPrice;
	}

	public void setCalculatedPrice(String calculatedPrice) {
		this.calculatedPrice = calculatedPrice;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public float getAvgRate() {
		return avgRate;
	}
	public void setAvgRate(float avgRate) {
		this.avgRate = avgRate;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}

	public PetSitterApplication getMyApplyInfo() {
		return myApplyInfo;
	}

	public void setMyApplyInfo(PetSitterApplication myApplyInfo) {
		this.myApplyInfo = myApplyInfo;
	}
	
}
