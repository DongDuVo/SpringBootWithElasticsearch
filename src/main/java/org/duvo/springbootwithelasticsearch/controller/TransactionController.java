package org.duvo.springbootwithelasticsearch.controller;

import org.duvo.springbootwithelasticsearch.dto.TransactionRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@PostMapping
	public ResponseEntity<Void> addTransaction(@RequestBody TransactionRequestDto body) {
		return ResponseEntity.ok().build();
	}
}
