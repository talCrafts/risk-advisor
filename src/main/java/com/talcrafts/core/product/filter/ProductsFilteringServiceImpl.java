package com.talcrafts.core.product.filter;

import java.util.ArrayList;
import java.util.List;
import com.talcrafts.core.domain.Product;
import com.talcrafts.core.domain.User;
import com.talcrafts.core.util.MortalityRateAndRiskFinder;

public class ProductsFilteringServiceImpl implements ProductsFilteringService
{
	@Override
	public List<Product> getFilteredProductList(User user, List<Product> allProducts)
	{
		List<Product> filteredProductList = new ArrayList<Product>();
		int age = MortalityRateAndRiskFinder.findAge(user.getDob());
		boolean isSmoker = user.isTobacco();
		Double riskFactor = MortalityRateAndRiskFinder.findMortalityRiskFactor(user);
		String risk = MortalityRateAndRiskFinder.findRisk(riskFactor);
		for (Product prod : allProducts)
		{
			if (age == Integer.parseInt(prod.getMaxAge()) && isSmoker == Boolean.valueOf(prod.getSmoker())
					&& risk.equalsIgnoreCase(prod.getRisk()))
			{
				filteredProductList.add(prod);
			}
		}
		return filteredProductList;
	}
}