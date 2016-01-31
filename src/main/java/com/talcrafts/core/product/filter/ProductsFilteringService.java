package com.talcrafts.core.product.filter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;

import com.talcrafts.core.domain.Product;
import com.talcrafts.core.domain.User;
import com.talcrafts.core.util.MortalityRateAndRiskFinder;

public class ProductsFilteringService {
	public static List<Product> getFilteredProductList(User user, List<Product> allProducts) {
		List<Product> filteredProductList = new ArrayList<Product>();
		int age = MortalityRateAndRiskFinder.findAge(user.getDob());
		Double riskFactor = MortalityRateAndRiskFinder.findMortalityRiskFactor(user);
		String risk = MortalityRateAndRiskFinder.findRisk(riskFactor);
		boolean tobacco = user.isTobacco();
		for (Product prod : allProducts) {
			if (age <= Integer.parseInt(prod.getMaxAge())
					&& (BooleanUtils.toBoolean(tobacco) == BooleanUtils.toBoolean(prod.getSmoker()))
					&& risk.equalsIgnoreCase(prod.getRisk())) {
				filteredProductList.add(prod);
			}
		}
		return filteredProductList;
	}
}