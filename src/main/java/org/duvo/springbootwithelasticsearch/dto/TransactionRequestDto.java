package org.duvo.springbootwithelasticsearch.dto;

import java.math.BigDecimal;

import org.duvo.springbootwithelasticsearch.constant.TransactionGroup;
import org.duvo.springbootwithelasticsearch.constant.TransactionType;

public class TransactionRequestDto {

	private long id;
	private TransactionGroup group;
	private TransactionType type;
	private String userName;
	private String content;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
