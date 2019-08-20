package com.osori.lovesj.calculator;

import com.osori.lovesj.calculator.BirthDayCalculator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class BirthDayCalculatorSolarToLunarTest {

    @Test
    public void test_장인어른() throws Exception {
        //given
        LocalDate lunar = LocalDate.of(2019, 8, 14);

        //then
        LocalDate gregorianDateByLunar = BirthDayCalculator.converSolarToLunar(lunar);

        //assert & verify
        Assert.assertEquals("gregorianDateBySolar", LocalDate.of(2019, 7, 14), gregorianDateByLunar);
    }

    @Test
    public void test_장모님() throws Exception {
        //given
        LocalDate lunar = LocalDate.of(2019, 8, 5);

        //then
        LocalDate gregorianDateByLunar = BirthDayCalculator.converSolarToLunar(lunar);

        //assert & verify
        Assert.assertEquals("gregorianDateBySolar", LocalDate.of(2019, 7, 5), gregorianDateByLunar);
    }

    @Test
    public void test_미래_내_생일() throws Exception {
        //given
        LocalDate lunar = LocalDate.of(2020, 6, 23);

        //then
        LocalDate gregorianDateByLunar = BirthDayCalculator.converSolarToLunar(lunar);

        //assert & verify
        Assert.assertEquals("gregorianDateBySolar", LocalDate.of(2020, 5, 3), gregorianDateByLunar);
    }

    @Test
    public void test_미래_수진_생일() throws Exception {
        //given
        LocalDate lunar = LocalDate.of(2023, 9, 23);

        //then
        LocalDate gregorianDateByLunar = BirthDayCalculator.converSolarToLunar(lunar);

        //assert & verify
        Assert.assertEquals("gregorianDateBySolar", LocalDate.of(2023, 8, 9), gregorianDateByLunar);
    }
}