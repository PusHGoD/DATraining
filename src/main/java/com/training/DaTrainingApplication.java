package com.training;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.cassandra.core.convert.CassandraCustomConversions;

import com.training.utils.CassandraZonedDateTimeConverter;

@SpringBootApplication
public class DaTrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaTrainingApplication.class, args);
	}

	@Bean
	CassandraCustomConversions cassandraCustomConversions() {
		List<Converter<?, ?>> converters = new ArrayList<>();
		converters.add(new CassandraZonedDateTimeConverter());
		return new CassandraCustomConversions(converters);
	}

}
