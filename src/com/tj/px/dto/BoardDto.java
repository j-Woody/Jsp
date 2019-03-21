package com.tj.px.dto;

import java.sql.Date;

public class BoardDto {
	private int bNo;
	private String mEmail;
	private String bTitle;
	private String bContent;
	private String bImage;
	private int bGroup;
	private int bStep;
	private int bIndent;
	private Date bDate;
	private int bHit;
	private String bIp;
	public BoardDto(int bNo, String mEmail, String bTitle, String bContent, String bImage, int bGroup, int bStep,
			int bIndent, Date bDate, int bHit, String bIp) {
		super();
		this.bNo = bNo;
		this.mEmail = mEmail;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bImage = bImage;
		this.bGroup = bGroup;
		this.bStep = bStep;
		this.bIndent = bIndent;
		this.bDate = bDate;
		this.bHit = bHit;
		this.bIp = bIp;
	}
	public int getbNo() {
		return bNo;
	}
	public void setbNo(int bNo) {
		this.bNo = bNo;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public String getbImage() {
		return bImage;
	}
	public void setbImage(String bImage) {
		this.bImage = bImage;
	}
	public int getbGroup() {
		return bGroup;
	}
	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}
	public int getbStep() {
		return bStep;
	}
	public void setbStep(int bStep) {
		this.bStep = bStep;
	}
	public int getbIndent() {
		return bIndent;
	}
	public void setbIndent(int bIndent) {
		this.bIndent = bIndent;
	}
	public Date getbDate() {
		return bDate;
	}
	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}
	public int getbHit() {
		return bHit;
	}
	public void setbHit(int bHit) {
		this.bHit = bHit;
	}
	public String getbIp() {
		return bIp;
	}
	public void setbIp(String bIp) {
		this.bIp = bIp;
	}
	@Override
	public String toString() {
		return "BoardDto [bNo=" + bNo + ", mEmail=" + mEmail + ", bTitle=" + bTitle + ", bContent=" + bContent
				+ ", bImage=" + bImage + ", bGroup=" + bGroup + ", bStep=" + bStep + ", bIndent=" + bIndent + ", bDate="
				+ bDate + ", bHit=" + bHit + ", bIp=" + bIp + "]";
	}

	
}
