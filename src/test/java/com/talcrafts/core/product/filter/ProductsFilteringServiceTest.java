package com.talcrafts.core.product.filter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.talcrafts.TestObjectMother;
import com.talcrafts.core.domain.Product;

public class ProductsFilteringServiceTest {

	@Test
	public void test() {
		List<Product> products = new ArrayList<Product>();
		products.add(TestObjectMother.createTestProduct("1", "1", "carrierOne", 60, "Low"));
		products.add(TestObjectMother.createTestProduct("2", "2", "carrierOne", 60, "Low"));
		products.add(TestObjectMother.createTestProduct("3", "3", "carrierOne", 60, "Low"));
		products.add(TestObjectMother.createTestProduct("4", "4", "carrierOne", 60, "Low"));
		products.add(TestObjectMother.createTestProduct("5", "5", "carrierOne", 25, "Low"));
		products.add(TestObjectMother.createTestProduct("6", "6", "carrierOne", 60, "Low"));
		products.add(TestObjectMother.createTestProduct("7", "7", "carrierOne", 60, "Low"));
		products.add(TestObjectMother.createTestProduct("8", "8", "carrierOne", 60, "Low"));
		products.add(TestObjectMother.createTestProduct("9", "9", "carrierOne", 60, "Low"));
		products.add(TestObjectMother.createTestProduct("10", "10", "carrierOne", 60, "Low"));
		products.add(TestObjectMother.createTestProduct("11", "11", "carrierOne", 60, "Low"));
		products.add(TestObjectMother.createTestProduct("12", "12", "carrierOne", 60, "Low"));
		products.add(TestObjectMother.createTestProduct("13", "13", "carrierOne", 60, "Low"));
		products.add(TestObjectMother.createTestProduct("14", "14", "carrierOne", 60, "Low"));

		List<Product> filteredProductList = ProductsFilteringService.getFilteredProductList(
				TestObjectMother.createTestUserForAgeAndSmokerStatusAndHeightAndWeight(30, false, 70, 76), products);
		Assert.assertTrue(filteredProductList.size() == 13);
	}
}
