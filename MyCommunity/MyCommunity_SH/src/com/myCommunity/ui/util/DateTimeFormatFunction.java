package com.myCommunity.ui.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormatFunction {
	private final static SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static String dateTimeFormat(Date date){
		return formater.format(date);
	}
}
