package com.example.demo.service;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.UserRequest;

@Service
@Transactional
public class UserService {
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/**
	* リクエストパラメータをもとに新規登録
	* @param リクエストパラメータ
	* @return 作成された利用者ID
	*/
	public User store(UserRequest request) {
		var userModel = new User();
		userModel.bindingParamer(request.toMap());
		userRepository.save(userModel);
		
		return userModel;
	}
	
	public User show(int id) {
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}
}
