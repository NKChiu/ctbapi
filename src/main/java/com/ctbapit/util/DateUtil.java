package com.ctbapit.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {
	
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	
	public static final String FORMAT_TIME_ISO = "yyyy-MM-dd'T'hh:mm:ssXXX";

	public static final String FORMAT_TIME_HRS = "yyyy/MM/dd HH:mm:ss";
	
	
	public static Date parse(String date, String format) {

		Date result = null;

		try {
			if (date != null && !date.trim().equalsIgnoreCase("")) {
				SimpleDateFormat simple = null;
				if (format != null) {
					simple = new SimpleDateFormat(format);
				} else {
					simple = new SimpleDateFormat(FORMAT_TIME_HRS);
				}
				result = simple.parse(date);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return result;
	}
	
	public static String format(Date date, String format) {

		String result = null;

		try {
			if (date != null) {
				SimpleDateFormat simple = null;
				if (format != null) {
					simple = new SimpleDateFormat(format);
				} else {
					simple = new SimpleDateFormat(FORMAT_TIME_HRS);
				}
				result = simple.format(date);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return result;
	}
}
