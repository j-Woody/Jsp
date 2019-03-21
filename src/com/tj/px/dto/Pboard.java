package com.tj.px.dto;

import java.sql.Date;

public class Pboard {
	private int pbNo;
	private String mEmail;
	private String pContent;
	private int pNo;
	private Date pbDate;
	private int pbGroup;
	private int pbStep;
	private String pbIp;
	public Pboard(int pbNo, String mEmail, String pContent, int pNo, Date pbDate, int pbGroup, int pbStep,
			String pbIp) {
		super();
		this.pbNo = pbNo;
		this.mEmail = mEmail;
		this.pContent = pContent;
		this.pNo = pNo;
		this.pbDate = pbDate;
		this.pbGroup = pbGroup;
		this.pbStep = pbStep;
		this.pbIp = pbIp;
	}
	public int getPbNo() {
		return pbNo;
	}
	public void setPbNo(int pbNo) {
		this.pbNo = pbNo;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public String getpContent() {
		return pContent;
	}
	public void setpContent(String pContent) {
		this.pContent = pContent;
	}
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public Date getPbDate() {
		return pbDate;
	}
	public void setPbDate(Date pbDate) {
		this.pbDate = pbDate;
	}
	public int getPbGroup() {
		return pbGroup;
	}
	public void setPbGroup(int pbGroup) {
		this.pbGroup = pbGroup;
	}
	public int getPbStep() {
		return pbStep;
	}
	public void setPbStep(int pbStep) {
		this.pbStep = pbStep;
	}
	public String getPbIp() {
		return pbIp;
	}
	public void setPbIp(String pbIp) {
		this.pbIp = pbIp;
	}
	@Override
	public String toString() {
		return "Pboard [pbNo=" + pbNo + ", mEmail=" + mEmail + ", pContent=" + pContent + ", pNo=" + pNo + ", pbDate="
				+ pbDate + ", pbGroup=" + pbGroup + ", pbStep=" + pbStep + ", pbIp=" + pbIp + "]";
	}
	
	
}
