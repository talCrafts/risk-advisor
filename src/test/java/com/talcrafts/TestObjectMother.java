package com.talcrafts;

import org.joda.time.DateTime;

import com.talcrafts.core.domain.Product;
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

	public static Product createTestProduct(String code, String name, String carrierName, int maxAge,
			Double riskFactor) {
		Product product = new Product(code, name, carrierName);
		product.setMaxAge(String.valueOf(maxAge));
		product.setRisk(riskFactor);
		product.setSmoker("No");
		return product;
	}
}
