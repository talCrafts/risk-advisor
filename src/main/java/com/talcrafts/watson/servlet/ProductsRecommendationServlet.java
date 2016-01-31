package com.talcrafts.watson.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
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
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException {
		try {
			// Enumeration<String> enumeration = req.getParameterNames();
			// while (enumeration.hasMoreElements()) {
			// String parameterName = enumeration.nextElement();
			// System.out.println(parameterName +
			// req.getParameterValues(parameterName));
			// }
			String jsonString = FileUtils
					.readFileToString(new File(this.getClass().getResource("/json/product.json").toURI().getPath()));
			List<Product> products = JsonHelper.jsonArrayStringToJson(jsonString, Product.class);
			PrintWriter writer = resp.getWriter();
			writer.write(tradeoffAnalyticsService.getProductRecommendationsForRiskCategory(RISK_CATEGORY.HEALTH,
					products, getObjectives(req)));
			writer.flush();
			writer.close();
		} catch (IOException | URISyntaxException exception) {
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
