package game;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;


public class Level2 extends GameLevel{


    public Level2(Game game){
        //level 2 class

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
        Shape platform1Shape = new BoxShape(2.2f, 0.1f);
        StaticBody platform1 = new StaticBody(this, platform1Shape);
        platform1.setPosition(new Vec2(-20.8f, 2.8f));

        // make a platform, top left 2
        Shape platform2Shape = new BoxShape(2.2f, 0.1f);
        StaticBody platform2 = new StaticBody(this, platform2Shape);
        platform2.setPosition(new Vec2(-5.5f, 2.8f));

        // make a platform, middle
        Shape platform3Shape = new BoxShape(3.7f, 0.1f);
        StaticBody platform3 = new StaticBody(this, platform3Shape);
        platform3.setPosition(new Vec2(1.3f, -2.3f));

        // make a platform, middle left
        Shape platform4Shape = new BoxShape(3.7f, 0.1f);
        StaticBody platform4 = new StaticBody(this, platform4Shape);
        platform4.setPosition(new Vec2(-12.3f, -2.3f));

        // make a platform, middle right
        Shape platform5Shape = new BoxShape(3.7f, 0.1f);
        StaticBody platform5 = new StaticBody(this, platform5Shape);
        platform5.setPosition(new Vec2(14.8f, -2.3f));

        // make a platform, top right
        Shape platform6Shape = new BoxShape(2.2f, 0.1f);
        StaticBody platform6 = new StaticBody(this, platform6Shape);
        platform6.setPosition(new Vec2(8.2f, 2.8f));

        // make a platform, top right 2
        Shape platform7Shape = new BoxShape(2.f, 0.1f);
        StaticBody platform7 = new StaticBody(this, platform7Shape);
        platform7.setPosition(new Vec2(21.4f, 2.8f));

        // make a platform, bottom left
        Shape platform8Shape = new BoxShape(2.2f, 0.1f);
        StaticBody platform8 = new StaticBody(this, platform8Shape);
        platform8.setPosition(new Vec2(-7.2f, -6.8f));

        // make a platform, bottom middle
        Shape platform9Shape = new BoxShape(2.2f, 0.1f);
        StaticBody platform9 = new StaticBody(this, platform9Shape);
        platform9.setPosition(new Vec2(1.6f, -8.9f));

        // make a platform, bottom right
        Shape platform10Shape = new BoxShape(2.2f, 0.1f);
        StaticBody platform10 = new StaticBody(this, platform10Shape);
        platform10.setPosition(new Vec2(8.8f, -6.8f));


        // adds the door
        Door door = new Door (this);
        door.setPosition(new Vec2(22f, -9.18f));



    }

    @Override
    public void populate (Game game) {
        super.populate(game);

        //adds the Knight
        getKnight().setPosition(new Vec2(5, -10));
        CatchFire Fire = new CatchFire(getKnight(), getHealth());
        getKnight().addCollisionListener(Fire);
        getKnight().addCollisionListener(new CatchCoin(getKnight()));

        // adds the Dragon
        getDragon().setPosition(new Vec2(-19,-10));
        DragonCollide Knight = new DragonCollide(getDragon(), getKnight(), getHealth());
        getDragon().addCollisionListener(Knight);

        // adds the coin, top left
        Coin coin1 = new Coin(this);
        coin1.setPosition(new Vec2(-20.7f, 3f));

        // adds the coin, top left 2
        Coin coin2 = new Coin(this);
        coin2.setPosition(new Vec2(-5.4f, 3f));

        // adds the coin, middle
        Coin coin3 = new Coin(this);
        coin3.setPosition(new Vec2(1.7f, -1.3f));

        // adds the coin, middle left
        Coin coin4 = new Coin(this);
        coin4.setPosition(new Vec2(-12.5f, -1.3f));

        // adds the coin, middle right
        Coin coin5 = new Coin(this);
        coin5.setPosition(new Vec2(15f, -1.3f));

        // adds the coin, top right
        Coin coin6 = new Coin(this);
        coin6.setPosition(new Vec2(8.4f, 3f));

        // adds the coin, top right 2
        Coin coin7 = new Coin(this);
        coin7.setPosition(new Vec2(21.6f, 3f));

        // adds the coin, bottom left
        Coin coin8 = new Coin(this);
        coin8.setPosition(new Vec2(-7.4f, -5.9f));

        // adds the coin, bottom middle
        Coin coin9 = new Coin(this);
        coin9.setPosition(new Vec2(1.6f, -8.7f));

        // adds the coin, bottom right
        Coin coin10 = new Coin(this);
        coin10.setPosition(new Vec2(9.1f, -5.9f));

        // adds the health bar
        getHealth().setPosition(new Vec2(-21,9));


    }



    @Override
    public boolean isComplete() {
        if (getKnight().getCoinCount() == 15)  //if the coin count equals 15
            return true;                       //returns true
        else
            return false;                      //returns false
    }

    @Override
    public Image paintBackground() {
        Image background = new ImageIcon("data/castle2.png").getImage();   //image for the background
        return background;
    }

    @Override
    public String getLevelName() {

        return "Level2";
    }


}
