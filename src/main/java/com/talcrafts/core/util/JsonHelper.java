package com.talcrafts.core.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public final class JsonHelper {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static final String jsonToString(Object value)
			throws JsonGenerationException, JsonMappingException, IOException {
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		StringWriter stringWriter = new StringWriter();
		objectMapper.writeValue(stringWriter, value);
		return stringWriter.toString();
	}

	public static <T> java.util.List<T> jsonArrayStringToJson(String jsonArrayString, Class<T> classType)
			throws IOException {
		final CollectionType javaType = objectMapper.getTypeFactory().constructCollectionType(Collection.class,
				classType);
		return objectMapper.readValue(jsonArrayString, javaType);
	}

}