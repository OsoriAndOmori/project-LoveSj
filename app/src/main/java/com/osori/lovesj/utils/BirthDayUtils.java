package com.osori.lovesj.utils;

import java.time.LocalDate;

import com.osori.lovesj.anniversary.BirthDay;

public class BirthDayUtils {
	private static final String DOT_SPACE = ". ";
	private static final String SPACE = " ";

	public static LocalDate findUpComingBirthDay(LocalDate birth, boolean isLunarBirthDay) {
		LocalDate now = LocalDate.now();
		LocalDate birthDayOfThisYear = LocalDate.of(now.getYear(), birth.getMonthValue(), birth.getDayOfMonth());
		LocalDate birthDayOfThisYearConvertedIntoSolar = isLunarBirthDay
				? LunarSolarConverter.convertLunarToSolar(birthDayOfThisYear)
				: birthDayOfThisYear;

		// 올해 날짜 안지난 경우
		if (now.isBefore(birthDayOfThisYearConvertedIntoSolar) || now.isEqual(birthDayOfThisYearConvertedIntoSolar)) {
			return birthDayOfThisYearConvertedIntoSolar;
		}

		// 올해 날짜 지난 경우 => 내년 생일
		if (isLunarBirthDay) {
			return LunarSolarConverter
					.convertLunarToSolar(LocalDate.of(now.getYear() + 1, birth.getMonthValue(),
							birth.getDayOfMonth()));
		}
		return LocalDate.of(now.getYear() + 1, birth.getMonthValue(), birth.getDayOfMonth());
	}

	public static String buildBirthDayText(BirthDay birthDay) {
		LocalDate upComingBirthDay = findUpComingBirthDay(birthDay.getBirth(), birthDay.isLunarBirthDay());

		StringBuilder builder = new StringBuilder().append(birthDay.name()).append(SPACE).append(SPACE)
				.append(localDateToReadableText(upComingBirthDay));

		if (birthDay.isLunarBirthDay()) {
			builder.append(" (음 ").append(birthDay.getBirth().getMonthValue()).append(DOT_SPACE)
					.append(birthDay.getBirth().getDayOfMonth()).append(")");
		}

		return builder
				.append(".").append("    ").append("D - ")
				.append(DayCounter.countDdayFrom(upComingBirthDay, LocalDate.now()))
				.toString();
	}

	public static String localDateToReadableText(LocalDate date) {
		return new StringBuilder()
				.append(date.getYear()).append(DOT_SPACE)
				.append(date.getMonthValue()).append(DOT_SPACE)
				.append(date.getDayOfMonth()).toString();
	}
}
