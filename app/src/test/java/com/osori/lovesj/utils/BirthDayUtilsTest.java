package com.osori.lovesj.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class BirthDayUtilsTest {

    @Test
    public void 올해_지난_날짜_양력() {
        LocalDate nextBirthDay = BirthDayUtils.findUpComingBirthDay(LocalDate.of(1990, 6, 23), false);

        Assert.assertEquals("올해 지난 날짜, 양력", LocalDate.of(2020, 6, 23), nextBirthDay);
    }

    @Test
    public void 올해_안지난_날짜_양력() {
        LocalDate nextBirthDay = BirthDayUtils.findUpComingBirthDay(LocalDate.of(1993, 9, 23), false);

        Assert.assertEquals("올해 안지난 날짜, 양력", LocalDate.of(2019, 9, 23), nextBirthDay);
    }

    @Test
    public void 올해_지난_날짜_음력() {
        LocalDate nextBirthDay = BirthDayUtils.findUpComingBirthDay(LocalDate.of(1961, 7, 14), true);

        Assert.assertEquals("올해 지난 날짜, 음력", LocalDate.of(2020, 9, 1), nextBirthDay);
    }

    @Test
    public void 올해_안지난_날짜_음력() {
        LocalDate nextBirthDay = BirthDayUtils.findUpComingBirthDay(LocalDate.of(1975, 9, 14), true);

        Assert.assertEquals("올해 안지난 날짜, 음력", LocalDate.of(2019, 10, 12), nextBirthDay);
    }
}