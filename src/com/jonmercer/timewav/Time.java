package com.jonmercer.timewav;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jon Mercer on 15-09-12.
 */
public class Time {
    public Time(FileSystem fileSystem, Merger merger) {

    }

    private void loopThroughTime() {

        Date startDate = null;
        Date endDate = null;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            startDate = formatter.parse("1900-01-01");
            endDate = formatter.parse("2010-12-26");
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

            int week = cal.get(Calendar.WEEK_OF_YEAR);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DAY_OF_YEAR);

            System.out.println(day + " " + week +" " + month + " " + year);
        }
    }
}
