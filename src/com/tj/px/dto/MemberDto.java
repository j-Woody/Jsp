package com.tj.px.dto;

import java.sql.Date;

public class MemberDto {
	private String mEmail;
	private String mImage;
	private String mName;
	private String mPw;
	private Date mJdate;
	private String mLoc;
	private String mSelf;
	private String mPhone1;
	private String mPhone2;
	private String mPhone3;
	private int mAccess;
	private int Mban;
	public MemberDto(String mEmail, String mImage, String mName, String mPw, Date mJdate, String mLoc, String mSelf,
			String mPhone1, String mPhone2, String mPhone3, int mAccess, int mban) {
		super();
		this.mEmail = mEmail;
		this.mImage = mImage;
		this.mName = mName;
		this.mPw = mPw;
		this.mJdate = mJdate;
		this.mLoc = mLoc;
		this.mSelf = mSelf;
		this.mPhone1 = mPhone1;
		this.mPhone2 = mPhone2;
		this.mPhone3 = mPhone3;
		this.mAccess = mAccess;
		Mban = mban;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public String getmImage() {
		return mImage;
	}
	public void setmImage(String mImage) {
		this.mImage = mImage;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmPw() {
		return mPw;
	}
	public void setmPw(String mPw) {
		this.mPw = mPw;
	}
	public Date getmJdate() {
		return mJdate;
	}
	public void setmJdate(Date mJdate) {
		this.mJdate = mJdate;
	}
	public String getmLoc() {
		return mLoc;
	}
	public void setmLoc(String mLoc) {
		this.mLoc = mLoc;
	}
	public String getmSelf() {
		return mSelf;
	}
	public void setmSelf(String mSelf) {
		this.mSelf = mSelf;
	}
	public String getmPhone1() {
		return mPhone1;
	}
	public void setmPhone1(String mPhone1) {
		this.mPhone1 = mPhone1;
	}
	public String getmPhone2() {
		return mPhone2;
	}
	public void setmPhone2(String mPhone2) {
		this.mPhone2 = mPhone2;
	}
	public String getmPhone3() {
		return mPhone3;
	}
	public void setmPhone3(String mPhone3) {
		this.mPhone3 = mPhone3;
	}
	public int getmAccess() {
		return mAccess;
	}
	public void setmAccess(int mAccess) {
		this.mAccess = mAccess;
	}
	public int getMban() {
		return Mban;
	}
	public void setMban(int mban) {
		Mban = mban;
	}
	@Override
	public String toString() {
		return "MemberDto [mEmail=" + mEmail + ", mImage=" + mImage + ", mName=" + mName + ", mPw=" + mPw + ", mJdate="
				+ mJdate + ", mLoc=" + mLoc + ", mSelf=" + mSelf + ", mPhone1=" + mPhone1 + ", mPhone2=" + mPhone2
				+ ", mPhone3=" + mPhone3 + ", mAccess=" + mAccess + ", Mban=" + Mban + "]";
	}
	
}
