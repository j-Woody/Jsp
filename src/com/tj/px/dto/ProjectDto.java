package com.tj.px.dto;

import java.sql.Date;

public class ProjectDto {
	private int pNo;
	private String pTitle;
	private String mEmail;
	private Date pDate;
	private int pPay;
	private int pNow;
	private int pCnt;
	private String pContent;
	private String pImage;
	private String pCategory;
	private String pAccount;
	private String pBankName;
	private String pAcName;
	public ProjectDto(int pNo, String pTitle, String mEmail, Date pDate, int pPay, int pNow, int pCnt, String pContent,
			String pImage, String pCategory, String pAccount, String pBankName, String pAcName) {
		super();
		this.pNo = pNo;
		this.pTitle = pTitle;
		this.mEmail = mEmail;
		this.pDate = pDate;
		this.pPay = pPay;
		this.pNow = pNow;
		this.pCnt = pCnt;
		this.pContent = pContent;
		this.pImage = pImage;
		this.pCategory = pCategory;
		this.pAccount = pAccount;
		this.pBankName = pBankName;
		this.pAcName = pAcName;
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
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
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
	public String getpContent() {
		return pContent;
	}
	public void setpContent(String pContent) {
		this.pContent = pContent;
	}
	public String getpImage() {
		return pImage;
	}
	public void setpImage(String pImage) {
		this.pImage = pImage;
	}
	public String getpCategory() {
		return pCategory;
	}
	public void setpCategory(String pCategory) {
		this.pCategory = pCategory;
	}
	public String getpAccount() {
		return pAccount;
	}
	public void setpAccount(String pAccount) {
		this.pAccount = pAccount;
	}
	public String getpBankName() {
		return pBankName;
	}
	public void setpBankName(String pBankName) {
		this.pBankName = pBankName;
	}
	public String getpAcName() {
		return pAcName;
	}
	public void setpAcName(String pAcName) {
		this.pAcName = pAcName;
	}
	@Override
	public String toString() {
		return "ProjectDto [pNo=" + pNo + ", pTitle=" + pTitle + ", mEmail=" + mEmail + ", pDate=" + pDate + ", pPay="
				+ pPay + ", pNow=" + pNow + ", pCnt=" + pCnt + ", pContent=" + pContent + ", pImage=" + pImage
				+ ", pCategory=" + pCategory + ", pAccount=" + pAccount + ", pBankName=" + pBankName + ", pAcName="
				+ pAcName + "]";
	}
	
	
	
}
