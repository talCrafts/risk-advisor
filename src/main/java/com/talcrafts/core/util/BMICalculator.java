package com.talcrafts.core.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import com.talcrafts.core.domain.User;

public class BMICalculator {
	public static int calculate(User user) {
		int heightInInches = user.getHeightInInches();
		int weightInKgs = user.getWeightInKgs();
		BigDecimal bmiInBigDecimal = new BigDecimal(
				(weightInKgs) / ((heightInInches * .0254) * (heightInInches * .0254)),
				new MathContext(2, RoundingMode.FLOOR));
		return bmiInBigDecimal.intValue();
	}
}
