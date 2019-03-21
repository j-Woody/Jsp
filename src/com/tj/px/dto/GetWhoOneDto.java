package com.tj.px.dto;

import java.sql.Date;

public class GetWhoOneDto {
	private String mEmail;
	private int pNo;
	private String pTitle;
	private Date pDate;
	private int pPay;
	private int pNow;
	private int pCnt;
	private String pImage;
	private int gNo;
	private int pay;
	public GetWhoOneDto(int pNo,String mEmail, String pTitle, Date pDate, int pPay, int pNow, int pCnt, String pImage, int gNo,
			int pay) {
		super();
		this.mEmail =mEmail;
		this.pNo = pNo;
		this.pTitle = pTitle;
		this.pDate = pDate;
		this.pPay = pPay;
		this.pNow = pNow;
		this.pCnt = pCnt;
		this.pImage = pImage;
		this.gNo = gNo;
		this.pay = pay;
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
	public String getpTitle() {
		return pTitle;
	}
	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}
	public Date getpDate() {
		return pDate;
	}
	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}
	public int getpPay() {
		return pPay;
	}
	public void setpPay(int pPay) {
		this.pPay = pPay;
	}
	public int getpNow() {
		return pNow;
	}
	public void setpNow(int pNow) {
		this.pNow = pNow;
	}
	public int getpCnt() {
		return pCnt;
	}
	public void setpCnt(int pCnt) {
		this.pCnt = pCnt;
	}
	public String getpImage() {
		return pImage;
	}
	public void setpImage(String pImage) {
		this.pImage = pImage;
	}
	public int getgNo() {
		return gNo;
	}
	public void setgNo(int gNo) {
		this.gNo = gNo;
	}
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}

	@Override
	public String toString() {
		return "GetWhoOneDto [mEmail=" + mEmail + ", pNo=" + pNo + ", pTitle=" + pTitle + ", pDate=" + pDate + ", pPay="
				+ pPay + ", pNow=" + pNow + ", pCnt=" + pCnt + ", pImage=" + pImage + ", gNo=" + gNo + ", pay=" + pay
				+ "]";
	}

	
}

