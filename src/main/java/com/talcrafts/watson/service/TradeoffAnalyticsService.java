package com.talcrafts.watson.service;

import java.io.InputStream;
import java.util.List;

import com.talcrafts.RISK_CATEGORY;
import com.talcrafts.core.domain.Product;

public interface TradeoffAnalyticsService {

	public enum OBJECTIVE {
		TERM, PREMIUM, COVERAGE, CSR, CASH_VALUE, ALL;
	}

	public String getProductRecommendationsForRiskCategory(RISK_CATEGORY riskCategory, List<Product> availableProducts,
			List<OBJECTIVE> objectives);

	public String getResoultionForProblem(InputStream inputstream, String endpoint, String metadata);
}
