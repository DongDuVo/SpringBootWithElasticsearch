package org.duvo.springbootwithelasticsearch.service;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.duvo.springbootwithelasticsearch.criteria.ElasticsearchQueryParser;
import org.duvo.springbootwithelasticsearch.dto.TransactionRequestDto;
import org.duvo.springbootwithelasticsearch.dto.TransactionSearchRequestDto;
import org.duvo.springbootwithelasticsearch.dto.TransactionsResponseDto;
import org.duvo.springbootwithelasticsearch.entity.Transaction;
import org.duvo.springbootwithelasticsearch.util.Constants;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
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
				.username(dto.getUserName()).content(dto.getContent()).id(dto.getId()).type(dto.getType())
				.group(dto.getGroup()).build();
		
		try {
			String data = objectMapper.writeValueAsString(t);
			IndexRequest indexRequest = new IndexRequest(index, "_doc").source(data, XContentType.JSON);
			client.index(indexRequest, RequestOptions.DEFAULT);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public TransactionsResponseDto search(TransactionSearchRequestDto criteria) {
        int page = Optional.ofNullable(criteria.getPage()).orElse(Constants.DEFAULT_PAGE_INDEX);
        int size = Optional.ofNullable(criteria.getLimit()).orElse(Constants.DEFAULT_PAGE_SIZE);
        String sortField = getSortField(criteria.getSortField());
        SortOrder order = Optional.ofNullable(criteria.getOrder()).orElse(SortOrder.ASC);
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.size(size);
        sourceBuilder.from(page * size);
        sourceBuilder.sort(sortField, order);
        try {
            QueryBuilder queryBuilder = ElasticsearchQueryParser.getInstance().parse(criteria);
            sourceBuilder.query(queryBuilder);
            searchRequest.source(sourceBuilder);
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            List<Transaction> items = new LinkedList<>();
            for (SearchHit hit : response.getHits().getHits()) {
                items.add(objectMapper.readValue(hit.getSourceAsString(), Transaction.class));
            }
            return new TransactionsResponseDto(items);
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
    }

    private String getSortField(String sortField) {
        String fieldName = Optional.ofNullable(sortField).orElse(Constants.DEFAULT_SORT_FIELD);
        try {
            Field field = Transaction.class.getDeclaredField(fieldName);
            if (Number.class.isAssignableFrom(field.getType())) {
                return fieldName;
            }
            return fieldName + ".keyword";
        } catch (NoSuchFieldException | SecurityException e) {
            return Constants.DEFAULT_SORT_FIELD;
        }
    }
}
