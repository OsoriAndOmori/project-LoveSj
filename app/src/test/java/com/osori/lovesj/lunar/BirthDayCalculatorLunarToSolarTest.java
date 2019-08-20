package com.osori.lovesj.lunar;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BirthDayCalculatorLunarToSolarTest {
	@Test
	public void test_장인어른_2019년() throws Exception {
		//given
		LocalDate lunar = LocalDate.of(2019, 7, 14);

		//then
		LocalDate gregorianDateByLunar = BirthDayCalculator.convertLunarToSolar(lunar);

		//assert & verify
		Assert.assertEquals("gregorianDateByLunar", LocalDate.of(2019, 8, 14), gregorianDateByLunar);
	}

	@Test
	public void test_장인어른_2020년() throws Exception {
		//given
		LocalDate lunar = LocalDate.of(2020, 7, 14);

		//then
		LocalDate gregorianDateByLunar = BirthDayCalculator.convertLunarToSolar(lunar);

		//assert & verify
		Assert.assertEquals("gregorianDateByLunar", LocalDate.of(2020, 9, 1), gregorianDateByLunar);
	}

	@Test
	public void test_장인어른_2021년() throws Exception {
		//given
		LocalDate lunar = LocalDate.of(2021, 7, 14);

		//then
		LocalDate gregorianDateByLunar = BirthDayCalculator.convertLunarToSolar(lunar);

		//assert & verify
		Assert.assertEquals("gregorianDateByLunar", LocalDate.of(2021, 8, 21), gregorianDateByLunar);
	}

	@Test
	public void test_장인어른_2022년() throws Exception {
		//given
		LocalDate lunar = LocalDate.of(2022, 7, 14);

		//then
		LocalDate gregorianDateByLunar = BirthDayCalculator.convertLunarToSolar(lunar);

		//assert & verify
		Assert.assertEquals("gregorianDateByLunar", LocalDate.of(2022, 8, 11), gregorianDateByLunar);
	}

	@Test
	public void test_장모님_2019년() throws Exception {
		//given
		LocalDate lunar = LocalDate.of(2019, 7, 5);

		//then
		LocalDate gregorianDateByLunar = BirthDayCalculator.convertLunarToSolar(lunar);

		//assert & verify
		Assert.assertEquals("gregorianDateByLunar", LocalDate.of(2019, 8, 5), gregorianDateByLunar);
	}

	@Test
	public void test_장모님_2020년() throws Exception {
		//given
		LocalDate lunar = LocalDate.of(2020, 7, 5);

		//then
		LocalDate gregorianDateByLunar = BirthDayCalculator.convertLunarToSolar(lunar);

		//assert & verify
		Assert.assertEquals("gregorianDateByLunar", LocalDate.of(2020, 8, 23), gregorianDateByLunar);
	}

	@Test
	public void test_장모님_2021년() throws Exception {
		//given
		LocalDate lunar = LocalDate.of(2021, 7, 5);

		//then
		LocalDate gregorianDateByLunar = BirthDayCalculator.convertLunarToSolar(lunar);

		//assert & verify
		Assert.assertEquals("gregorianDateByLunar", LocalDate.of(2021, 8, 12), gregorianDateByLunar);
	}

	@Test
	public void test_장모님_2022년() throws Exception {
		//given
		LocalDate lunar = LocalDate.of(2022, 7, 5);

		//then
		LocalDate gregorianDateByLunar = BirthDayCalculator.convertLunarToSolar(lunar);

		//assert & verify
		Assert.assertEquals("gregorianDateByLunar", LocalDate.of(2022, 8, 2), gregorianDateByLunar);
	}

	@Test
	public void test_임의의_날짜() throws Exception {
		//given
		LocalDate lunar = LocalDate.of(2025, 6, 23);

		//then
		LocalDate gregorianDateByLunar = BirthDayCalculator.convertLunarToSolar(lunar);

		//assert & verify
		Assert.assertEquals("gregorianDateByLunar", LocalDate.of(2025, 7, 17), gregorianDateByLunar);
	}

	@Test
	public void test_임의의_날짜2() throws Exception {
		//given
		LocalDate lunar = LocalDate.of(2018, 11, 11);

		//then
		LocalDate gregorianDateByLunar = BirthDayCalculator.convertLunarToSolar(lunar);

		//assert & verify
		Assert.assertEquals("gregorianDateByLunar", LocalDate.of(2018, 12, 17), gregorianDateByLunar);
	}
}