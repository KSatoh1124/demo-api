package com.example.demo.model;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.log.LogCreator;
import com.example.demo.utility.Common;

public abstract class MyModel {
	/**
	* リクエストパラメータと自身のプロパティを紐づける
	* @param リクエストパラメータ
	*/
	public void bindingParamer(HashMap<String,Object> requestParameter) {
		try {
			for (Map.Entry<String, Object> entity : requestParameter.entrySet()) {
				var key = entity.getKey();
				var value = entity.getValue();
				
				var field = this.getClass().getDeclaredField(key);
				field.setAccessible(true);
				
				switch(field.getType().getSimpleName()) {
					case "LocalDate":
						if (value != "") {
							var strDate = (String) value;
							var date = Common.stringToDate(strDate, "yyyy-MM-dd");
							if (date != null) {
								field.set(this,date);
							}
						}
						break;
					default:
						field.set(this, value);
						break;
				}
			}
		} catch (Exception e) {
			LogCreator.error(e.getMessage());
		}
	}
}
