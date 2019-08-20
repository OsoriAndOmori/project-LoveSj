package com.osori.lovesj.utils;

import java.time.LocalDate;

public class BirthDayUtils {
    public static LocalDate findUpComingBirthDay(LocalDate birth, boolean isLunarBirthDay) {
        LocalDate now = LocalDate.now();
        LocalDate birthDayOfThisYear = LocalDate.of(now.getYear(), birth.getMonthValue(), birth.getDayOfMonth());
        LocalDate birthDayOfThisYearConvertedIntoSolar = isLunarBirthDay
                ? LunarSolarConverter.convertLunarToSolar(birthDayOfThisYear)
                : birthDayOfThisYear;

        // 올해 날짜 안지난 경우
        if (now.isBefore(birthDayOfThisYearConvertedIntoSolar)) {
            return birthDayOfThisYearConvertedIntoSolar;
        }

        // 올해 날짜 지난 경우 => 내년 생일
        if (isLunarBirthDay) {
            return LunarSolarConverter.convertLunarToSolar(LocalDate.of(now.getYear() + 1, birth.getMonthValue(), birth.getDayOfMonth()));
        }
        return LocalDate.of(now.getYear() + 1, birth.getMonthValue(), birth.getDayOfMonth());
    }
}
