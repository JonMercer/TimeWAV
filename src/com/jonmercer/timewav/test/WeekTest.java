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
        String fileName1 = "2020-02-02 02_02";
        String fileName2 = "2030-03-03 03_03";
        String fileName3 = "2010-01-01 01_01";

        Week classToTest = new Week(1);

        classToTest.addFileName(fileName1);
        classToTest.addFileName(fileName2);
        classToTest.addFileName(fileName3);

        ArrayList<String> sortedFileNames = classToTest.getSortedFileNames();

        assertEquals("Array is not the right size", 3, sortedFileNames.size());
        assertEquals("File name is not sorted", fileName3, sortedFileNames.get(0));
        assertEquals("File name is not sorted", fileName1, sortedFileNames.get(1));
        assertEquals("File name is not sorted", fileName2, sortedFileNames.get(2));
    }



}