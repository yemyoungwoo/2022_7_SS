package com.KoreaIT.java.AM.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static String getDateStr() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date time = new Date();

		return format1.format(time);
		
		//자바 현재시간
		

	}

}
