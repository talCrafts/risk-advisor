package com.talcrafts.core.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	public static User build(AnswerObject userParameters) {
		User user = new User();
		user.setGender("on".equalsIgnoreCase(userParameters.getMale())?"male":"female");
		user.setTobacco("on".equalsIgnoreCase(userParameters.getTobacooyes())?true:false);
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dob = format.parse(userParameters.getDob());
			user.setDob(dob);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		user.setHeightInInches(userParameters.getHeightInFeet()*12 + userParameters.getHeightInInches());
		user.setWeightInKgs(userParameters.getWeight());
		System.out.println(user);
		return user;
	}

}
