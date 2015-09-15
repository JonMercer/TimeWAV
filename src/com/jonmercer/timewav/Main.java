package com.jonmercer.timewav;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {

    private static String INPUT_FOLDER = "/Users/Odin/Desktop/wav_input";
    private static String OUTPUT_FOLDER = "/Users/Odin/Desktop/wav_output";
//    private final String inputFolder;
//    private final String outputFolder;
//    private final String timeType;


    public Main(String[] args) {

//        inputFolder = args[0];
//        outputFolder = args[1];
//        timeType = args[3];


        //0. parse user input //TODO: done
        // jar TimeWAV /input/folder /output/folder -w
        //1. find day/week/month/year and itterate through it
        //2. does an audio file belong in this week
        //3. merge audio files
        //4. Create output file

        Merger merger = new Merger();
        FileSystem fileSystem = new FileSystem(INPUT_FOLDER, OUTPUT_FOLDER);
        Time time = new Time(fileSystem, merger);

    }



    public static void main(String[] args) {
        new Main(args);
    }
}
