package game;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.SoundClip;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;



public class Level3 extends GameLevel implements ActionListener{

    private Random rand;
    private static SoundClip fireSound;


    static {
        try {
            fireSound = new SoundClip("data/firesound.mp3");   // Open an audio input stream
            fireSound.setVolume(0.25);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }


    public Level3(Game game){
        //level 3 class
        super(game);


        // make the ground
        Shape shape = new BoxShape(22.8f, 0.01f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0, -11.6f));

        //make left wall
        Shape wallShape = new BoxShape(0.01f, 11f);
        StaticBody wall1 = new StaticBody(this, wallShape);
        wall1.setPosition(new Vec2(-23.5f, 0f));

        //make right wall
        StaticBody wall2 = new StaticBody(this, wallShape);
        wall2.setPosition(new Vec2(23.5f, 0f));


        // make a platform, 1
        Shape platform1Shape = new BoxShape(3.5f, 0.05f);
        StaticBody platform1 = new StaticBody(this, platform1Shape);
        platform1.setPosition(new Vec2(-20.5f, 2.5f));

        // make a platform, 2
        Shape platform2Shape = new BoxShape(3f, 0.05f);
        StaticBody platform2 = new StaticBody(this, platform2Shape);
        platform2.setPosition(new Vec2(-10.5f, -3.5f));

        // make a platform, 3
        Shape platform3Shape = new BoxShape(3f, 0.05f);
        StaticBody platform3 = new StaticBody(this, platform3Shape);
        platform3.setPosition(new Vec2(0f, 0f));

        // make a platform, 4
        Shape platform4Shape = new BoxShape(3f, 0.05f);
        StaticBody platform4 = new StaticBody(this, platform4Shape);
        platform4.setPosition(new Vec2(10.5f, -3.5f));

        // make a platform, 5
        Shape platform5Shape = new BoxShape(3.2f, 0.05f);
        StaticBody platform5 = new StaticBody(this, platform5Shape);
        platform5.setPosition(new Vec2(20.5f, 2.5f));

        // make a platform, 6
        Shape platform6Shape = new BoxShape(3f, 0.05f);
        StaticBody platform6 = new StaticBody(this, platform6Shape);
        platform6.setPosition(new Vec2(-20.5f, -8.5f));

        // make a platform, 7
        Shape platform7Shape = new BoxShape(3f, 0.05f);
        StaticBody platform7 = new StaticBody(this, platform7Shape);
        platform7.setPosition(new Vec2(0f, -7.5f));

        // make a platform, 8
        Shape platform8Shape = new BoxShape(2.8f, 0.05f);
        StaticBody platform8 = new StaticBody(this, platform8Shape);
        platform8.setPosition(new Vec2(20.5f, -8.5f));


        // adds the door
        Door door = new Door (this);
        door.setPosition(new Vec2(-21.6f, 4.8f));

        // makes a timer
        Timer t = new Timer(2000,this);
        t.start();
        rand = new Random();

    }


    public void fireSound()
    {
        fireSound.play();

    }

    @Override
    public void populate (Game game) {
        super.populate(game);

        //adds the Knight
        getKnight().setPosition(new Vec2(20.5f, 3.5f));
        CatchFire Fire = new CatchFire(getKnight(), getHealth());
        getKnight().addCollisionListener(Fire);
        getKnight().addCollisionListener(new CatchCoin(getKnight()));


        // adds the Dragon
        getDragon().setPosition(new Vec2(-13,-10));
        DragonCollide Knight = new DragonCollide(getDragon(), getKnight(), getHealth());
        getDragon().addCollisionListener(Knight);

        // adds the coin, top left
        Coin coin1 = new Coin(this);
        coin1.setPosition(new Vec2(-18.5f, 4.4f));

        // adds the coin, middle
        Coin coin2 = new Coin(this);
        coin2.setPosition(new Vec2(0f, 2f));


        // adds the health bar
        getHealth().setPosition(new Vec2(-21,9));

    }


    @Override
    public boolean isComplete() {
        if (getKnight().getCoinCount() == 17)  //if the coin count equals 17
            return true;                       //returns true
        else
            return false;                      //returns false
    }

    @Override
    public Image paintBackground() {
        Image background = new ImageIcon("data/castle3.png").getImage();   //image for the background
        return background;
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if (isComplete() == false) {
            fireSound();                                               //sound is played
            System.out.println("Action event!");                       //line is outputted
            Fire f = new Fire(this);                                //fire appears
            f.setPosition(new Vec2(rand.nextFloat() * 20, 10));  //at random location within a range
        }
    }

    @Override
    public String getLevelName() {

        return "Level3";
    }

}
