package org.duvo.springbootwithelasticsearch.converter;

public interface Converter<S, T> {

	T convert(S source);
}
