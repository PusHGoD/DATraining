package com.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class DaTrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaTrainingApplication.class, args);
	}

	// @Bean
	// @Primary
	// @ConfigurationProperties(prefix = "spring.datasource")
	// public Object primaryDataSource() {
	// return DataSourceBuilder.create().build();
	// }
	//
	// @Bean
	// @ConfigurationProperties(prefix = "spring.cassandra.datasource")
	// public Object cassandraDataSource() {
	// return DataSourceBuilder.create().build();
	// }
}
