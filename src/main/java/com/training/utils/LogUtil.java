package com.training.utils;

import org.slf4j.Logger;

public class LogUtil {

	public static void info(Logger logger, String msg) {
		logger.info(msg);
	}
	
	public static void debug(Logger logger, String msg) {
		logger.debug(msg);
	}

	public static void error(Logger logger, String msg) {
		logger.error(msg);
	}
}
