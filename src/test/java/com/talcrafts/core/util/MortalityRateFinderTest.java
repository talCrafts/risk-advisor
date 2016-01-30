package com.talcrafts.core.util;

import org.junit.Assert;
import org.junit.Test;

import com.talcrafts.TestObjectMother;
import com.talcrafts.core.domain.User;

public class MortalityRateFinderTest {
	@Test
	public void testAge30AndNonSmoker() {
		User user = TestObjectMother.createTestUserForAgeAndSmokerStatusAndHeightAndWeight(30, false, 70, 76);
		Double mortalityRate = MortalityRateFinder.findMortalityRiskFactor(user);
		Assert.assertNotNull(mortalityRate);
		Assert.assertEquals(new Double("1.0058"), mortalityRate);
	}

	@Test
	public void testAge30AndSmoker() {
		User user = TestObjectMother.createTestUserForAgeAndSmokerStatusAndHeightAndWeight(30, true, 70, 76);
		Double mortalityRate = MortalityRateFinder.findMortalityRiskFactor(user);
		Assert.assertNotNull(mortalityRate);
		Assert.assertEquals(new Double("21.0058"), mortalityRate);
	}

	@Test
	public void testAge48AndSmoker() {
		User user = TestObjectMother.createTestUserForAgeAndSmokerStatusAndHeightAndWeight(48, true, 64, 86);
		Double mortalityRate = MortalityRateFinder.findMortalityRiskFactor(user);
		Assert.assertNotNull(mortalityRate);
		Assert.assertEquals(new Double("66.61174"), mortalityRate);
	}

	@Test
	public void testAge89AndNonSmoker() {
		User user = TestObjectMother.createTestUserForAgeAndSmokerStatusAndHeightAndWeight(89, false, 50, 57);
		Double mortalityRate = MortalityRateFinder.findMortalityRiskFactor(user);
		Assert.assertNotNull(mortalityRate);
		Assert.assertEquals(new Double("114.485"), mortalityRate);
	}

	@Test
	public void testNotInBMI() {
		User user = TestObjectMother.createTestUserForAgeAndSmokerStatusAndHeightAndWeight(30, false, 200, 2);
		Double mortalityRate = MortalityRateFinder.findMortalityRiskFactor(user);
		Assert.assertNotNull(mortalityRate);
		Assert.assertEquals(new Double("101.0057"), mortalityRate);
	}
}
