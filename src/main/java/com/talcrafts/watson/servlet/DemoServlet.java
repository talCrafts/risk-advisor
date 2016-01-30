/**
 * Copyright 2014 IBM Corp. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.talcrafts.watson.servlet;

import java.io.IOException;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.talcrafts.core.util.ApplicationProperties;

@Component
public class DemoServlet extends HttpServlet {

	private static final long serialVersionUID = -1200679495247430042L;
	private ApplicationProperties applicationProperties;

	@Autowired(required = false)
	public void setApplicationProperties(ApplicationProperties applicationProperties) {
		this.applicationProperties = applicationProperties;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("UTF-8");
			String reqURI = req.getRequestURI();
			String endpoint = reqURI.substring(reqURI.lastIndexOf('/') + 1);
			String url = applicationProperties.getTradeoffServiceBaseUrl() + "/v1/" + endpoint;
			URI uri = new URI(url).normalize();
			Request newReq = Request.Post(uri);
			newReq.addHeader("Accept", "application/json");
			String metadata = req.getHeader("x-watson-metadata");
			if (metadata != null) {
				metadata += "client-ip:" + req.getRemoteAddr();
				newReq.addHeader("x-watson-metadata", metadata);
			}
			InputStreamEntity entity = new InputStreamEntity(req.getInputStream());
			String requestBody = EntityUtils.toString(entity, "UTF-8");
			newReq.bodyString(requestBody, ContentType.APPLICATION_JSON);
			Executor executor = this.buildExecutor(uri);
			Response response = executor.execute(newReq);
			HttpResponse httpResponse = response.returnResponse();
			resp.setStatus(httpResponse.getStatusLine().getStatusCode());
			httpResponse.getEntity().writeTo(System.out);
			ServletOutputStream servletOutputStream = resp.getOutputStream();
			httpResponse.getEntity().writeTo(servletOutputStream);
			servletOutputStream.flush();
			servletOutputStream.close();
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	private Executor buildExecutor(URI url) {
		return Executor.newInstance()
				.auth(applicationProperties.getTradeoffServiceUserName(),
						applicationProperties.getTradeoffServicePassword())
				.authPreemptive(new HttpHost(url.getHost(), url.getPort(), url.getScheme()));
	}
}
