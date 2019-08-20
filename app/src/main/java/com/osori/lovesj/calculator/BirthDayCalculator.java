package com.osori.lovesj.calculator;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ChineseCalendar;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * BirthDayCalculator
 */
public class BirthDayCalculator {
    public static LocalDate findNextBirthDay(LocalDate birth, boolean isLunar) {
        LocalDate now = LocalDate.now();
        LocalDate birthDayOfThisYear;
        if (isLunar) {
            birthDayOfThisYear = convertLunarToSolar(LocalDate.of(now.getYear(), birth.getMonthValue(), birth.getDayOfMonth()));
        } else {
            birthDayOfThisYear = LocalDate.of(now.getYear(), birth.getMonthValue(), birth.getDayOfMonth());
        }

        LocalDate birthDayOfNextYear;
        if (isLunar) {
            birthDayOfNextYear = convertLunarToSolar(LocalDate.of(now.getYear() + 1, birth.getMonthValue(), birth.getDayOfMonth()));
        } else {
            birthDayOfNextYear = LocalDate.of(now.getYear() + 1, birth.getMonthValue(), birth.getDayOfMonth());
        }

        if (now.isBefore(birthDayOfThisYear)) {
            return birthDayOfThisYear;
        }

        return birthDayOfNextYear;
    }

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