package com.training.utils;

import java.time.ZonedDateTime;

public class DateTimeUtil {

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATE_TIME_WITH_ZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS O";
	public static final String DATE_TIME_WITHOUT_ZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";

	public static ZonedDateTime getCurrent() {
		return ZonedDateTime.now();
	}

	public static int getMonth(ZonedDateTime dateTime) {
		return dateTime.getMonthValue();
	}

	public static int getQuarter(ZonedDateTime dateTime) {
		return (dateTime.getMonthValue() / 3) + 1;
	}

	public static int getYear(ZonedDateTime dateTime) {
		return dateTime.getYear();
	}
}
