package com.training.utils;

import java.util.Date;

import org.joda.time.DateTime;

public class DateTimeUtil {

	public static DateTime getCurrent() {
		return DateTime.now();
	}

	public static int getMonth(DateTime dateTime) {
		return dateTime.getMonthOfYear();
	}

	public static int getQuarter(DateTime dateTime) {
		return (dateTime.getMonthOfYear() / 3) + 1;
	}

	public static int getYear(DateTime dateTime) {
		return dateTime.getYear();
	}
}
