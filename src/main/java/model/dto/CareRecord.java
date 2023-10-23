package model.dto;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class CareRecord implements Serializable {
	private int id;
	private List<CareDetails> checkList;
	private String writeDate;
	private String title;
	private String content;
	private Care careInfo;
	private List<String> images;
	
	public CareRecord() {
		super();
	}
	
	public CareRecord(int id, List<CareDetails> checkList, String writeDate, String title, String content,
			Care careInfo, List<String> images) {
		super();
		this.id = id;
		this.checkList = checkList;
		this.writeDate = writeDate;
		this.title = title;
		this.content = content;
		this.careInfo = careInfo;
		this.images = images;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
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
