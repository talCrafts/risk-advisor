package com.talcrafts.core.util;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class JsonHelperTest {

	@Test
	public void test() throws Exception {
		List<Object> objects = JsonHelper.stringToJson("/product.json");
		Assert.assertTrue(objects.size() == 13);
	}
}
