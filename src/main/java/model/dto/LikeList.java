package model.dto;

public class LikeList {
	private String memberId;
	private PetSitter likeSitter;
	
	public LikeList(String memberId, PetSitter likeSitter) {
		super();
		this.memberId = memberId;
		this.likeSitter = likeSitter;
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public PetSitter getLikeSitter() {
		return likeSitter;
	}
	public void setLikeSitter(PetSitter likeSitter) {
		this.likeSitter = likeSitter;
	}
}
