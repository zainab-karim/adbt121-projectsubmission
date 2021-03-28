package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class CatchCoin implements CollisionListener {

    private Knight knight;

    public CatchCoin(Knight k){

        this.knight = k;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Coin) { //if the knight collides with the coin
            knight.addCoin();                   //add coin is class used
            knight.coinSound();                 //coin sound is played
            e.getOtherBody().destroy();         //coin object disappears

        }
    }

}