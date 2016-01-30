package com.talcrafts.watson.service;

import java.util.List;

import com.talcrafts.RISK_CATEGORY;
import com.talcrafts.core.domain.Product;

public interface TradeoffAnalyticsService {
	public String getProductRecommendationsForRiskCategory(RISK_CATEGORY riskCategory, List<Product> availableProducts);
}
