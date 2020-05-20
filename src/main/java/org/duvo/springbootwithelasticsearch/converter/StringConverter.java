package org.duvo.springbootwithelasticsearch.converter;

import org.springframework.util.StringUtils;

public class StringConverter implements Converter<String, String> {

	@Override
	public String convert(String source) {
		return StringUtils.isEmpty(source) ? null : source;
	}

}
