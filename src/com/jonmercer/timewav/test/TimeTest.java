package com.jonmercer.timewav.test;

import com.jonmercer.timewav.Time;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class TimeTest {

    @Test
    public void testOrganizeFileNamesByYearAndWeek() throws Exception {

    }

    @Test
    public void testGetYearNum() throws Exception {
        String dateString = "2010-01-01 01_01";
        int expected = 2010;

        Time classToTest = new Time();

        int yearNum = classToTest.getYearNum(dateString);

        assertEquals(expected, yearNum);
    }

//    @Test(expected=java.text.ParseException.class)
//    public void testGetBadYearNumShouldFail() throws Exception {
//
//        Time classToTest = new Time();
//
//        classToTest.getYearNum("poodle");
//    }

    @Test
    public void testGetWeekNum() throws Exception {
        Time classToTest = new Time();
        int expected = 1;

        String dateAndTimeString = "2010-01-01 01_01";
        int weekNum = classToTest.getWeekNum(dateAndTimeString);

        assertEquals(expected, weekNum);

    }
}