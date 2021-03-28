package game;

import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import city.cs.engine.World;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

public abstract class GameLevel extends World {
    private Knight knight;
    private Dragon dragon;
    private Health health;

    private static SoundClip doorSound;

    static {
        try {
            doorSound = new SoundClip("data/doorsound.mp3");   // Open an audio input stream
            doorSound.setVolume(0.5);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }


    public void doorSound()
    {
        doorSound.play();

    }


    public GameLevel(Game game){
    }

    public void populate(Game game){
        knight = new Knight(this);
        dragon = new Dragon(this);
        health = new Health (this);

        DoorListener listener = new DoorListener(this, game);   //door listener
        knight.addCollisionListener(listener);

    }

    public Knight getKnight(){

        return knight;
    }

    public void setKnight(Knight k) {

        knight = k;
    }

    public Dragon getDragon(){

        return dragon;
    }

    public void setDragon(Dragon d) {

        dragon = d;
    }

    public Health getHealth(){

        return health;
    }

    public void setHealth(Health h) {

        health = h;
    }


    public abstract boolean isComplete();

    public abstract Image paintBackground();

    public abstract String getLevelName();

}


