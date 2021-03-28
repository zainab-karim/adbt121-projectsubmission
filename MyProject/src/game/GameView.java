package game;

import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;


public class GameView extends UserView {

    private GameLevel level;
    private Image background;
    public Image back;
    Knight knight;


    public GameView(World w, int width, int height) {
        super(w, width, height);
    }

    public void setBack (Image background) {

        this.back = background;
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(back,0,0,940,460,this);

    }

    @Override
    protected void paintForeground (Graphics2D g) {
        g.setColor(Color.white);
        g.drawString("score:" + knight.getCoinCount(),26,80);

    }


}
