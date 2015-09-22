package com.jonmercer.timewav.test;

import com.jonmercer.timewav.FileSystem;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileSystemTest {

    @Test
    public void testGetAllFileNamesFromInputFolderWithTwoFiles() throws Exception {
        String[] expected = new String[]{"1.txt", "2.txt"};

        FileSystem classToTest = new FileSystem();
        String[] result = classToTest.getAllFileNamesFromInputFolder("/Users/Odin/Projects/TimeWAV/TestFolders/Strings/TwoStrings");

        assertArrayEquals("Folders contents don't match!", expected, result);
    }

    @Test
    public void testGetAllFileNamesFromInputFolderWithNoFiles() throws Exception {
        FileSystem classToTest = new FileSystem();
        String[] result = classToTest.getAllFileNamesFromInputFolder("/Users/Odin/Projects/TimeWAV/TestFolders/Strings/NoStrings");

        assertEquals("Size is not zero", 0, result.length);
    }

    @Test
    public void testGetAllFileNamesFromInputFolderWithOneFile() throws Exception {
        String[] expected = new String[]{"1.txt"};

        FileSystem classToTest = new FileSystem();
        String[] result = classToTest.getAllFileNamesFromInputFolder("/Users/Odin/Projects/TimeWAV/TestFolders/Strings/OneString");

        assertArrayEquals("Folders contents don't match!", expected, result);
    }


}