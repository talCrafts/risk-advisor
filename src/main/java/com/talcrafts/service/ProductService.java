package com.talcrafts.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductService {

	@RequestMapping(name = "/test")
	public String test(@RequestParam(name = "test") String test) {
		// to test use 
		// http://localhost:8080/test?test=test
		return test + 1;
	}
}
