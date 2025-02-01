package com.example.demo.helper;

import org.springframework.validation.BindingResult;

import com.example.demo.log.LogCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FormHelper {
	
	public static String JsonSerializeValidateError(BindingResult result) {
		try {
			var mapper = new ObjectMapper();
			var errorStr = mapper.writeValueAsString(result.getFieldErrors());
			return errorStr;
		} catch(JsonProcessingException e) {
			LogCreator.error(e.getOriginalMessage());
			return "";
		}
	}
}
