package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

public class Tracker implements StepListener {
    private final GameView view;
    private final Knight knight;
    public Tracker(GameView view, Knight knight) {
        this.view = view;
        this.knight = knight;
    }
    public void preStep(StepEvent e) {}
    public void postStep(StepEvent e) {

        view.setCentre(new Vec2(knight.getPosition()));
    }
}