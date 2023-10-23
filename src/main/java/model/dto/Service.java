package model.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Service implements Serializable {
	private String id;
	private String title;
	private String content;
	
	public Service() {
		super();
	}
	
	public Service(String id) {
		super();
		this.id = id;
	}
	
	public Service(String id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
}
