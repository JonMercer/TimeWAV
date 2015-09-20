package com.jonmercer.timewav;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jon Mercer on 15-09-20.
 */
public class Year {

    private int yearNumber;
    private HashMap<Integer, Week> weeks = new HashMap<Integer, Week>();

    public Year(int yearNum) {
        this.yearNumber = yearNum;
    }

    public void addWeek(Week week) {
        weeks.put(week.getWeekNumber(), week);
    }

    public HashMap<Integer, Week> getWeeks() {
        return weeks;
    }
}
