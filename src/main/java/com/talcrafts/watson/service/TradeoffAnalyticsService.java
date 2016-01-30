package com.talcrafts.watson.service;

import com.talcrafts.watson.domain.Options;
import com.talcrafts.watson.domain.Problem;

public interface TradeoffAnalyticsService {
	public String getResolutionsForProblem(Problem problem, Options options);
}
