package com.osori.lovesj.lunar;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BirthDayCalculatorSolarToLunarTest {

    /*
     * 고쳐주세요
     */
    @Ignore
    @Test
    public void test_장인어른() throws Exception {
        //given
        LocalDate lunar = LocalDate.of(2019, 8, 14);

        //then
        LocalDate gregorianDateByLunar = BirthDayCalculator.converSolarToLunar(lunar);

        //assert & verify
        Assert.assertEquals("gregorianDateBySolar", LocalDate.of(2019, 7, 14), gregorianDateByLunar);
    }

    /*
     * 고쳐주세요
     */
    @Ignore
    @Test
    public void test_장모님() throws Exception {
        //given
        LocalDate lunar = LocalDate.of(2019, 8, 5);

        //then
        LocalDate gregorianDateByLunar = BirthDayCalculator.converSolarToLunar(lunar);

        //assert & verify
        Assert.assertEquals("gregorianDateBySolar", LocalDate.of(2019, 7, 5), gregorianDateByLunar);
    }
}