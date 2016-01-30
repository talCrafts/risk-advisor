package com.talcrafts.core.domain;

import java.util.Date;

public class User {

	private String gender;
	private boolean tobacco;
	private Date dob;
	private int weightInKgs;
	private int heightInInches;

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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getWeightInKgs() {
		return weightInKgs;
	}

	public void setWeightInKgs(int weightInKgs) {
		this.weightInKgs = weightInKgs;
	}

	public int getHeightInInches() {
		return heightInInches;
	}

	public void setHeightInInches(int heightInInches) {
		this.heightInInches = heightInInches;
	}

	@Override
	public String toString() {
		return "User [gender=" + gender + ", tobacco=" + tobacco + ", dob=" + dob + ", weightInKgs=" + weightInKgs
				+ ", heightInInches=" + heightInInches + "]";
	}

}
