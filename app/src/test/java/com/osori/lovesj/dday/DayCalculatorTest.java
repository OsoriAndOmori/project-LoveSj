package com.osori.lovesj.dday;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * DayCalculatorTest
 *
 * @author 스포츠_개발 (dl_sports_sweng@navercorp.com)
 */
@RunWith(MockitoJUnitRunner.class)
public class DayCalculatorTest {
	@Test
	public void countDdayFrom_양수_dday_체크() throws Exception {
		//given
		LocalDate standard = LocalDate.of(2019, 8, 16);
		LocalDate target = LocalDate.of(2019, 8, 10);

		//then
		long dday = DayCalculator.countDdayFrom(standard, target);
		long answer = 6;
		//assert & verify
		assertEquals("dday", answer, dday);
	}

	@Test
	public void countDdayFrom_0_dday_체크() throws Exception {
		//given
		LocalDate standard = LocalDate.of(2019, 8, 16);
		LocalDate target = LocalDate.of(2019, 8, 16);

		//then
		long dday = DayCalculator.countDdayFrom(standard, target);
		long answer = 0;

		//assert & verify
		assertEquals("dday", answer, dday);
	}

	@Test
	public void countDdayFrom_음수_dday_체크() throws Exception {
		//given
		LocalDate standard = LocalDate.of(2019, 8, 16);
		LocalDate target = LocalDate.of(2019, 8, 26);

		//then
		long dday = DayCalculator.countDdayFrom(standard, target);
		long answer = -10;

		//assert & verify
		assertEquals("dday", answer, dday);
	}

	@Test
	public void countDdayFrom_음수_1년넘어가는거_dday_체크() throws Exception {
		//given
		LocalDate standard = LocalDate.of(2019, 8, 16);
		LocalDate target = LocalDate.of(2020, 9, 26);

		//then
		long dday = DayCalculator.countDdayFrom(standard, target);
		long answer = -407;

		//assert & verify
		assertEquals("dday", answer, dday);
	}
}