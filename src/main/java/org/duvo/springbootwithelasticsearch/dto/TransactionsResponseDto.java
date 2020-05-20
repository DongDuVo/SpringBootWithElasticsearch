package org.duvo.springbootwithelasticsearch.dto;

import java.util.List;

import org.duvo.springbootwithelasticsearch.entity.Transaction;

public class TransactionsResponseDto {

	private List<Transaction> transactions;
	
	public TransactionsResponseDto(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}
