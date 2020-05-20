package org.duvo.springbootwithelasticsearch.controller;

import org.duvo.springbootwithelasticsearch.dto.TransactionRequestDto;
import org.duvo.springbootwithelasticsearch.dto.TransactionSearchRequestDto;
import org.duvo.springbootwithelasticsearch.dto.TransactionsResponseDto;
import org.duvo.springbootwithelasticsearch.service.ElasticsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	private ElasticsearchService service;

	@PostMapping
	public ResponseEntity<Void> addTransaction(@RequestBody TransactionRequestDto body) {
		service.index(body);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/search")
	public ResponseEntity<TransactionsResponseDto> search(@RequestBody TransactionSearchRequestDto body) {
		TransactionsResponseDto response = service.search(body);
		return ResponseEntity.ok(response);
	}
}
