package com.talcrafts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

import com.talcrafts.servlet.ProductsRecommendationServlet;
import com.talcrafts.watson.servlet.DemoServlet;

@SpringBootApplication
public class RiskAdvisorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiskAdvisorApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean demoServletRegistrationBean() {
		return new ServletRegistrationBean(new DemoServlet(), "/demo/*");
	}

	@Bean
	public ServletRegistrationBean problemServletRegistrationBean() {
		return new ServletRegistrationBean(new ProductsRecommendationServlet(), "/problem/*");
	}


}
