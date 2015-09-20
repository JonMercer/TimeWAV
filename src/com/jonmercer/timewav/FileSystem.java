package com.jonmercer.timewav;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Jon Mercer on 15-09-12.
 */
public class FileSystem {


    private final String inputFolder;
    private final String outputFolder;
    private final File[] listOfFiles;
    private File folder;

    public FileSystem(String inputFolder, String outputFolder) {
        this.inputFolder = inputFolder;
        this.outputFolder = outputFolder;

        folder = new File(inputFolder);
        listOfFiles = folder.listFiles();
    }

    public ArrayList<String> getFilesForWeekNumber(int yearNum, int weekNum) {

        ArrayList<String> fileNamesToReturn = new ArrayList<String>();

        //TODO: Do I have to sort this first?
        for (File file : listOfFiles) {
            Calendar cal = Calendar.getInstance();

            String fileDate = file.getName().substring(0, 9);

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            try {
                Date date = format.parse(fileDate);
                cal.setTime(date);
                int fileYearNum = cal.get(Calendar.YEAR);
                int fileWeekNum = cal.get(Calendar.WEEK_OF_YEAR);


                if (yearNum == fileYearNum && weekNum == fileWeekNum) {
                    System.out.println(weekNum);
                    fileNamesToReturn.add(file.getName());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return fileNamesToReturn;


//        return listOfFiles;
    }

    public String[] getAllFileNames(String inputFolder) {
        File folder = new File(inputFolder);
        File[] listOfFiles = folder.listFiles();

        String[] fileNames = new String[listOfFiles.length];

        for (int i = 0; i < listOfFiles.length; i++) {
            fileNames[i] = listOfFiles[i].getName();
        }

        return fileNames;
    }
}
