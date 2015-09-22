package com.jonmercer.timewav.test;

import com.jonmercer.timewav.Week;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WeekTest {


    @Test
    public void testGetWeekNumber() throws Exception {
        int expected = 1;
        Week classToTest = new Week(expected);

        int actual = classToTest.getWeekNumber();

        assertEquals("Week numbers don't match", expected, actual);
    }

    @Test
    public void testAddOneGetOneFileName() throws Exception {
        String fileName = "File";

        Week classToTest = new Week(1);

        classToTest.addFileName(fileName);
        ArrayList<String> sortedFileNames = classToTest.getSortedFileNames();

        assertEquals("Week numbers don't match", 1, sortedFileNames.size());
        assertEquals("File does not exist in arrayList", fileName, sortedFileNames.get(0));
    }

    @Test
    public void testAddThreeGetThreeSorted() throws Exception {
        String fileName1 = "B";
        String fileName2 = "C";
        String fileName3 = "A";

        Week classTotest = new Week(1);

        classTotest.addFileName(fileName1);
        classTotest.addFileName(fileName2);
        classTotest.addFileName(fileName3);

        ArrayList<String> sortedFileNames = classTotest.getSortedFileNames();

        assertEquals("Array is not the right size", 3, sortedFileNames.size());
        assertEquals("File name is not sorted", fileName3, sortedFileNames.get(0));
        assertEquals("File name is not sorted", fileName1, sortedFileNames.get(1));
        assertEquals("File name is not sorted", fileName2, sortedFileNames.get(2));
    }



}