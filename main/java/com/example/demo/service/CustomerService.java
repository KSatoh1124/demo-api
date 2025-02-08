package com.example.demo.service;
import java.util.HashMap;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.request.CustomerRequest;
import com.example.demo.specification.CustomerSpecification;
import com.example.demo.utility.Common;

@Service
@Transactional
public class CustomerService {
	private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerService(CustomerRepository clientRepository) {
		this.customerRepository = clientRepository;
	}
	
	public Page<Customer> index(String request, String searchParam) {
		var currentPage = request == null? 0 : Integer.parseInt(request);
		var pagenate = PageRequest.of(currentPage, 5);
		
		var criterias = new HashMap<String, String>();
		if (searchParam != null) {
			var jsonNode = Common.base64ToJsonNode(searchParam);
			if (jsonNode != null) {
				jsonNode.fields().forEachRemaining(nodeField -> criterias.put(nodeField.getKey(), nodeField.getValue().asText()));
			}
		}
		
		return customerRepository.findAll(CustomerSpecification.withCriteria(criterias),pagenate);
	}
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
