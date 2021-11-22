package model.dto;

import java.util.List;

public class CareRecord {
	private String id;
	private List<CareDetails> checkList;
	private String writeDate;
	private String title;
	private String content;
	private Care careInfo;
	private List<String> images;
	
	public CareRecord(String id) {
		super();
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<CareDetails> getCheckList() {
		return checkList;
	}
	public void setCheckList(List<CareDetails> checkList) {
		this.checkList = checkList;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Care getCareInfo() {
		return careInfo;
	}
	public void setCareInfo(Care careInfo) {
		this.careInfo = careInfo;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}
}
