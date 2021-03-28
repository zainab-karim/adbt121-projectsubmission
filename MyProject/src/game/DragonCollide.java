package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class DragonCollide implements CollisionListener {

    private final Dragon dragon;
    private final Knight knight;
    private final Health health;
    private static SoundClip gameOver;


    static {
        try {
            gameOver = new SoundClip("data/gameover.mp3");                        // Open an audio input stream
            gameOver.setVolume(0.5);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }


    public DragonCollide(Dragon dragon, Knight knight, Health health){

        this.dragon = dragon;
        this.knight = knight;
        this.health = health;

    }


    public void gameOver(){

        gameOver.play();
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Knight) {                          //if the dragon collides with the knight
            knight.livesCount-- ;                                          //the lives go down by 1
            System.out.println("livesCount = " + knight.livesCount);       //the lives left are printed

            if (knight.livesCount == 2) {                                  //if there are 2 lives left
                dragon.knifeSound();                                       //a sound is played
                health.setTwoHearts();                                     //the health image changes to the 2 hearts
                knight.setPosition(new Vec2(5, -10));                //the knight goes back to its initial position
                dragon.setPosition(new Vec2(-19,-10));               //the dragon goes back to its initial position

            }
            else if (knight.livesCount == 1) {                            //if there is 1 life left
                dragon.knifeSound();                                      //a sound is played
                health.setOneHeart();                                     //the health image changes to the 1 heart
                knight.setPosition(new Vec2(5, -10));               //the knight goes back to its initial position
                dragon.setPosition(new Vec2(-19,-10));              //the dragon goes back to its initial position


            }
            else if (knight.livesCount == 0) {                           //if there is 0 lives left
                gameOver();                                              //a sound is played
                System.exit(0);                                    //the game is terminated
            }
        }
    }


}