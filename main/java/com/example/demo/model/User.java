package com.example.demo.model;

import java.security.Timestamp;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User extends MyModel {
	@Id
	@GeneratedValue
	private Integer id;
	
	private String userId;
	
	private String name;
	
	private String password;
	
	private String address;
	
	private String tel;
	
	private LocalDate birthday;
	
	private Integer gender;
	
	private Timestamp createdDate;
	
	private Timestamp lastUpdate;
	
}
