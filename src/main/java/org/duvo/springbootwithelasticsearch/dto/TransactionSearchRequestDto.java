package org.duvo.springbootwithelasticsearch.dto;

import org.duvo.springbootwithelasticsearch.constant.TransactionGroup;
import org.duvo.springbootwithelasticsearch.constant.TransactionType;
import org.duvo.springbootwithelasticsearch.converter.StringConverter;
import org.duvo.springbootwithelasticsearch.criteria.SearchCriteria;
import org.duvo.springbootwithelasticsearch.util.Constants.OPERATOR;
import org.elasticsearch.search.sort.SortOrder;

public class TransactionSearchRequestDto {

	@SearchCriteria(field = "time", operator = OPERATOR.GREATER_THAN_EQUAL, order = 2)
    private Long from;

    @SearchCriteria(field = "time", operator = OPERATOR.LESS_THAN_EQUAL, order = 2)
    private Long to;

    @SearchCriteria(order = 1)
    private TransactionGroup group;

    @SearchCriteria
    private TransactionType type;

    @SearchCriteria(operator = OPERATOR.LIKE, order = 999, converter = StringConverter.class)
    private String userName;

    @SearchCriteria(operator = OPERATOR.LIKE, order = 9999, converter = StringConverter.class)
    private String content;

    private Integer page;
    private Integer limit;
    private String sortField;
    private SortOrder order;
    
	public Long getFrom() {
		return from;
	}
	public void setFrom(Long from) {
		this.from = from;
	}
	public Long getTo() {
		return to;
	}
	public void setTo(Long to) {
		this.to = to;
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
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public SortOrder getOrder() {
		return order;
	}
	public void setOrder(SortOrder order) {
		this.order = order;
	}
}
