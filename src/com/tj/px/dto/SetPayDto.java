package com.tj.px.dto;

public class SetPayDto {
	
		private int spNo;
		private int pNo;
		private String pName;
		private int pay;
		private String pexp;
		public SetPayDto(int spNo, int pNo, String pName, int pay, String pexp) {
			super();
			this.spNo = spNo;
			this.pNo = pNo;
			this.pName = pName;
			this.pay = pay;
			this.pexp = pexp;
		}
		public int getSpNo() {
			return spNo;
		}
		public void setSpNo(int spNo) {
			this.spNo = spNo;
		}
		public int getpNo() {
			return pNo;
		}
		public void setpNo(int pNo) {
			this.pNo = pNo;
		}
		public String getpName() {
			return pName;
		}
		public void setpName(String pName) {
			this.pName = pName;
		}
		public int getPay() {
			return pay;
		}
		public void setPay(int pay) {
			this.pay = pay;
		}
		public String getPexp() {
			return pexp;
		}
		public void setPexp(String pexp) {
			this.pexp = pexp;
		}
		@Override
		public String toString() {
			return "SetPayDto [spNo=" + spNo + ", pNo=" + pNo + ", pName=" + pName + ", pay=" + pay + ", pexp=" + pexp
					+ "]";
		}
		
		

}
