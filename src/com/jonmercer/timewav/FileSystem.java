package com.jonmercer.timewav;

import java.io.File;

/**
 * Accesses IO in the file system
 */
public class FileSystem {

    /**
     * Lists out all the file names from a selected folder. Not recursive
     * @param inputFolder folder where file names will be taken from
     * @return String[] of all the file names in the folder
     */
    public String[] getAllFileNamesFromInputFolder(String inputFolder) {
        File folder = new File(inputFolder);
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        String[] fileNames = new String[listOfFiles.length];

        for (int i = 0; i < listOfFiles.length; i++) {
            fileNames[i] = listOfFiles[i].getName();
        }

        return fileNames;
    }
}
