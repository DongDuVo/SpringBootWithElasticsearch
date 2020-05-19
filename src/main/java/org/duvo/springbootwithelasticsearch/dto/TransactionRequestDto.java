package org.duvo.springbootwithelasticsearch.dto;

import java.math.BigDecimal;

import org.duvo.springbootwithelasticsearch.constant.TransactionGroup;
import org.duvo.springbootwithelasticsearch.constant.TransactionType;

public class TransactionRequestDto {

	private long id;
	private TransactionGroup group;
	private TransactionType type;
	private String firstname;
	private String lastname;
	private BigDecimal amount;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public TransactionGroup getGroup() {
		return group;
	}
	public void setGroup(TransactionGroup group) {
		this.group = group;
	}
	public TransactionType getType() {
		return type;
	}
	public void setType(TransactionType type) {
		this.type = type;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
