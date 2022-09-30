package uet.oop.bomberman.Sound_Bomberman;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    public static final String soundtrack = "soundtrack";
    public static final String pickItem = "pickItem";
    public static final String walking = "walking";
    public static final String placeBomb = "placeBomb";
    public static final String enemyDead = "enemyDead";
    public static final String bombermanDead = "bombermanDead";
    public static final String nextLevel = "nextLevel";
    public static final String bombExplode = "bombExplode";
    private Clip clip;

    public Sound(String filename){
        String path ="C:\\Users\\Nguyen Duc Thien\\OneDrive\\Desktop\\OOP_Bomberman\\bomberman-starter\\res\\Sound_bombay"
                + filename + ".wav";
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(inputStream);
        } catch (UnsupportedAudioFileException |LineUnavailableException | IOException e){
            e.printStackTrace();
        }
    }
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
