package com.member.app.bean;

public class SpBoardDTO {
	private int idx;
	private String name;
	private String title;
	private String contents;
	private String regdate;
	
	public SpBoardDTO() {}
	
	public SpBoardDTO(String name, String title, String contents, String regdate) {
		this.name = name;
		this.title = title;
		this.contents = contents;
		this.regdate = regdate;
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

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
}
