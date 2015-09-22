package com.jonmercer.timewav;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jon Mercer on 15-09-12.
 */
public class Merger {

    /**
     * Iterate through a map of years and the weeks within each year
     * @param organizedDates ordered data structure of years
     * @param inputFolderPath the input folder path
     * @param outputFolderPath the output folder path
     */
    public void mergeAllTheWeeks(HashMap<Integer, Year> organizedDates, String inputFolderPath, String outputFolderPath) {

        Object[] years = organizedDates.values().toArray();

        for (int i = 0; i < years.length; i++) {
            HashMap<Integer, Week> weeksMap = ((Year) years[i]).getWeeks();
            Object[] weeks = weeksMap.values().toArray();

            for (int j = 0; j < weeks.length; j++) {
                ArrayList<String> sortedFileNames = ((Week) weeks[i]).getSortedFileNames();
                System.out.println("begin merge on: " + sortedFileNames.toString());
                mergeListOfFiles(sortedFileNames, inputFolderPath, outputFolderPath);
            }
        }
    }

    /**
     * Given a list of filenames, merge them.
     * @param nameOfFilesToMerge list of files to merge
     * @param inputFolderPath the input folder path
     * @param outputFolderPath the output folder path
     */
    public void mergeListOfFiles(ArrayList<String> nameOfFilesToMerge, String inputFolderPath, String outputFolderPath) {

        //TODO: what if size is 0?
        if (nameOfFilesToMerge.size() == 1) {
            try {
                AudioInputStream onlyWav = AudioSystem.getAudioInputStream(new File(inputFolderPath + "/" + nameOfFilesToMerge.get(0)));
                writeWav(onlyWav, nameOfFilesToMerge.get(0), outputFolderPath);
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (nameOfFilesToMerge.size() > 1) {
            try {
                AudioInputStream currentWav;
                currentWav = AudioSystem.getAudioInputStream(new File(inputFolderPath + "/" + nameOfFilesToMerge.get(0)));

                for (int i = 1; i < nameOfFilesToMerge.size(); i++) {
                    AudioInputStream toAppend = AudioSystem.getAudioInputStream(new File(inputFolderPath + "/" + nameOfFilesToMerge.get(i)));
                    currentWav =
                            new AudioInputStream(
                                    new SequenceInputStream(currentWav, toAppend),
                                    currentWav.getFormat(),
                                    currentWav.getFrameLength() + toAppend.getFrameLength());
                }

                writeWav(currentWav, nameOfFilesToMerge.get(0), outputFolderPath);

            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Write a WAV from memory to disk. Will rename based on dateString
     * @param wav the WAV file
     * @param dateString the date of this file
     * @param outputFolderPath the output folder path
     */
    public void writeWav(AudioInputStream wav, String dateString, String outputFolderPath) {
        Time time = new Time();

        int week = time.getWeekNum(dateString);
        int year = time.getYearNum(dateString);

        try {
            if(week < 10) {
                //Prepend a 0 in front of the number
                AudioSystem.write(wav,
                        AudioFileFormat.Type.WAVE,
                        new File(outputFolderPath+"/"+year+"-0"+week+".wav"));
            } else {
                AudioSystem.write(wav,
                        AudioFileFormat.Type.WAVE,
                        new File(outputFolderPath+"/"+year+"-"+week+".wav"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
