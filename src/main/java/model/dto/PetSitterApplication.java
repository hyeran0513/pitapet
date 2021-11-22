package model.dto;

import java.util.List;

public class PetSitterApplication {

	private String id;
	private String applyDate;
	private String career;
	private String certification;
	private String introduction;
	private String approvalStatus;
	private List<PetKind> kinds;
	private List<Service> services;
	private Member applicant;
	private List<String> images;
	
	public PetSitterApplication(String id) {
		super();
		this.id = id;
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
	public List<PetKind> getKinds() {
		return kinds;
	}
	public void setKinds(List<PetKind> kinds) {
		this.kinds = kinds;
	}
	public List<Service> getServices() {
		return services;
	}
	public void setServices(List<Service> services) {
		this.services = services;
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
