package com.jonmercer.timewav;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.SequenceInputStream;
import java.util.ArrayList;

/**
 * Created by Jon Mercer on 15-09-12.
 */
public class Merger {


    public void merge(ArrayList<File> files) {
        AudioInputStream currentWav = null;
        for (int i = 0; i < files.size(); i++) {
            if(i == 0) {
                currentWav = AudioSystem.getAudioInputStream(new File(INPUT_PATH + "/" + INTRO));
            } else {
                currentWav = appendWav(currentWav, file.getName());
            }
        }

        writeWav(currentWav, 9999, 9999);

    }

    private AudioInputStream appendWav(AudioInputStream currentWav, String appendName) {
        try{

            AudioInputStream toAppend = AudioSystem.getAudioInputStream(new File(INPUT_PATH+"/"+appendName));
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
                        new File(OUTOUT_PATH+"/"+year+"-0"+week+".wav"));
            } else {
                AudioSystem.write(wav,
                        AudioFileFormat.Type.WAVE,
                        new File(OUTOUT_PATH+"/"+year+"-"+week+".wav"));
            }
        } catch (Exception e) {
            System.out.println("Error in writeWav");
            e.printStackTrace();
        }
    }


}
