package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class Knight extends Walker {


    private static final Shape knightShape = new PolygonShape(          //determines shape
            1.04f,1.94f,
            1.82f,-1.78f,
            -0.97f,-1.8f,
            -1.63f,0.34f,
            0.89f,1.93f);

    private static final BodyImage image =
            new BodyImage("data/knightLeft.png", 4f);     //image of knight


    public int livesCount;
    private int fireCount;
    private static int coinCount;
    private static SoundClip coinSound;


    static {
        try {
            coinSound = new SoundClip("data/coinsound.mp3");   // Open an audio input stream
            coinSound.setVolume(0.5);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }


    public Knight(World w) {
        super(w, knightShape);
        addImage(image);

        fireCount = 0;                                     //fire count ia set to 0
        livesCount = 3;                                    //lives are set to 3

    }

    public void addFire(){
        fireCount++;                                       //fire count is incremented
        System.out.println("fireCount = " + fireCount);

    }
    

    public void addCoin(){
        coinCount++;                                       //coin count is incremented
        System.out.println("coinCount = " + coinCount);

    }

    public int getFireCount(){

        return fireCount;
    }

    public static int getCoinCount(){

        return coinCount;
    }

    public void setCoinCount(int cc){

        coinCount = cc;
    }

    public int getLivesCount(){

        return livesCount;
    }

    public void coinSound()
    {
        coinSound.play();

    }


}

