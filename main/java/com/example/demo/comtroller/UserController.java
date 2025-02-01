package com.example.demo.comtroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.log.LogCreator;
import com.example.demo.model.User;
import com.example.demo.request.UserRequest;
import com.example.demo.response.MyFormResponse;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService)
	{
		this.userService = userService;
	}
	
	/**
	* 利用者登録
	* @param リクエストパラメータ、バリデート結果
	* @return Jsonをシリアライズしたレスポンスパラメータ、ステータスコード
	*/
	@PostMapping("/user/create")
	public ResponseEntity<?> store(@RequestBody @Validated UserRequest request, BindingResult result) 
	{
		if (result.hasErrors()) {
			LogCreator.info(result.toString());
			return new ResponseEntity<>(request.JsonSerializeValidateError(result),HttpStatus.BAD_REQUEST);
		}

		try {
			var user = userService.store(request);
			var response = new MyFormResponse<User>(user);
			return new ResponseEntity<>(response.getParameter(),HttpStatus.OK);
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
	@PostMapping("/user/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id) {
		try {
			var user = userService.show(id);
			var response = new MyFormResponse<User>(user);
			return new ResponseEntity<>(response.getParameter(),HttpStatus.OK);
		} catch (Exception e) {
			LogCreator.error(e.getMessage());
			return new ResponseEntity<>("DB登録に失敗",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
