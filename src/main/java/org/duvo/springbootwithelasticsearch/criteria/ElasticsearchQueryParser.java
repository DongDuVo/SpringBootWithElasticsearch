package org.duvo.springbootwithelasticsearch.criteria;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.duvo.springbootwithelasticsearch.dto.TransactionSearchRequestDto;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.util.StringUtils;

public class ElasticsearchQueryParser extends QueryParser<TransactionSearchRequestDto> {
	private static ElasticsearchQueryParser instance = new ElasticsearchQueryParser();

    private ElasticsearchQueryParser() {}

    public static ElasticsearchQueryParser getInstance() {
        return instance;
    }
    
    public QueryBuilder parse(TransactionSearchRequestDto criteria) throws Exception {
    	List<ElasticsearchQueryBuilder> conditions = new LinkedList<>();
        for (Field field : criteria.getClass().getDeclaredFields()) {
            SearchCriteria searchCriteria = field.getDeclaredAnnotation(SearchCriteria.class);
            if (searchCriteria != null) {
                Object value = getValue(criteria, field, searchCriteria);
                if (value != null) {
                    String fieldName = StringUtils.isEmpty(searchCriteria.field()) ? field.getName() : searchCriteria.field();
                    conditions.add(new ElasticsearchQueryBuilder(fieldName, searchCriteria.operator(), value, searchCriteria.order()));
                }
            }
        }
        Collections.sort(conditions);
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        for (ElasticsearchQueryBuilder queryBuilder : conditions) {
            query = query.must(queryBuilder.build());
        }
        return query;
    }
}
