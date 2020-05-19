package org.duvo.springbootwithelasticsearch.entity;

import java.math.BigDecimal;

import org.duvo.springbootwithelasticsearch.constant.TransactionGroup;
import org.duvo.springbootwithelasticsearch.constant.TransactionType;

public class Transaction {

	private long time;
	private long id;
	private TransactionGroup group;
	private TransactionType type;
	private String firstname;
	private String lastname;
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
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public static TransactionBuilder builder() {
		return new TransactionBuilder();
	}

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
		
		public TransactionBuilder firstname(String firstname) {
			transaction.firstname = firstname;
			return this;
		}
		
		public TransactionBuilder lastname(String lastname) {
			transaction.lastname = lastname;
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
