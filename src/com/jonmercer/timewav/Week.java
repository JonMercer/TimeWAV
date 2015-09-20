package com.jonmercer.timewav;

import java.util.ArrayList;

/**
 * Created by Jon Mercer on 15-09-20.
 */
public class Week {

    private int weekNumber;
    private ArrayList<String> fileNames = new ArrayList<String>();

    public Week(int weekNum) {
        this.weekNumber = weekNum;
    }

    public void addFileName(String name) {
        fileNames.add(name);
    }

    public int getWeekNumber() {
        return weekNumber;
    }
}
