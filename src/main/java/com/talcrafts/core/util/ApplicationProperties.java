package com.talcrafts.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

	@Autowired(required = false)
	private Environment environment;

	public String getTradeoffServiceBaseUrl() {
		return environment.getProperty("watson.tradeoff.service.base.url");
	}

	public String getTradeoffServiceUserName() {
		return environment.getProperty("watson.tradeoff.service.username");
	}

	public String getTradeoffServicePassword() {
		return environment.getProperty("watson.tradeoff.service.password");
	}

}
