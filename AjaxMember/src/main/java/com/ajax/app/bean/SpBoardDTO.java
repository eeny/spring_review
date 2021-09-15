package com.ajax.app.bean;

public class SpBoardDTO {
	private int idx;
	private String name;
	private String title;
	private String email;
	private String content;
	
	public SpBoardDTO() {}

	public SpBoardDTO(String name, String title, String email, String content) {
		super();
		this.name = name;
		this.title = title;
		this.email = email;
		this.content = content;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
