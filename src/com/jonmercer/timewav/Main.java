package com.jonmercer.timewav;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {

    private static final String INPUT_FOLDER = "/Users/Odin/Desktop/wav_input";
    private static final String OUTPUT_FOLDER = "/Users/Odin/Desktop/wav_output";

    public Main(String[] args) {
        //TODO: process args

        Merger merger = new Merger(INPUT_FOLDER, OUTPUT_FOLDER);
        FileSystem fileSystem = new FileSystem(INPUT_FOLDER, OUTPUT_FOLDER);
        new Time(fileSystem, merger);
    }

    public static void main(String[] args) {
        new Main(args);
    }
}
