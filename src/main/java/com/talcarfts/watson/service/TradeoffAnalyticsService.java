package com.talcarfts.watson.service;

import com.talcarfts.watson.domain.Options;
import com.talcarfts.watson.domain.Problem;

public interface TradeoffAnalyticsService {

	public String getResolutionsForProblem(Problem problem, Options options);

	public String loadProfile(String profileName);

}
