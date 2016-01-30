package com.talcrafts.core.domain;


import java.math.BigDecimal;

public class User {
	
	private String gender;
	private boolean tobacco;
	private String dob;
	private BigDecimal weight;
	private BigDecimal height;
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public boolean isTobacco() {
		return tobacco;
	}
	public void setTobacco(boolean tobacco) {
		this.tobacco = tobacco;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public BigDecimal getHeight() {
		return height;
	}
	public void setHeight(BigDecimal height) {
		this.height = height;
	}
	
		

}
