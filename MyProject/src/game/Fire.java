package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Fire extends DynamicBody {

    private static final Shape fireShape = new CircleShape(1);         //determines shape

    private static final BodyImage image =
            new BodyImage("data/fire.png", 2f);               //image of fire


    private static SoundClip fireSound;
    static {
        try {
            fireSound = new SoundClip("data/firesound.mp3");        // Open an audio input stream
            fireSound.setVolume(0.5);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Fire(World w) {
        super(w,fireShape);
        addImage(image);

    }

    @Override
    public void destroy()
    {
        fireSound.play();
        super.destroy();

    }
}
