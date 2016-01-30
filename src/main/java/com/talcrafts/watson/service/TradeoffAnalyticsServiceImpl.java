package com.talcrafts.watson.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.TradeoffAnalytics;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.Dilemma;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.Option;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.Problem;
import com.talcrafts.RISK_CATEGORY;
import com.talcrafts.core.domain.Product;
import com.talcrafts.core.util.ApplicationProperties;
import com.talcrafts.core.util.JsonHelper;

@Service
public class TradeoffAnalyticsServiceImpl implements TradeoffAnalyticsService {

	private ApplicationProperties applicationProperties;

	@Override
	public String getProductRecommendationsForRiskCategory(RISK_CATEGORY riskCategory,
			List<Product> availableProducts) {
		try {
			List<Option> options = new ArrayList<>();
			for (Product product : availableProducts) {
				options.add(product.toOption());
			}
			TradeoffAnalytics service = new TradeoffAnalytics();
			service.setUsernameAndPassword(applicationProperties.getTradeoffServiceUserName(),
					applicationProperties.getTradeoffServicePassword());
			Problem problem = riskCategory.getProblem();
			problem.setOptions(options);
			Dilemma dilemma = service.dilemmas(problem);
			String dilemmaString = JsonHelper.jsonToString(dilemma);
			System.out.println(dilemmaString);
			return dilemmaString;
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

	}

	@Autowired(required = false)
	public void setApplicationProperties(ApplicationProperties applicationProperties) {
		this.applicationProperties = applicationProperties;
	}

}
