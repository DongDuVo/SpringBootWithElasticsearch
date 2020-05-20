package org.duvo.springbootwithelasticsearch.util;

public final class Constants {
	public enum OPERATOR {
        EQUAL, GREATER_THAN, GREATER_THAN_EQUAL, LESS_THAN, LESS_THAN_EQUAL, NOT_EQUAL, IN, LIKE, NOT_IN, NOT_LIKE
    }
	
	public static int DEFAULT_PAGE_INDEX = 0;
	public static int DEFAULT_PAGE_SIZE = 10;
	public static String DEFAULT_SORT_FIELD = "id";
}
