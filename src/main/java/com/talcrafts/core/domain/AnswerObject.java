package com.talcrafts.core.domain;

public class AnswerObject {

	/*---------------Demographic details -----------------*/

	private String male;
	private String female;
	private String tobacooyes;
	private String tobacoono;
	private String firstname;
	private String lastname;
	private String dob;

	public String getMale() {
		return male;
	}

	public void setMale(String male) {
		this.male = male;
	}

	public String getFemale() {
		return female;
	}

	public void setFemale(String female) {
		this.female = female;
	}

	public String getTobacooyes() {
		return tobacooyes;
	}

	public void setTobacooyes(String tobacooyes) {
		this.tobacooyes = tobacooyes;
	}

	public String getTobacoono() {
		return tobacoono;
	}

	public void setTobacoono(String tobacoono) {
		this.tobacoono = tobacoono;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	/*---------------Demographic details -----------------*/

	public static AnswerObject populate(String jsonStr) {
		String[] answerArray = jsonStr.split("&");
		AnswerObject object = new AnswerObject();
		for (String var : answerArray) {
			String[] split = var.split("=");

			if ("male".equalsIgnoreCase(split[0])) {
				object.setMale(split[1]);
			}

			else if ("female".equalsIgnoreCase(split[0])) {
				object.setFemale(split[1]);
			}

			else if ("firstname".equalsIgnoreCase(split[0])) {
				object.setFirstname(split[1]);
			}

			else if ("lastname".equalsIgnoreCase(split[0])) {
				object.setLastname(split[1]);
			} else if ("dob".equalsIgnoreCase(split[0])) {
				object.setDob(split[1]);
			}

			else if ("tobacooyes".equalsIgnoreCase(split[0])) {
				object.setTobacooyes(split[1]);
			} else if ("tobacoono".equalsIgnoreCase(split[0])) {
				object.setTobacoono(split[1]);
			}
		}
		return object;

	}
}
