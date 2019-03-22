package com.tj.px.dto;

public class AdminDto {
	private String aId;
	private String aPw;
	private String name = "운영자";
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getaPw() {
		return aPw;
	}
	public void setaPw(String aPw) {
		this.aPw = aPw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AdminDto(String aId, String aPw, String name) {
		super();
		this.aId = aId;
		this.aPw = aPw;
		this.name = name;
	}
 
}
