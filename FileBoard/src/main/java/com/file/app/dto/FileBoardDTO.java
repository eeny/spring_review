package com.file.app.dto;

public class FileBoardDTO {
	private int idx;
	private String txt;
	private String filename;
	
	public FileBoardDTO() {}

	public FileBoardDTO(String txt, String filename) {
		this.txt = txt;
		this.filename = filename;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
