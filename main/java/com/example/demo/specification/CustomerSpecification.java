package com.example.demo.specification;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.Customer;

public class CustomerSpecification {
	//検索条件をもとに絞り込み
	public static Specification<Customer> withCriteria(HashMap<String, String> criteriaItem) {
		var id = Optional.ofNullable(criteriaItem.get("id")).orElse("");
		var name = Optional.ofNullable(criteriaItem.get("name")).orElse("");
		var name_kana = Optional.ofNullable(criteriaItem.get("name_kana")).orElse("");
		
		return Specification
				.where(withId(id))
				.and(withNameKana(name_kana))
				.and(withName(name));
	}
	
	//検索条件　ID
	private static Specification<Customer> withId(String id) {
		return (root, query, cb) -> {
			if (id == "") return null;
			return cb.equal(root.get("id"), id);
		};
	}
	
	//検索条件　かな名
	private static Specification<Customer> withNameKana(String name_kana) {
		return (root, query, cb) -> {
			if (name_kana == "") return null;
			return cb.like(root.get("name_kana"),"%" + name_kana + "%");
		};
	}

	//検索条件　名前
	private static Specification<Customer> withName(String name) {
		return (root, query, cb) -> {
			if (name == "") return null;
			return cb.like(root.get("name"),"%" + name + "%");
		};
	}
}
