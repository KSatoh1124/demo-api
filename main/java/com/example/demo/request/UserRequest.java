package com.example.demo.request;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class UserRequest extends FormRequest {
	@NotEmpty
	private String userId;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	@Size(min=8,max=16)
	private String password;
	
	private String address;
	
	@Size(min=10,max=14)
	private String tel;
	
	private String birthday;
	
	private Integer gender;	
}
