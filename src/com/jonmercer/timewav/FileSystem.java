package com.jonmercer.timewav;

import java.io.File;

/**
 * Created by Jon Mercer on 15-09-12.
 */
public class FileSystem {


    private final String inputFolder;
    private final String outputFolder;

    public FileSystem(String inputFolder, String outputFolder) {
        this.inputFolder = inputFolder;
        this.outputFolder = outputFolder;
    }

    private void listFiles(){
        File folder = new File(inputFolder);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            System.out.println(file.getName());
        }
    }
}
