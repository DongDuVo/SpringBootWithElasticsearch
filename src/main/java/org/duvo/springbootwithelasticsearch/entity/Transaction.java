package org.duvo.springbootwithelasticsearch.entity;

import java.math.BigDecimal;

import org.duvo.springbootwithelasticsearch.constant.TransactionGroup;
import org.duvo.springbootwithelasticsearch.constant.TransactionType;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Transaction.TransactionBuilder.class)
public class Transaction {

	private long time;
	private long id;
	private TransactionGroup group;
	private TransactionType type;
	private String userName;
	private String content;
	private BigDecimal amount;
	
	public long getTime() {
		return time;
	}
	
	public long getId() {
		return id;
	}
	
	public TransactionGroup getGroup() {
		return group;
	}
	
	public TransactionType getType() {
		return type;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getContent() {
		return content;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public static TransactionBuilder builder() {
		return new TransactionBuilder();
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class TransactionBuilder {
		private Transaction transaction = new Transaction();
		
		public TransactionBuilder time(long time) {
			transaction.time = time;
			return this;
		}
		
		public TransactionBuilder id(long id) {
			transaction.id = id;
			return this;
		}
		
		public TransactionBuilder type(TransactionType type) {
			transaction.type = type;
			return this;
		}
		
		public TransactionBuilder group(TransactionGroup group) {
			transaction.group = group;
			return this;
		}
		
		public TransactionBuilder username(String username) {
			transaction.userName = username;
			return this;
		}
		
		public TransactionBuilder content(String content) {
			transaction.content = content;
			return this;
		}
		
		public TransactionBuilder amount(BigDecimal amount) {
			transaction.amount = amount;
			return this;
		}
		
		public Transaction build() {
			return transaction;
		}
	}
}
