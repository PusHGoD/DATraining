package com.training.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class CassandraZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {

	@Override
	public ZonedDateTime convert(Date arg0) {
		return ZonedDateTime.ofInstant(arg0.toInstant(), ZoneId.systemDefault());
	}

}
