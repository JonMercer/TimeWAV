package com.jonmercer.timewav;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jon Mercer on 15-09-12.
 */
public class Time {

    private int yearNum = 0;
    private int weekNum = 0;

    private ArrayList<Integer> year = new ArrayList<Integer>();
    private ArrayList<Integer> week = new ArrayList<Integer>();
    private FileSystem fileSystem;
    private Merger merger;


    public Time(FileSystem fileSystem, Merger merger) {

        this.fileSystem = fileSystem;
        this.merger = merger;
        loopThroughTime();
    }

    private void loopThroughTime() {

        Date startDate = null;
        Date endDate = null;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            startDate = formatter.parse("1900-01-01");
            endDate = formatter.parse("2020-12-26");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Calendar cal = Calendar.getInstance();
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);


        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            // Do your job here with `date`.
            cal.setTime(date);

            if(weekNum != cal.get(Calendar.WEEK_OF_YEAR)) {
                yearNum = cal.get(Calendar.YEAR);
                weekNum = cal.get(Calendar.WEEK_OF_YEAR);
                ArrayList<String> nameOfFilesToMerge = fileSystem.getFilesForWeekNumber(yearNum, weekNum);
                merger.merge(nameOfFilesToMerge);
            }



        }
    }
}
