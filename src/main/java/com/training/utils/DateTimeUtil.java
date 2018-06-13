package com.training.utils;

import java.time.LocalDateTime;

public class DateTimeUtil {

	public static LocalDateTime getCurrent() {
		return LocalDateTime.now();
	}

	public static int getMonth(LocalDateTime dateTime) {
		return dateTime.getMonthValue();
	}

	public static int getQuarter(LocalDateTime dateTime) {
		return (dateTime.getMonthValue() / 3) + 1;
	}

	public static int getYear(LocalDateTime dateTime) {
		return dateTime.getYear();
	}
}
