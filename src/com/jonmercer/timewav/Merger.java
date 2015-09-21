package com.jonmercer.timewav;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Jon Mercer on 15-09-12.
 */
public class Merger {

    private String inputFolder;
    private String outputFolder;

    private int week = 0;
    private int year = 999;

    public Merger(String inputFolder, String outputFolder) {

        this.inputFolder = inputFolder;
        this.outputFolder = outputFolder;
    }


    public void mergeAllTheWeeks(HashMap<Integer, Year> organizedDates) {

        Object[] years = organizedDates.values().toArray();

        for (int i = 0; i < years.length; i++) {
            HashMap<Integer, Week> weeksMap = ((Year) years[i]).getWeeks();
            Object[] weeks = weeksMap.values().toArray();

            for (int j = 0; j < weeks.length; j++) {
                ArrayList<String> sortedFileNames = ((Week) weeks[i]).getSortedFileNames();
                System.out.println("begin merge on: " + sortedFileNames.toString());
                mergeOneWeek(sortedFileNames);
            }
        }
    }

    public void mergeOneWeek(ArrayList<String> nameOfFilesToMerge) {

        if (nameOfFilesToMerge.size() == 1) {
            try {
                AudioInputStream onlyWav = AudioSystem.getAudioInputStream(new File(inputFolder + "/" + nameOfFilesToMerge.get(0)));
                writeWav(onlyWav, nameOfFilesToMerge.get(0));
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return;
        } else if (nameOfFilesToMerge.size() > 1) {
            week = week + 1;
            try {
                AudioInputStream currentWav;
                currentWav = AudioSystem.getAudioInputStream(new File(inputFolder + "/" + nameOfFilesToMerge.get(0)));

                for (int i = 1; i < nameOfFilesToMerge.size(); i++) {
                    AudioInputStream toAppend = AudioSystem.getAudioInputStream(new File(inputFolder + "/" + nameOfFilesToMerge.get(i)));
                    currentWav =
                            new AudioInputStream(
                                    new SequenceInputStream(currentWav, toAppend),
                                    currentWav.getFormat(),
                                    currentWav.getFrameLength() + toAppend.getFrameLength());
                }


                writeWav(currentWav, nameOfFilesToMerge.get(0));


            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void writeWav(AudioInputStream wav, String dateString) {
        Time time = new Time();

        int week = time.getWeekNum(dateString);
        int year = time.getYearNum(dateString);

        try {
            if(week < 10) {
                //Prepend a 0 in front of the number
                AudioSystem.write(wav,
                        AudioFileFormat.Type.WAVE,
                        new File(outputFolder+"/"+year+"-0"+week+".wav"));
            } else {
                AudioSystem.write(wav,
                        AudioFileFormat.Type.WAVE,
                        new File(outputFolder+"/"+year+"-"+week+".wav"));
            }
        } catch (Exception e) {
            System.out.println("Error in writeWav");
            e.printStackTrace();
        }
    }


}
