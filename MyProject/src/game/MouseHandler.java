package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler extends MouseAdapter {

    private final GameLevel level;
    private final GameView view;

    public MouseHandler(GameLevel l, GameView v){
        level = l;
        view = v;
    }

    /**
    @Override
    public void mousePressed(MouseEvent e) {
        //create fire and add them to world
        Fire fire = new Fire(level);
        //get the mouse coordinates
        Point mousePoint = e.getPoint();
        //transform them to world coordinates
        Vec2 worldPoint = view.viewToWorld(mousePoint);
        //position the fire
        fire.setPosition(worldPoint);
    } **/


    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}

