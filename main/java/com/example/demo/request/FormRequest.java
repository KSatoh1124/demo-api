package com.example.demo.request;

import java.util.HashMap;

import org.springframework.validation.BindingResult;

import com.example.demo.log.LogCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class FormRequest {
	
	/**
	* バリデートエラーオブジェクトをシリアライズ
	* @param バリデート結果
	* @return シリアライズ化したバリデートエラーオブジェクト
	*/
	public String JsonSerializeValidateError(BindingResult result) {
		try {
			var mapper = new ObjectMapper();
			var errorStr = mapper.writeValueAsString(result.getFieldErrors());
			return errorStr;
		} catch(Exception e) {
			LogCreator.error(e.getMessage());
			return "";
		}
	}
	
	/**
	* 自身のプロパティをもとにHashMapを生成
	* @param バリデート結果
	* @return シリアライズ化したバリデートエラーオブジェクト
	*/
	public HashMap<String,Object> toMap() {
		try {
			var map = new HashMap<String,Object>();
			var fields = this.getClass().getDeclaredFields();
			for (var field : fields) {
				field.setAccessible(true);
				map.put(field.getName(),field.get(this));
			}
			
			return map;
			
		} catch (Exception e) {
			LogCreator.error(e.getMessage());
			return new HashMap<String, Object>();
		}
	}
}
