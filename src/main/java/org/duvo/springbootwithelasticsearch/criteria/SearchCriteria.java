package org.duvo.springbootwithelasticsearch.criteria;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.duvo.springbootwithelasticsearch.converter.Converter;
import org.duvo.springbootwithelasticsearch.util.Constants.OPERATOR;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SearchCriteria {

	String field() default "";
    OPERATOR operator() default OPERATOR.EQUAL;
    Class<? extends Converter<?,?>>[] converter() default {};
    int order() default 0;
}
