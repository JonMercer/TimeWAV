package com.jonmercer.timewav;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Iterates through each week of the year and delegates file merge
 */
public class Time {

    private static final String START_TIME = "1970-01-01";
    private String endTime;

    public Time(FileSystem fileSystem, Merger merger) {

        setEndTime();
        loopThroughTime(fileSystem, merger);
    }


    private void loopThroughTime(FileSystem fileSystem, Merger merger) {

        Date startDate = null;
        Date endDate = null;

        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            startDate = dateFormatter.parse(START_TIME);
            endDate = dateFormatter.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(startDate);
        end.setTime(endDate);


        int yearNum = 0;
        int weekNum = 0;

        //TODO: does it add 1 first or not?
        //TODO: test if it includes the end
        //Increment start date by one day until endTime
        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            cal.setTime(date);

            //Enter once per week
            if (weekNum != cal.get(Calendar.WEEK_OF_YEAR)) {
                yearNum = cal.get(Calendar.YEAR);
                weekNum = cal.get(Calendar.WEEK_OF_YEAR);
                ArrayList<String> nameOfFilesToMerge = fileSystem.getFilesForWeekNumber(yearNum, weekNum);
                merger.merge(nameOfFilesToMerge);
            }

        }
    }

    /**
     * Sets end time to today plus 1 day
     */
    private void setEndTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        endTime = dateFormat.format(cal.getTime());
    }
}
