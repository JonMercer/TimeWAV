package com.jonmercer.timewav;

import java.util.HashMap;

public class Main {

    private static final String INPUT_FOLDER = "/Users/Odin/Desktop/wav_input";
    private static final String OUTPUT_FOLDER = "/Users/Odin/Desktop/wav_output";

    public Main(String[] args) {
        //TODO: process args

        Merger merger = new Merger(INPUT_FOLDER, OUTPUT_FOLDER);
        FileSystem fileSystem = new FileSystem(INPUT_FOLDER, OUTPUT_FOLDER);
        Time time = new Time(fileSystem, merger);

        //Refactoring:
        //1. Iterate through files and create a list of years and weeks //TODO: done
        //2. Iterate through list and merge items


        String[] allFileNames = fileSystem.getAllFileNames(INPUT_FOLDER);
        HashMap<Integer, Year> organizedDates = time.organizeFileNamesByYearAndWeek(allFileNames);
        merger.merge(organizedDates);

    }

    public static void main(String[] args) {
        new Main(args);
    }
}
