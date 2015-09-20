package com.jonmercer.timewav;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Iterates through each week of the year and delegates file merge
 */
public class Time {

    private static final String START_TIME = "1970-01-01";
    private String endTime;

    public Time(FileSystem fileSystem, Merger merger) {

        setEndTime();
//        loopThroughTime(fileSystem, merger);
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

    public HashMap<Integer, Year> organizeFileNamesByYearAndWeek(String[] fileNames) {


        HashMap<Integer, Year> years = new HashMap<Integer, Year>();

        for (String fileName : fileNames) {

            String dateAndTimeString = fileName.substring(0, 16);

            int yearNum = getYear(dateAndTimeString);
            int weekNum = getWeek(dateAndTimeString);

            //A new year and thus a new week
            if (years.get(yearNum) == null) {

                Year year = new Year(yearNum);
                Week week = new Week(weekNum);

                week.addFileName(fileName);
                year.addWeek(week);
                years.put(yearNum, year);
            } else {
                Year year = years.get(yearNum);
                HashMap<Integer, Week> weeks = year.getWeeks();
                Week week = weeks.get(weekNum);

                //A new week
                if (week == null) {

                    week = new Week(weekNum);
                    week.addFileName(fileName);
                    year.addWeek(week);

                } else { //week already created
                    week.addFileName(fileName);
                }
            }
        }

        return years;
    }

    private int getYear(String dateAndTimeString) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh_mm");

        try {
            Date date = dateFormatter.parse(dateAndTimeString);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            return cal.get(Calendar.YEAR);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int getWeek(String dateAndTimeString) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh_mm");

        try {
            Date date = dateFormatter.parse(dateAndTimeString);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            return cal.get(Calendar.WEEK_OF_YEAR);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
