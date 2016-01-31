package com.talcrafts.watson.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talcrafts.RISK_CATEGORY;
import com.talcrafts.core.domain.Product;
import com.talcrafts.core.util.ApplicationProperties;
import com.talcrafts.core.util.JsonHelper;
import com.talcrafts.watson.domain.Option;
import com.talcrafts.watson.domain.Options;
import com.talcrafts.watson.domain.Problem;

@Service
public class TradeoffAnalyticsServiceImpl implements TradeoffAnalyticsService {

	private ApplicationProperties applicationProperties;

	@Override
	public String getProductRecommendationsForRiskCategory(RISK_CATEGORY riskCategory, List<Product> availableProducts,
			List<OBJECTIVE> objectives) {
		try {
			Option[] optionsArray = new Option[availableProducts.size()];
			for (int i = 0; i < availableProducts.size(); i++) {
				optionsArray[i] = availableProducts.get(i).toOption();
			}
			Problem problem = riskCategory.getProblem(objectives);
			problem.setSubject(riskCategory.getSubject());
			Options options = new Options();
			options.setOptions(optionsArray);
			StringBuilder requestBuilder = new StringBuilder();
			String problemString = JsonHelper.jsonToString(problem);
			requestBuilder.append(problemString.substring(0, problemString.lastIndexOf("}")));
			String optionsString = JsonHelper.jsonToString(options);
			requestBuilder.append(",");
			requestBuilder.append(optionsString.substring(optionsString.indexOf("{") + 1));
			String request = requestBuilder.toString();
			String solution = getResoultionForProblem(new ByteArrayInputStream(request.getBytes()), "dilemmas", "");
			return solution;
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

	}

	@Override
	public String getResoultionForProblem(InputStream inputstream, String endpoint, String metadata) {
		try {
			String url = applicationProperties.getTradeoffServiceBaseUrl() + "/v1/" + endpoint;
			URI uri = new URI(url).normalize();
			Request newReq = Request.Post(uri);
			newReq.addHeader("Accept", "application/json");
			if (metadata != null) {
				newReq.addHeader("x-watson-metadata", metadata);
			}
			InputStreamEntity entity = new InputStreamEntity(inputstream);
			String requestBody = EntityUtils.toString(entity, "UTF-8");
			newReq.bodyString(requestBody, ContentType.APPLICATION_JSON);
			Executor executor = this.buildExecutor(uri);
			Response response = executor.execute(newReq);
			HttpResponse httpResponse = response.returnResponse();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			httpResponse.getEntity().writeTo(byteArrayOutputStream);
			return new String(byteArrayOutputStream.toByteArray());
		} catch (IOException | URISyntaxException exception) {
			throw new RuntimeException(exception);
		} finally {
			IOUtils.closeQuietly(inputstream);
		}
	}

	private Executor buildExecutor(URI url) {
		return Executor.newInstance()
				.auth(applicationProperties.getTradeoffServiceUserName(),
						applicationProperties.getTradeoffServicePassword())
				.authPreemptive(new HttpHost(url.getHost(), url.getPort(), url.getScheme()));
	}

	@Autowired(required = false)
	public void setApplicationProperties(ApplicationProperties applicationProperties) {
		this.applicationProperties = applicationProperties;
	}

}
