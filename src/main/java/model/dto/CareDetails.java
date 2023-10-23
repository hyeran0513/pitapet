package model.dto;

import java.io.Serializable;

/* receive service랑 checklist 테이블이 합쳐진 것 */
@SuppressWarnings("serial")
public class CareDetails implements Serializable {
	private String id; // recv_id
	private int recordId; // 추가
	private Care careInfo;
	private Service serviceInfo;
	private Pet carePet;
	private String check;
	
	public CareDetails() {
		super();
	}
	
	public CareDetails(Pet carePet) {
		super();
		this.carePet = carePet;
	}
	
	public CareDetails(String id, Care careInfo, Service serviceInfo, Pet carePet) {
		super();
		this.id = id;
		this.careInfo = careInfo;
		this.serviceInfo = serviceInfo;
		this.carePet = carePet;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
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
