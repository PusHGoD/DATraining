package com.training.utils;

import java.time.ZonedDateTime;

public class DateTimeUtil {

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
