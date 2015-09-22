package com.jonmercer.timewav;

import java.util.HashMap;

public class Main {

    private static final String INPUT_FOLDER = "/Users/Odin/Desktop/wav_input";
    private static final String OUTPUT_FOLDER = "/Users/Odin/Desktop/wav_output";

    public Main(String[] args) {
        //TODO: process args

        FileSystem fileSystem = new FileSystem();
        Time time = new Time();
        Merger merger = new Merger();

        String[] allFileNames = fileSystem.getAllFileNamesFromInputFolder(INPUT_FOLDER);
        HashMap<Integer, Year> organizedDates = time.organizeFileNamesByYearAndWeek(allFileNames);
        merger.mergeAllTheWeeks(organizedDates, INPUT_FOLDER, OUTPUT_FOLDER);

    }

    public static void main(String[] args) {
        new Main(args);
    }
}
