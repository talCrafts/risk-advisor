package com.talcrafts.core.util;

import org.junit.Assert;
import org.junit.Test;

import com.talcrafts.TestObjectMother;

public class BMICalculatorTest {
	@Test
	public void test() {
		int bmi = BMICalculator
				.calculate(TestObjectMother.createTestUserForAgeAndSmokerStatusAndHeightAndWeight(30, false, 70, 76));
		Assert.assertEquals(24, bmi);
	}
}
