package game;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;


public class Level1 extends GameLevel {

    public Level1(Game game){
        //level 1 class

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

        // make a platform, top left
        Shape platform1Shape = new BoxShape(2, 0.1f);
        StaticBody platform1 = new StaticBody(this, platform1Shape);
        platform1.setPosition(new Vec2(-21.4f, 1.9f));

        // make a platform, top left 2
        Shape platform2Shape = new BoxShape(2.7f, 0.1f);
        StaticBody platform2 = new StaticBody(this, platform2Shape);
        platform2.setPosition(new Vec2(-10.4f, -0.2f));

        // make a platform, middle
        Shape platform3Shape = new BoxShape(3.7f, 0.1f);
        StaticBody platform3 = new StaticBody(this, platform3Shape);
        platform3.setPosition(new Vec2(-0.5f, -2.5f));

        // make a platform, top right
        Shape platform4Shape = new BoxShape(2.f, 0.1f);
        StaticBody platform4 = new StaticBody(this, platform4Shape);
        platform4.setPosition(new Vec2(8.2f, 2));

        // make a platform, bottom left
        Shape platform5Shape = new BoxShape(2.f, 0.1f);
        StaticBody platform5 = new StaticBody(this, platform5Shape);
        platform5.setPosition(new Vec2(-11.3f, -8));

        // make a platform, bottom right
        Shape platform6Shape = new BoxShape(1.9f, 0.1f);
        StaticBody platform6 = new StaticBody(this, platform6Shape);
        platform6.setPosition(new Vec2(9.4f, -8));


        // adds the door
        Door door = new Door(this);
        door.setPosition(new Vec2(19.5f, -9.18f));


    }

    @Override
    public void populate (Game game) {
        super.populate(game);

        //adds the Knight
        getKnight().setPosition(new Vec2(5, -10));
        CatchFire Fire = new CatchFire(getKnight(),getHealth());
        getKnight().addCollisionListener(Fire);
        getKnight().addCollisionListener(new CatchCoin(getKnight()));

        // adds the Dragon
        getDragon().setPosition(new Vec2(-19,-10));
        DragonCollide Knight = new DragonCollide(getDragon(), getKnight(), getHealth());
        getDragon().addCollisionListener(Knight);

        // adds the coin, top left
        Coin coin = new Coin(this);
        coin.setPosition(new Vec2(-21.2f, 2.1f));

        // adds the coin, top right
        Coin coin2 = new Coin(this);
        coin2.setPosition(new Vec2(8.2f, 2.1f));

        // adds the coin, middle
        Coin coin3 = new Coin(this);
        coin3.setPosition(new Vec2(-0.5f, -1.5f));

        // adds the coin, bottom left
        Coin coin4 = new Coin(this);
        coin4.setPosition(new Vec2(-11.2f, -7));

        // adds the coin, top left 2
        Coin coin5 = new Coin(this);
        coin5.setPosition(new Vec2(-10.6f, 1.1f));

        // adds the health bar
        getHealth().setPosition(new Vec2(-21,9));

    }



    @Override
    public boolean isComplete() {
        if (getKnight().getCoinCount() == 5)   //if the coin count equals 5
            return true;                       //returns true
        else
            return false;                      //returns false
    }

    @Override
    public Image paintBackground() {
        Image background = new ImageIcon("data/castle.png").getImage();   //image for the background
        return background;
    }

    @Override
    public String getLevelName() {

        return "Level1";
    }

}
