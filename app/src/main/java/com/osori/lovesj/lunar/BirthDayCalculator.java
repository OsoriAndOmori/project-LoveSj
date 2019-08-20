package com.osori.lovesj.lunar;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ChineseCalendar;

/**
 * BirthDayCalculator
 */
public class BirthDayCalculator {
	/**
	 * 음력 2019년 7월 5일 => 양력 2019년 8월 5일 리턴하는 메서드
	 *
	 * @param date LocalDate.of(2019, 7, 5)
	 * @return LocalDate.of(2019, 8, 5)
	 */
	public static LocalDate convertLunarToSolar(LocalDate date) {
		ChineseCalendar cc = new ChineseCalendar();
		Calendar cal = Calendar.getInstance();

		cc.set(ChineseCalendar.EXTENDED_YEAR, date.getYear() + 2637);
		cc.set(ChineseCalendar.MONTH, date.getMonthValue() - 1);
		cc.set(ChineseCalendar.DAY_OF_MONTH, date.getDayOfMonth());

		cal.setTimeInMillis(cc.getTimeInMillis());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DateTimeFormatter yyyy_MM_dd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(sdf.format(cal.getTime()), yyyy_MM_dd);
	}

	/**
	 * 양력 2019년 8월 5일 => 음력 2019년 7월 5일 리턴하는 메서드
	 *
	 * @param date LocalDate.of(2019, 8, 5)
	 * @return LocalDate.of(2019, 7, 5)
	 */
	public static LocalDate converSolarToLunar(LocalDate date) {
		ChineseCalendar cc = new ChineseCalendar();
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, date.getYear());
		cal.set(Calendar.MONTH, date.getMonthValue() - 1);
		cal.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());

		cc.setTimeInMillis(cal.getTimeInMillis());

		int y = cc.get(ChineseCalendar.EXTENDED_YEAR) - 2637;
		int m = cc.get(ChineseCalendar.MONTH) + 1;
		int d = cc.get(ChineseCalendar.DAY_OF_MONTH);

		return LocalDate.of(y, m, d);
	}
}