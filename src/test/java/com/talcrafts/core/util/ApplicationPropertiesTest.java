package com.talcrafts.core.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.talcrafts.RiskAdvisorApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(RiskAdvisorApplication.class)
public class ApplicationPropertiesTest {

	@Autowired
	private ApplicationProperties applicationProperties;

	@Test
	public void testGetUsername() {
		Assert.assertEquals("fd548db1-624f-4169-a2f6-6c2e46e5e5a3", applicationProperties.getTradeoffServiceUserName());
	}

}
