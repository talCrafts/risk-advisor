package com.talcrafts.core.util;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import com.talcrafts.core.domain.Product;

public class JsonHelperTest {

	@Test
	public void testJsonArrayStringToJson() throws Exception {
		String jsonString = FileUtils
				.readFileToString(new File(this.getClass().getResource("/product.json").toURI().getPath()));
		List<Product> objects = JsonHelper.jsonArrayStringToJson(jsonString, Product.class);
		Assert.assertTrue(objects.size() == 13);
		Assert.assertTrue(objects.get(0) instanceof Product);
	}
}
