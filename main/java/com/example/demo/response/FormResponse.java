package com.example.demo.response;

import java.util.HashMap;

import com.example.demo.log.LogCreator;

public class FormResponse<T>{
	protected HashMap<String, Object> toMapParameter;
	
	public FormResponse(T obj) {
		try {
			var map = new HashMap<String,Object>();
			var fields = obj.getClass().getDeclaredFields();
			for (var field : fields) {
				field.setAccessible(true);
				map.put(field.getName(),field.get(obj));
			}
			
			this.toMapParameter = map;
		} catch (Exception e) {
			LogCreator.error(e.getMessage());
		}
	}
}
