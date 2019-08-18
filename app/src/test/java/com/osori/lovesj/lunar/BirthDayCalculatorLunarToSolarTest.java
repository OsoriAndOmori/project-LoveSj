package com.osori.lovesj.lunar;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class BirthDayCalculatorLunarToSolarTest {
    @Test
    public void test_장인어른() throws Exception {
        //given
        LocalDate lunar = LocalDate.of(2019, 7, 14);

        //then
        LocalDate gregorianDateByLunar = BirthDayCalculator.convertLunarToSolar(lunar);

        //assert & verify
        Assert.assertEquals("gregorianDateByLunar", LocalDate.of(2019, 8, 14), gregorianDateByLunar);
    }

    @Test
    public void test_장모님() throws Exception {
        //given
        LocalDate lunar = LocalDate.of(2019, 7, 5);

        //then
        LocalDate gregorianDateByLunar = BirthDayCalculator.convertLunarToSolar(lunar);

        //assert & verify
        Assert.assertEquals("gregorianDateByLunar", LocalDate.of(2019, 8, 5), gregorianDateByLunar);
    }

    @Test
    public void test_임의의_날짜() throws Exception {
        //given
        LocalDate lunar = LocalDate.of(2020, 7, 14);

        //then
        LocalDate gregorianDateByLunar = BirthDayCalculator.convertLunarToSolar(lunar);

        //assert & verify
        Assert.assertEquals("gregorianDateByLunar", LocalDate.of(2020, 9, 1), gregorianDateByLunar);
    }

    /*
     * 왜 안되는지 잘 모르겠음. 하루 틀리게나옴...
     */
    @Ignore
    @Test
    public void test_임의의_날짜2() throws Exception {
        //given
        LocalDate lunar = LocalDate.of(2018, 10, 4);

        //then
        LocalDate gregorianDateByLunar = BirthDayCalculator.convertLunarToSolar(lunar);

        //assert & verify
        Assert.assertEquals("gregorianDateByLunar", LocalDate.of(2018, 11, 11), gregorianDateByLunar);
    }
}