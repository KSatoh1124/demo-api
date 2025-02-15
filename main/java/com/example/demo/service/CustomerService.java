package com.example.demo.service;

import java.util.HashMap;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
	
	public Page<Customer> index(String page, String searchParam, String sort) {
		var currentPage = page == null || page.isEmpty()? 0 : Integer.parseInt(page);
		var displayPageCount = 5;
		
		var paginate =  PageRequest.of(currentPage, displayPageCount, Sort.by("id").ascending());
		if (sort != null) {
			if (!sort.isEmpty()) {
				var sortJsonNode = Common.urlEncodedToJsonNode(sort);
				var fieldName = sortJsonNode.get("fieldName");
				var sortby = sortJsonNode.get("by");
				if (sortby.asText().equals("asc")) {
					paginate = PageRequest.of(currentPage, displayPageCount, Sort.by(fieldName.asText()).ascending());
				} else {
					paginate = PageRequest.of(currentPage, displayPageCount, Sort.by(fieldName.asText()).descending());
				}
			}
		}
		
		var criterias = new HashMap<String, String>();
		if (searchParam != null) {
			var jsonNode = Common.urlEncodedToJsonNode(searchParam);
			if (jsonNode != null) {
				jsonNode.fields().forEachRemaining(nodeField -> criterias.put(nodeField.getKey(), nodeField.getValue().asText()));
			}
		}
		
		return customerRepository.findAll(CustomerSpecification.withCriteria(criterias),paginate);
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
