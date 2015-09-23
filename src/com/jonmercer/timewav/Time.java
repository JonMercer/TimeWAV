package com.jonmercer.timewav;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Organizes the list of file names such that the Merger can consume it.
 */
public class Time {

    /**
     * Given a list of file names, organize them into their respective year and week within that year.
     * @param fileNames list of fileNames in a certain format
     * @return Map of years.
     */
    public HashMap<Integer, Year> organizeFileNamesByYearAndWeek(String[] fileNames) {


        HashMap<Integer, Year> years = new HashMap<Integer, Year>();

        for (String fileName : fileNames) {

            String dateAndTimeString = fileName.substring(0, 16);

            int yearNum = getYearNum(dateAndTimeString);
            int weekNum = getWeekNum(dateAndTimeString);

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

    /**
     * Given a dateString, find the year number
     * @param dateAndTimeString date string in a certain format
     * @return the year number
     */
    public int getYearNum(String dateAndTimeString) {
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

    /**
     * Given a dateString, find the week number
     * @param dateAndTimeString date string in a certain format
     * @return the year number
     */
    public int getWeekNum(String dateAndTimeString) {
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
