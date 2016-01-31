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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.talcrafts.watson.service.TradeoffAnalyticsService;

@Component
public class TradeoffServiceServlet extends HttpServlet {

	private TradeoffAnalyticsService analyticsService;

	@Autowired(required = false)
	public void setAnalyticsService(TradeoffAnalyticsService analyticsService) {
		this.analyticsService = analyticsService;
	}

	private static final long serialVersionUID = -1200679495247430042L;

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
			String metadata = req.getHeader("x-watson-metadata");
			if (metadata != null) {
				metadata += "client-ip:" + req.getRemoteAddr();
			}
			String response = analyticsService.getResoultionForProblem(req.getInputStream(), endpoint, metadata);
			resp.getWriter().write(response);
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

}
