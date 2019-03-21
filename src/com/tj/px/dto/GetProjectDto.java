package com.tj.px.dto;

public class GetProjectDto {
	private int gNo;
	private String mEmail;
	private int pNo;
	private int pay;
	public GetProjectDto(int gNo, String mEmail, int pNo, int pay) {
		super();
		this.gNo = gNo;
		this.mEmail = mEmail;
		this.pNo = pNo;
		this.pay = pay;
	}
	public int getgNo() {
		return gNo;
	}
	public void setgNo(int gNo) {
		this.gNo = gNo;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	@Override
	public String toString() {
		return "GetProject [gNo=" + gNo + ", mEmail=" + mEmail + ", pNo=" + pNo + ", pay=" + pay + "]";
	}
	
	
}
