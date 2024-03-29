package com.osori.lovesj.utils;

import com.osori.lovesj.utils.DayCounter;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * DayCounterDdayTest
 */
@RunWith(MockitoJUnitRunner.class)
public class DayCounterIncludeStandardTest {
	@Test
	public void countDaysIncludeStandardFrom_한자리수_day_체크() throws Exception {
		//given
		LocalDate standard = LocalDate.of(2019, 8, 16);
		LocalDate target = LocalDate.of(2019, 8, 26);

		//then
		long dday = DayCounter.countDaysIncludeStandardFrom(standard, target);
		long answer = 11;

		//assert & verify
		assertEquals("dday", answer, dday);
	}

	@Test
	public void countDaysIncludeStandardFrom_1년_day_체크() throws Exception {
		//given
		LocalDate standard = LocalDate.of(2019, 8, 16);
		LocalDate target = LocalDate.of(2020, 8, 16);

		//then
		long dday = DayCounter.countDaysIncludeStandardFrom(standard, target);
		//2020년이 윤년임
		long answer = 367;

		//assert & verify
		assertEquals("dday", answer, dday);
	}

	@Test(expected = IllegalStateException.class)
	public void countDaysIncludeStandardFrom_Exception_체크() throws Exception {
		//given
		LocalDate standard = LocalDate.of(2019, 8, 16);
		LocalDate target = LocalDate.of(2019, 8, 15);

		//then
		long dday = DayCounter.countDaysIncludeStandardFrom(standard, target);
	}
}