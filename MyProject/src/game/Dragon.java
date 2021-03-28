package game;

import city.cs.engine.*;
import city.cs.engine.BodyImage;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;



public class Dragon extends Walker {

    private static final Shape dragonShape = new PolygonShape(                                  //determines shape
            1.48f,2.06f,
            2.95f,0.66f,
            2.14f,-2.58f,
            -3.04f,-2.67f,
            -2.16f,1.02f,
            1.4f,2.1f);

    private static final BodyImage image =
            new BodyImage("data/dragonRight.png", 7f);                           //image for Dragon


    private static SoundClip knifeSound;

    static {
        try {
            knifeSound = new SoundClip("data/knives.mp3");                             //open an audio input stream
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Dragon(World w) {
        super(w, dragonShape);
        addImage(image);


    }

    public void knifeSound()
    {

        knifeSound.play();
    }

}



