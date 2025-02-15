package com.example.demo.comtroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.log.LogCreator;
import com.example.demo.request.CustomerRequest;
import com.example.demo.service.CustomerService;

@RestController
public class CustomerController {
	private CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService)
	{
		this.customerService = customerService;
	}
	
	
	/**
	* 利用者一覧取得
	* @param クエリストリングス
	* @return Jsonをシリアライズしたレスポンスパラメータ、ステータスコード
	*/
	@GetMapping("/customer")
	public ResponseEntity<?> index(@RequestParam (required = false) String page,@RequestParam (required = false) String serachParam,@RequestParam (required = false) String sort) {
		try {
			var customers = this.customerService.index(page,serachParam,sort);
			return new ResponseEntity<>(customers,HttpStatus.OK);	
		} catch (Exception e) {
			LogCreator.error(e.getMessage());
			return new ResponseEntity<>("データの取得に失敗",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	* 利用者登録
	* @param リクエストパラメータ、バリデート結果
	* @return Jsonをシリアライズしたレスポンスパラメータ、ステータスコード
	*/
	@PostMapping("/customer")
	public ResponseEntity<?> store(@RequestBody @Validated CustomerRequest request, BindingResult result)  {
		if (result.hasErrors()) {
			LogCreator.info(result.toString());
			return new ResponseEntity<>(request.JsonSerializeValidateError(result),HttpStatus.BAD_REQUEST);
		}

		try {
			var customer = this.customerService.store(request);
			return new ResponseEntity<>(customer,HttpStatus.OK);
		} catch (Exception e) {
			LogCreator.error(e.getMessage());
			return new ResponseEntity<>("DB登録に失敗",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	* 利用者取得
	* @param ルートパラメータ
	* @return Jsonをシリアライズしたレスポンスパラメータ、ステータスコード
	*/
	@GetMapping("/customer/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id) {
		try {
			var customer = this.customerService.show(id);
			return new ResponseEntity<>(customer,HttpStatus.OK);
		} catch (Exception e) {
			LogCreator.error(e.getMessage());
			return new ResponseEntity<>("DB取得yに失敗",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	* 利用者更新
	* @param ルートパラメータ
	* @return Jsonをシリアライズしたレスポンスパラメータ、ステータスコード
	*/
	@PatchMapping("/customer/{id}")
	public ResponseEntity<?> Update(@RequestBody @Validated CustomerRequest request, BindingResult result,@PathVariable Integer id) {
		if (result.hasErrors()) {
			LogCreator.info(result.toString());
			return new ResponseEntity<>(request.JsonSerializeValidateError(result),HttpStatus.BAD_REQUEST);
		}

		try {
			var customer = this.customerService.update(request,id);
			return new ResponseEntity<>(customer,HttpStatus.OK);
		} catch (Exception e) {
			LogCreator.error(e.getMessage());
			return new ResponseEntity<>("DB更新に失敗",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	* 利用者削除
	* @param ルートパラメータ
	* @return Jsonをシリアライズしたレスポンスパラメータ、ステータスコード
	*/
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> destroy(@PathVariable Integer id) {
		try {
			var isSuucess = this.customerService.destroy(id);
			return new ResponseEntity<>(isSuucess,HttpStatus.OK);
		} catch (Exception e) {
			LogCreator.error(e.getMessage());
			return new ResponseEntity<>("DB削除に失敗",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
