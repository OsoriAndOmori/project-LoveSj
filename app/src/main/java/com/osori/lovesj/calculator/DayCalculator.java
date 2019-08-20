package com.osori.lovesj.calculator;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * DayCalculator
 */
public class DayCalculator {
	/**
	 * @param target 오늘 기준으로 일반적인 Dday를 체크하는 메서드
	 *               standard 2019년 8월 13일이고, target 이 2019년 8월 15일 이면  return -2;
	 *               standard 2019년 8월 13일이고, target 이 2019년 8월 12일 이면  return +1;
	 */
	public static long countDdayFrom(LocalDate standard, LocalDate target) {
		return DAYS.between(target, standard);
	}

	/**
	 * @param target 오늘 기준으로 몇일이 지났는지 체크하는 메서드, 연애한지, 결혼한지 몇 일~ 같은것에 사용. 음수는 없음.
	 *               연애 시작 start 이 2019년 8월 12일 이고, target 2019년 8월 13일이면  return 2;
	 */
	public static long countDaysIncludeStandardFrom(LocalDate start, LocalDate target) {
		long countDays = DAYS.between(start, target) + 1;
		if (countDays < 1) {
			throw new IllegalStateException("날짜 카운팅하는 것에 0 이하는 있을 수 없다. 연애는 1일부터 셈");
		}
		return countDays;
	}
}