package com.example.demo.response;

import java.util.HashMap;

import lombok.Data;

@Data
public class MyFormResponse<T> extends FormResponse<T> {
	public HashMap<String, Object> parameter;
	
	public MyFormResponse(T obj) {
    	super(obj);
    	this.parameter = this.toMapParameter;
    }   
}
