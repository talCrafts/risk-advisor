package com.talcrafts.core.product.filter;

import java.util.List;
import com.talcrafts.core.domain.Product;
import com.talcrafts.core.domain.User;

public interface ProductsFilteringService
{
	public List<Product> getFilteredProductList(User user, List<Product> products);
}