package com.jonmercer.timewav.test;

import com.jonmercer.timewav.Week;
import com.jonmercer.timewav.Year;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class YearTest {

    @Test
    public void testOneWeek() throws Exception {

        Year classToTest = new Year(1);

        Week week = new Week(1);

        classToTest.addWeek(week);

        HashMap<Integer, Week> weeks = classToTest.getWeeks();

        assertEquals(week.getWeekNumber(), weeks.get(1).getWeekNumber());
        assertEquals(1, weeks.size());
    }


    @Test
    public void testThreeWeeks() throws Exception {
        Year classToTest = new Year(1);

        Week week1 = new Week(1);
        Week week2 = new Week(2);
        Week week3 = new Week(3);

        classToTest.addWeek(week1);
        classToTest.addWeek(week2);
        classToTest.addWeek(week3);

        HashMap<Integer, Week> weeks = classToTest.getWeeks();

        assertEquals(3, weeks.size());
        assertEquals(1, weeks.get(1).getWeekNumber());
        assertEquals(2, weeks.get(2).getWeekNumber());
        assertEquals(3, weeks.get(3).getWeekNumber());
    }
}