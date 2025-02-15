package com.example.demo.model;

import java.security.Timestamp;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="m_customers")
public class Customer extends MyModel {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "name_kana")
	private String nameKana;
	
	private String name;
	
	private String postnumber;
	
	private String address;
	
	private String tel;
	
	private LocalDate birthday;
	
	private Integer gender;
	
	private String remarks;
	
	@Column(name="created_date")
	private Timestamp createdDate;
	
	@Column(name="last_update")
	private Timestamp lastUpdate;
}
