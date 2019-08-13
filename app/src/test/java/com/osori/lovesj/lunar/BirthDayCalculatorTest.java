package com.osori.lovesj.lunar;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * BirthDayCalculatorTest
 *
 * @author 스포츠_개발 (dl_sports_sweng@navercorp.com)
 */
@RunWith(MockitoJUnitRunner.class)
public class BirthDayCalculatorTest {
	@Test
	public void test_장인어른() throws Exception {
		//given
		LocalDate gregorian = LocalDate.of(2019, 7, 14);

		//then
		LocalDate gregorianDateByLunar = BirthDayCalculator.makeLunarDateByGregorian(gregorian);

		//assert & verify
		Assert.assertEquals("gregorianDateByLunar", LocalDate.of(2019, 8, 14), gregorianDateByLunar);
	}

	@Test
	public void test_장모님() throws Exception {
		//given
		LocalDate gregorian = LocalDate.of(2019, 7, 5);

		//then
		LocalDate gregorianDateByLunar = BirthDayCalculator.makeLunarDateByGregorian(gregorian);

		//assert & verify
		Assert.assertEquals("gregorianDateByLunar", LocalDate.of(2019, 8, 5), gregorianDateByLunar);
	}

	@Test
	public void test_임의의_날짜() throws Exception {
		//given
		LocalDate gregorian = LocalDate.of(2020, 7, 14);

		//then
		LocalDate gregorianDateByLunar = BirthDayCalculator.makeLunarDateByGregorian(gregorian);

		//assert & verify
		Assert.assertEquals("gregorianDateByLunar", LocalDate.of(2020, 9, 1), gregorianDateByLunar);
	}

	@Test
	public void test_임의의_날짜2() throws Exception {
		//given
		LocalDate gregorian = LocalDate.of(2018, 10, 4);

		//then
		LocalDate gregorianDateByLunar = BirthDayCalculator.makeLunarDateByGregorian(gregorian);

		//assert & verify
		Assert.assertEquals("gregorianDateByLunar", LocalDate.of(2018, 11, 11), gregorianDateByLunar);
	}
}