package com.tj.px.dto;

public class PaymentDto {
	private String pcNum;
	private String pMonth;
	private String pYear;
	private String pBirth;
	private String pPw;
	private String pPhone1;
	private String pPhone2;
	private String pPhone3;
	private String mEmail;
	public PaymentDto(String pcNum, String pMonth, String pYear, String pBirth, String pPw, String pPhone1,
			String pPhone2, String pPhone3, String mEmail) {
		super();
		this.pcNum = pcNum;
		this.pMonth = pMonth;
		this.pYear = pYear;
		this.pBirth = pBirth;
		this.pPw = pPw;
		this.pPhone1 = pPhone1;
		this.pPhone2 = pPhone2;
		this.pPhone3 = pPhone3;
		this.mEmail = mEmail;
	}
	public String getPcNum() {
		return pcNum;
	}
	public void setPcNum(String pcNum) {
		this.pcNum = pcNum;
	}
	public String getpMonth() {
		return pMonth;
	}
	public void setpMonth(String pMonth) {
		this.pMonth = pMonth;
	}
	public String getpYear() {
		return pYear;
	}
	public void setpYear(String pYear) {
		this.pYear = pYear;
	}
	public String getpBirth() {
		return pBirth;
	}
	public void setpBirth(String pBirth) {
		this.pBirth = pBirth;
	}
	public String getpPw() {
		return pPw;
	}
	public void setpPw(String pPw) {
		this.pPw = pPw;
	}
	public String getpPhone1() {
		return pPhone1;
	}
	public void setpPhone1(String pPhone1) {
		this.pPhone1 = pPhone1;
	}
	public String getpPhone2() {
		return pPhone2;
	}
	public void setpPhone2(String pPhone2) {
		this.pPhone2 = pPhone2;
	}
	public String getpPhone3() {
		return pPhone3;
	}
	public void setpPhone3(String pPhone3) {
		this.pPhone3 = pPhone3;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	@Override
	public String toString() {
		return "PaymentDto [pcNum=" + pcNum + ", pMonth=" + pMonth + ", pYear=" + pYear + ", pBirth=" + pBirth
				+ ", pPw=" + pPw + ", pPhone1=" + pPhone1 + ", pPhone2=" + pPhone2 + ", pPhone3=" + pPhone3
				+ ", mEmail=" + mEmail + "]";
	}
	
	
	
}
