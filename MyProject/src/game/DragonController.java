package game;

import city.cs.engine.BodyImage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class DragonController implements KeyListener {

    private static float WALKING_SPEED = 3;

    private Dragon dragon;

    public DragonController(Dragon d) {

        dragon = d;
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {  //key controls for the dragon
        int code = e.getKeyCode();
        // other key commands omitted
        if (code == KeyEvent.VK_LEFT) { //if the left key is pressed
            dragon.removeAllImages();
            dragon.addImage(new BodyImage("data/dragonLeft.png", 7f));
            dragon.startWalking(-WALKING_SPEED);
        } else if (code == KeyEvent.VK_RIGHT) { //if the right key is pressed
            dragon.removeAllImages();
            dragon.addImage(new BodyImage("data/dragonRight.png", 7f));
            dragon.startWalking(WALKING_SPEED);
        } else if (code == KeyEvent.VK_UP) { //if the up key is pressed
            dragon.jump(WALKING_SPEED* 3.2f);
        }
    }
    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) {
            dragon.stopWalking();
        } else if (code == KeyEvent.VK_RIGHT) {
            dragon.stopWalking();
        }
    }

    public void updateDragon(Dragon dragon){

        this.dragon = dragon;
    }

}