package com.talcrafts.core.util;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import com.talcrafts.core.domain.User;

public class MortalityRateFinderTest {
	@Test
	public void testAge30() {
		MortalityRateFinder mortalityRateFinder = new MortalityRateFinder();
		User user = createTestUser(30);
		Double mortalityRate = mortalityRateFinder.findMortalityRate(user);
		Assert.assertNotNull(mortalityRate);
		Assert.assertEquals(new Double("0.10058"), mortalityRate);
	}

	@Test
	public void testAge40() {
		MortalityRateFinder mortalityRateFinder = new MortalityRateFinder();
		User user = createTestUser(40);
		Double mortalityRate = mortalityRateFinder.findMortalityRate(user);
		Assert.assertNotNull(mortalityRate);
		Assert.assertEquals(new Double("0.14822"), mortalityRate);
	}

	@Test
	public void testAge50() {
		MortalityRateFinder mortalityRateFinder = new MortalityRateFinder();
		User user = createTestUser(50);
		Double mortalityRate = mortalityRateFinder.findMortalityRate(user);
		Assert.assertNotNull(mortalityRate);
		Assert.assertEquals(new Double("0.32001"), mortalityRate);
	}

	@Test
	public void testAge60() {
		MortalityRateFinder mortalityRateFinder = new MortalityRateFinder();
		User user = createTestUser(60);
		Double mortalityRate = mortalityRateFinder.findMortalityRate(user);
		Assert.assertNotNull(mortalityRate);
		Assert.assertEquals(new Double("0.90322"), mortalityRate);
	}

	@Test
	public void testAge97() {
		MortalityRateFinder mortalityRateFinder = new MortalityRateFinder();
		User user = createTestUser(97);
		Double mortalityRate = mortalityRateFinder.findMortalityRate(user);
		Assert.assertNotNull(mortalityRate);
		Assert.assertEquals(new Double("7.42819"), mortalityRate);
	}

	@Test
	public void testAge54() {
		MortalityRateFinder mortalityRateFinder = new MortalityRateFinder();
		User user = createTestUser(54);
		Double mortalityRate = mortalityRateFinder.findMortalityRate(user);
		Assert.assertNotNull(mortalityRate);
		Assert.assertEquals(new Double("0.32001"), mortalityRate);
	}

	private User createTestUser(int age) {
		User user = new User();
		DateTime dateTime = new DateTime();
		user.setDob(dateTime.minusYears(age).toDate());
		user.setGender("Female");
		user.setTobacco(false);
		return user;
	}
}
