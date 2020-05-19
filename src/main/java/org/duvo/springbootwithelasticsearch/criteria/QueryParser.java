package org.duvo.springbootwithelasticsearch.criteria;

import java.lang.reflect.Field;

import org.duvo.springbootwithelasticsearch.converter.Converter;
import org.springframework.util.StringUtils;

public class QueryParser<T> {
	protected Object getValue(T criteria, Field field, SearchCriteria searchCriteria) throws Exception {
        String getter = "get" + StringUtils.capitalize(field.getName());
        Object value = criteria.getClass().getMethod(getter).invoke(criteria);
        Class<? extends Converter<?, ?>> converterClass = searchCriteria.converter().length == 0 ? null : searchCriteria.converter()[0];
        if (converterClass != null) {
            @SuppressWarnings("unchecked")
            Converter<Object, Object> converter = (Converter<Object, Object>) converterClass.getDeclaredConstructor().newInstance();
            value = converter.convert(value);
        }
        return value;
    }
}
