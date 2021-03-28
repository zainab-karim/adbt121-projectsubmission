package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class DoorListener implements CollisionListener {

    private final GameLevel level;
    private Game game;

    public DoorListener(GameLevel level, Game game) {
        this.level = level;
        this.game = game;
    }


    @Override
    public void collide (CollisionEvent e) {
        boolean levelDone = game.isComplete();
        if (e.getOtherBody() instanceof Door                                     //if the knight collides with the door
            && level.isComplete()) {                                             //and the level is completed
            level.doorSound();                                                   //a sound is played
            System.out.println("door sound");                                    //a line is printed
            System.out.println("Going to the next level...");                    //a line is printed
            game.goToNextLevel();                                                //it goes to the next level
        }
    }

}
