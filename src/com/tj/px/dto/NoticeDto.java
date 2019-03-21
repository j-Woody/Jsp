package com.tj.px.dto;

import java.sql.Date;

public class NoticeDto {
	private int nNo;
	private String aId;
	private String nTitle;
	private String nContent;
	private String nImage;
	private String nImage2;
	private String nImage3;
	private Date nDate;
	public NoticeDto(int nNo, String aId, String nTitle, String nContent, String nImage, String nImage2, String nImage3,
			Date nDate) {
		super();
		this.nNo = nNo;
		this.aId = aId;
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.nImage = nImage;
		this.nImage2 = nImage2;
		this.nImage3 = nImage3;
		this.nDate = nDate;
	}
	public int getnNo() {
		return nNo;
	}
	public void setnNo(int nNo) {
		this.nNo = nNo;
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getnTitle() {
		return nTitle;
	}
	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}
	public String getnContent() {
		return nContent;
	}
	public void setnContent(String nContent) {
		this.nContent = nContent;
	}
	public String getnImage() {
		return nImage;
	}
	public void setnImage(String nImage) {
		this.nImage = nImage;
	}
	public String getnImage2() {
		return nImage2;
	}
	public void setnImage2(String nImage2) {
		this.nImage2 = nImage2;
	}
	public String getnImage3() {
		return nImage3;
	}
	public void setnImage3(String nImage3) {
		this.nImage3 = nImage3;
	}
	public Date getnDate() {
		return nDate;
	}
	public void setnDate(Date nDate) {
		this.nDate = nDate;
	}
	@Override
	public String toString() {
		return "NoticeDto [nNo=" + nNo + ", aId=" + aId + ", nTitle=" + nTitle + ", nContent=" + nContent + ", nImage="
				+ nImage + ", nImage2=" + nImage2 + ", nImage3=" + nImage3 + ", nDate=" + nDate + "]";
	}
	
	
}
