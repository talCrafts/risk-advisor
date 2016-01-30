package com.talcrafts.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.talcrafts.core.domain.AnswerObject;
import com.talcrafts.core.domain.User;

@RestController
@RequestMapping("/api/")
public class RiskAdvisorService{
	
    @RequestMapping(value="/form", method = RequestMethod.POST)
    public String submit(@RequestBody String jsonStr) {
    	AnswerObject answers= AnswerObject.populate(jsonStr);
    	
    	User.build(answers);
    	return jsonStr;
    }

}