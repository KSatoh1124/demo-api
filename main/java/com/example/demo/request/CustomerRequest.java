package com.example.demo.request;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class CustomerRequest extends FormRequest {
	
	@NotEmpty(message ="必ず入力してください")
	@Pattern(regexp = "^[ぁ-ん]([ぁ-ん\\s　]*[ぁ-ん])?$",message ="ひらがなで入力してください")
	private String nameKana;

	private String name;
	
	@Size(min=7,max=7,message ="郵便番号")
	private String postnumber;

	private String address;
	
	@Size(min=10,max=14,message ="ハイフンを含めず１０～１４文字の範囲で入力してください")
	@Pattern(regexp = "^[0-9０-９]*$",message ="数字で入力してください")
	private String tel;
	
	private String birthday;
	
	private Integer gender;
	
	private String remarks;
}
