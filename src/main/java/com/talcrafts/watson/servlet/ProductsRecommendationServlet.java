package com.talcrafts.watson.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.talcrafts.RISK_CATEGORY;
import com.talcrafts.core.domain.Product;
import com.talcrafts.core.util.JsonHelper;
import com.talcrafts.watson.service.TradeoffAnalyticsService;
import com.talcrafts.watson.service.TradeoffAnalyticsService.OBJECTIVE;

@Component
@Scope(value = "prototype")
public class ProductsRecommendationServlet extends HttpServlet {

	private TradeoffAnalyticsService tradeoffAnalyticsService;

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) {
		try {
			List<OBJECTIVE> objectives = getObjectives(req);
			byte[] bytes = new byte[req.getContentLength()];
			IOUtils.readFully(req.getInputStream(), bytes);
			String availableProductsJson = new String(bytes);
			String productRecommendations = "";
			if (availableProductsJson != null && StringUtils.isNotBlank(availableProductsJson)) {
				List<Product> products = JsonHelper.jsonArrayStringToJson(availableProductsJson, Product.class);
				productRecommendations = tradeoffAnalyticsService
						.getProductRecommendationsForRiskCategory(RISK_CATEGORY.HEALTH, products, objectives);

			}
			PrintWriter writer = resp.getWriter();
			writer.write(productRecommendations);
			writer.flush();
			writer.close();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

	}

	private List<OBJECTIVE> getObjectives(final HttpServletRequest req) {
		List<OBJECTIVE> objectives = new ArrayList<>();
		String[] problemCriteria = req.getParameterValues("problemCriteria");
		if (problemCriteria != null && problemCriteria.length > 0) {
			for (String problem : problemCriteria) {
				objectives.add(OBJECTIVE.valueOf(problem));
			}
		} else {
			objectives.add(OBJECTIVE.ALL);
		}
		return objectives;
	}

	@Autowired(required = false)
	public void setTradeoffAnalyticsService(TradeoffAnalyticsService tradeoffAnalyticsService) {
		this.tradeoffAnalyticsService = tradeoffAnalyticsService;
	}
}
