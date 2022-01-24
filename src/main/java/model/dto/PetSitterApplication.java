package model.dto;

import java.util.List;

public class PetSitterApplication {

	private String id;
	private String applyDate;
	private String career;
	private String certification;
	private String introduction;
	private String approvalStatus;
	private Member applicant;
	private List<String> images;
	
	public PetSitterApplication(String id) {
		super();
		this.id = id;
	}
	
	public PetSitterApplication(String id, String applyDate, Member applicant) {
		super();
		this.id = id;
		this.applyDate = applyDate;
		this.applicant = applicant;
	}
	
	public PetSitterApplication(String id, String applyDate, String career, String certification, String introduction, Member applicant) {
		super();
		this.id = id;
		this.applyDate = applyDate;
		this.career = career;
		this.certification = certification;
		this.introduction = introduction;
		this.applicant = applicant;
	}
	
	public PetSitterApplication(String career, String certification, String introduction, String approvalStatus) {
		super();
		this.career = career;
		this.certification = certification;
		this.introduction = introduction;
		this.approvalStatus = approvalStatus;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getCertification() {
		return certification;
	}
	public void setCertification(String certification) {
		this.certification = certification;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public Member getApplicant() {
		return applicant;
	}
	public void setApplicant(Member applicant) {
		this.applicant = applicant;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
}
