package com.talcrafts.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.json.BasicJsonParser;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonHelper {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static final String jsonToString(Object value)
			throws JsonGenerationException, JsonMappingException, IOException {
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		StringWriter stringWriter = new StringWriter();
		objectMapper.writeValue(stringWriter, value);
		return stringWriter.toString();
	}

	public static List<Object> stringToJson(String jsonPath) throws Exception {
		BasicJsonParser basicJsonParser = new BasicJsonParser();
		InputStream inputStream = JsonHelper.class.getResourceAsStream(jsonPath);
		String jsonString = IOUtils.toString(inputStream);
		return basicJsonParser.parseList(jsonString);
	}

}