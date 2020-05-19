package org.duvo.springbootwithelasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

	@Autowired
	private RestClientProperties props;

	@Bean
	public RestHighLevelClient client() {
		RestClientBuilder builder = RestClient.builder(HttpHost.create(props.getUris().get(0)))
				.setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder
						.setConnectTimeout((int) props.getConnectionTimeout().toMillis())
						.setSocketTimeout((int) props.getReadTimeout().toMillis()));
		return new RestHighLevelClient(builder);
	}
}
