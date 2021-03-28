package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class CatchFire implements CollisionListener {

    private final Knight knight;
    private final Health health;
    private static SoundClip lifeLost;
    private static SoundClip gameOver;


    static {
        try {
            lifeLost = new SoundClip("data/lifelost.mp3");   // Open an audio input stream
            lifeLost.setVolume(0.5);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    static {
        try {
            gameOver = new SoundClip("data/gameover.mp3");   // Open an audio input stream
            gameOver.setVolume(0.5);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public CatchFire(Knight k, Health health){

        this.knight = k;
        this.health = health;

    }

    public void lifeLost()
    {
        lifeLost.play();

    }

    public void gameOver(){

        gameOver.play();
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Fire) {                           //if the knight collides with the fire
            knight.livesCount-- ;                                         //the lives go down by 1
            System.out.println("livesCount = " + knight.livesCount);      //the lives left are printed

            if (knight.livesCount == 2) {                                 //if there are 2 lives left
                knight.addFire();                                         //the fire count goes up by 1
                e.getOtherBody().destroy();                               //the fire body is destroyed
                lifeLost();                                               //a sound is played
                health.setTwoHearts();                                    //the health image changes to the 2 hearts
                knight.setPosition(new Vec2(20.5f, 3.5f));          //the knight goes back to its initial position


            }
            else if (knight.livesCount == 1) {                            //if there is 1 life left
                knight.addFire();                                         //the fire count goes up by 1
                e.getOtherBody().destroy();                               //the fire body is destroyed
                lifeLost();                                               //a sound is played
                health.setOneHeart();;                                    //the health image changes to the 1 heart
                knight.setPosition(new Vec2(20.5f, 3.5f));          //the knight goes back to its initial position


            }
            else if (knight.livesCount == 0) {                           //if there is 0 lives left
                gameOver();                                              //a sound is played
                System.exit(0);                                    //the game is terminated
            }
        }
    }
}