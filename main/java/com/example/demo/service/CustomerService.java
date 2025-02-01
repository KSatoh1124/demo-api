package com.example.demo.service;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.request.CustomerRequest;

@Service
@Transactional
public class CustomerService {
	private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerService(CustomerRepository clientRepository) {
		this.customerRepository = clientRepository;
	}
	
	public Page<Customer> index(String page) {
		var currentPage = page == null? 0 : Integer.parseInt(page);
		var pagenate = PageRequest.of(currentPage, 5);
		var customers = customerRepository.findAll(pagenate);
		return customers;
	}
	/**
	* リクエストパラメータをもとに新規登録
	* @param リクエストパラメータ
	* @return 作成された利用者ID
	*/
	public Customer store(CustomerRequest request) {
		var customerModel = new Customer();
		customerModel.bindingParamer(request.toMap());
		this.customerRepository.save(customerModel);
		
		return customerModel;
	}
	
	public Customer show(int id) {
		Optional<Customer> customer = this.customerRepository.findById(id);
		return customer.get();
	}
	
	public Customer update(CustomerRequest request,int id) {
		Optional<Customer> customerOption = this.customerRepository.findById(id);
		var customerModel = customerOption.get();

		customerModel.bindingParamer(request.toMap());
		this.customerRepository.save(customerModel);

		return customerModel;

	}
	
	public boolean destroy(Integer id) {
		Optional<Customer> customer = this.customerRepository.findById(id);
		this.customerRepository.delete(customer.get());
		
		return true;
	}
}
