package com.mnasro.demo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="demo")
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name= "from_currency",nullable = false)
	private String fromCurrency;
	
	@Column(name= "to_currency",nullable = false)
	private String toCurrency;
	
	@Column(name= "timestamp",nullable = false)
	private Date timestamp;
	
	@Column(name= "amount",nullable = false)
	private Double amount;

}
