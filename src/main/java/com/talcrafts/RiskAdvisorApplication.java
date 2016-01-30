package com.talcrafts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import com.talcrafts.watson.servlet.ProductsRecommendationServlet;

@SpringBootApplication
@PropertySource("classpath:risk_advisor.properties")
public class RiskAdvisorApplication {

	private ProductsRecommendationServlet productsRecommendationServlet;

	public static void main(String[] args) {
		SpringApplication.run(RiskAdvisorApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean demoServletRegistrationBean() {
		return new ServletRegistrationBean(productsRecommendationServlet, "/demo/*");
	}

	@Bean
	public ServletRegistrationBean problemServletRegistrationBean() {
		return new ServletRegistrationBean(productsRecommendationServlet, "/problem/*");
	}

	@Autowired(required = false)
	public void setProductsRecommendationServlet(ProductsRecommendationServlet productsRecommendationServlet) {
		this.productsRecommendationServlet = productsRecommendationServlet;
	}

}
