package com.example.demo.request;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class CustomerRequest extends FormRequest {
	
	@NotEmpty
	private String name;
	
	@Size(min=7,max=7)
	private String postnumber;

	private String address;
	
	@Size(min=10,max=14)
	private String tel;
	
	private String birthday;
	
	private Integer gender;
	
	private String remarks;
}
