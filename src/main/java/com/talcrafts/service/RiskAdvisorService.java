package com.talcrafts.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.talcrafts.core.domain.AnswerObject;
import com.talcrafts.core.domain.Product;
import com.talcrafts.core.domain.User;
import com.talcrafts.core.product.filter.ProductsFilteringService;
import com.talcrafts.core.util.JsonHelper;

@RestController
@RequestMapping("/api/")
public class RiskAdvisorService{
	
    @RequestMapping(value="/form", method = RequestMethod.POST)
    public String submit(@RequestBody String jsonStr) {
    	User userDetails = User.build(AnswerObject.populate(jsonStr));
    	String jsonString;
		try {
			jsonString = FileUtils
					.readFileToString(new File(this.getClass().getResource("/product.json").toURI().getPath()));
			List<Product> allProducts = JsonHelper.jsonArrayStringToJson(jsonString, Product.class);
			List<Product> filteredProductList = ProductsFilteringService.getFilteredProductList(userDetails, allProducts);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
    	return jsonStr;
    }

    
    @RequestMapping(value="/get")
    public String get() {
    	return "GET";
    }
}