package org.duvo.springbootwithelasticsearch.service;

import org.duvo.springbootwithelasticsearch.dto.TransactionRequestDto;
import org.duvo.springbootwithelasticsearch.entity.Transaction;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ElasticsearchService {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private RestHighLevelClient client;
	
	@Value("${index-name}")
	private String index;

	public void index(TransactionRequestDto dto) {
		Transaction t = Transaction.builder().time(System.currentTimeMillis()).amount(dto.getAmount())
				.firstname(dto.getFirstname()).lastname(dto.getLastname()).id(dto.getId()).type(dto.getType())
				.group(dto.getGroup()).build();
		
		try {
			String data = objectMapper.writeValueAsString(t);
			IndexRequest indexRequest = new IndexRequest(index, "_doc").source(data, XContentType.JSON);
			client.index(indexRequest, RequestOptions.DEFAULT);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
