package org.duvo.springbootwithelasticsearch.criteria;

import org.duvo.springbootwithelasticsearch.util.Constants.OPERATOR;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

public class ElasticsearchQueryBuilder implements Comparable<ElasticsearchQueryBuilder> {
	
	private String field;
    private OPERATOR opt;
    private Object value;
    private int order;

    public ElasticsearchQueryBuilder(String field, OPERATOR opt, Object value, int order) {
		this.field = field;
		this.opt = opt;
		this.value = value;
		this.order = order;
	}

	public QueryBuilder build() {
        switch (opt) {
        case GREATER_THAN:
            return QueryBuilders.rangeQuery(field).gt(value);

        case GREATER_THAN_EQUAL:
            return QueryBuilders.rangeQuery(field).gte(value);

        case LESS_THAN:
            return QueryBuilders.rangeQuery(field).lt(value);

        case LESS_THAN_EQUAL:
            return QueryBuilders.rangeQuery(field).lte(value);

        case NOT_EQUAL:
            return QueryBuilders.boolQuery().mustNot(QueryBuilders.matchQuery(field, value).operator(Operator.AND));

        case LIKE:
            return QueryBuilders.regexpQuery(field, String.format(".*%s.*", value.toString().toLowerCase()));

        case NOT_LIKE:
            return QueryBuilders.boolQuery().mustNot(QueryBuilders.wildcardQuery(field, String.format("*%s*", value.toString().toLowerCase())));

        default:
            return QueryBuilders.matchQuery(field, value).operator(Operator.AND);
        }
    }

    @Override
    public int compareTo(ElasticsearchQueryBuilder o) {
        if (order == o.order) return 0;
        if (order < o.order) return -1;
        return 1;
    }

}
