package model.dto;

/* receive service랑 checklist 테이블이 합쳐진 것 */
public class CareDetails {
	private String id; // recv_id
	private Care careInfo;
	private Service serviceInfo;
	private Pet carePet;
	private String check;
	
	public CareDetails(String id) {
		super();
		this.id = id;
	}
	
	public CareDetails(Pet carePet) {
		super();
		this.carePet = carePet;
	}
	
	public CareDetails(String id, Care careInfo, Service serviceInfo, Pet carePet, String check) {
		super();
		this.id = id;
		this.careInfo = careInfo;
		this.serviceInfo = serviceInfo;
		this.carePet = carePet;
		this.check = check;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Care getCareInfo() {
		return careInfo;
	}

	public void setCareInfo(Care careInfo) {
		this.careInfo = careInfo;
	}

	public Service getServiceInfo() {
		return serviceInfo;
	}

	public void setServiceInfo(Service serviceInfo) {
		this.serviceInfo = serviceInfo;
	}

	public Pet getCarePet() {
		return carePet;
	}
	public void setCarePet(Pet carePet) {
		this.carePet = carePet;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
}
