package game;

import city.cs.engine.BodyImage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class KnightController implements KeyListener {

    private static final float WALKING_SPEED = 4;

    private Game game;
    private Knight knight;


    public KnightController(Game game) {

        this.game = game;
    }

    public void updateKnight(Knight knight) {

        this.knight = knight;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
                                                                                                                         // other key commands omitted
        if (code == KeyEvent.VK_A) {                                                                                     //if the a key is pressed
            game.getLevel().getKnight().removeAllImages();                                                               //remove the image of the right
            game.getLevel().getKnight().addImage(new BodyImage("data/knightLeft.png", 4f));               //add the left image of the knight
            game.getLevel().getKnight().startWalking(-WALKING_SPEED);                                                    //allows the knight to walk to the left
        } else if (code == KeyEvent.VK_D) {                                                                              //if the d key is pressed
            game.getLevel().getKnight().removeAllImages();                                                               //remove the image of the right
            game.getLevel().getKnight().addImage(new BodyImage("data/knightRight.png", 4f));              //add the right image of the knight
            game.getLevel().getKnight().startWalking(WALKING_SPEED);                                                     //allows the knight to walk to the right
        } else if (code == KeyEvent.VK_SPACE) {                                                                          //if the space key is pressed
            game.getLevel().getKnight().jump(WALKING_SPEED* 2.5f);                                                 //allows the knight to jump
        } else if (code == KeyEvent.VK_S) {                                                                              //if the s key is pressed
            System.out.println("save");
            try {
                GameSaverLoader.save(game.getLevel(), "data/save.txt");                                          //game is saved to text file
            }catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else if (code == KeyEvent.VK_L) {                                                                              //if the l key is pressed
            System.out.println("load");
            try {
                GameLevel level = GameSaverLoader.load(game, "data/save.txt");                                   //game is loaded from the text file
                game.setLevel(level);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e){                                                                                 //key released class
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {                                                                                     //if the a key is released,
            game.getLevel().getKnight().stopWalking();                                                                   //the knight will stop walking
        } else if (code == KeyEvent.VK_D) {                                                                              //if the d key is released,
            game.getLevel().getKnight().stopWalking();                                                                   //the knight will stop walking
        }
    }

}
