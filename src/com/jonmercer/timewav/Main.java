package com.jonmercer.timewav;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Main {

    private static String TEMP_IN_PATH = "/Users/Odin/Desktop/wav_input";
    private static String TEMP_OUT_PATH = "/Users/Odin/Desktop/wav_output";


    public Main() {
        //1. find day/week/month/year
        //2. does an audio file belong in this week
        //3. merge audio files
        //4. Create output file

        loopThroughTime();
    }

    private void loopThroughTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = formatter.parse("1900-01-01");
            endDate = formatter.parse("2010-12-26");
        } catch (ParseException e) {
            e.printStackTrace();
        }


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

    public static void main(String[] args) {
        new Main();
    }
}
