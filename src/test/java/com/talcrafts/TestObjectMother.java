package com.talcrafts;

import org.joda.time.DateTime;

import com.talcrafts.core.domain.User;

public class TestObjectMother {
	public static User createTestUserForAgeAndSmokerStatusAndHeightAndWeight(int age, boolean smoker,
			int heightInInches, int weightInKgs) {
		User user = new User();
		DateTime dateTime = new DateTime();
		user.setDob(dateTime.minusYears(age).toDate());
		user.setGender("Female");
		user.setTobacco(smoker);
		user.setHeightInInches(heightInInches);
		user.setWeightInKgs(weightInKgs);
		return user;
	}
}
