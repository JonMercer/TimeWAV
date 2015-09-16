package com.jonmercer.timewav;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;

/**
 * Created by Jon Mercer on 15-09-12.
 */
public class Merger {


    private String inputFolder;
    private String outputFolder;

    public Merger(String inputFolder, String outputFolder) {

        this.inputFolder = inputFolder;
        this.outputFolder = outputFolder;
    }

    public void merge(ArrayList<String> nameOfFilesToMerge) {

        if (nameOfFilesToMerge.size() == 1) {
            writeWAV(nameOfFilesToMerge.get(0));
            return;
        }

        try {
            AudioInputStream clip1 = AudioSystem.getAudioInputStream(new File(inputFolder + "/" + nameOfFilesToMerge.get(0)));

            for (int i = 1; i < nameOfFilesToMerge.size(); i++) {
                AudioInputStream clip2 = AudioSystem.getAudioInputStream(new File(inputFolder + "/" + nameOfFilesToMerge.get(i)));
            }


        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //grab second audio
        //merge both

        //write it out


    }

    private void writeWAV(String s) {
    }


    public void merge(ArrayList<String> fileNames, int foo) {
        AudioInputStream currentWav = null;
        for (int i = 0; i < fileNames.size(); i++) {
            if(i == 0) {
                currentWav = AudioSystem.getAudioInputStream(new File(inputFolder + "/" + INTRO));
            } else {
                currentWav = appendWav(currentWav, file.getName());
            }
        }

        writeWav(currentWav, 9999, 9999);

    }

    private AudioInputStream appendWav(AudioInputStream currentWav, String appendName) {
        try{

            AudioInputStream toAppend = AudioSystem.getAudioInputStream(new File(inputFolder+"/"+appendName));
            AudioInputStream appendedFiles =
                    new AudioInputStream(
                            new SequenceInputStream(currentWav, toAppend),
                            currentWav.getFormat(),
                            currentWav.getFrameLength() + toAppend.getFrameLength());

            return appendedFiles;

        } catch (Exception e) {
            e.printStackTrace();
        }
        //Note, I'm not catching this null on the return.
        return null;
    }

    private void writeWav(AudioInputStream wav, int week, int year) {
        try {
            if(week < 10) {
                //Prepend a 0 infront of the number
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
