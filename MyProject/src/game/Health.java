package game;

import city.cs.engine.*;

public class Health extends StaticBody {


    private static final Shape healthShape = new CircleShape(1);       //determines shape


     private static final BodyImage oneHeart =
            new BodyImage("data/1heart.png", 4f);             //image of one heart

    private static final BodyImage twoHearts =
            new BodyImage("data/2hearts.png", 4f);            //image of two hearts

    private static final BodyImage threeHearts =
            new BodyImage("data/3hearts.png", 4f);           //image of three hearts

    public Health(World w) {
        super(w, healthShape);
        addImage(threeHearts);
    }

    public void setOneHeart(){
        removeAllImages();
        addImage(oneHeart);
    }
    public void setTwoHearts(){
        removeAllImages();
        addImage(twoHearts);
    }

    public void setThreeHearts(){
        removeAllImages();
        addImage(threeHearts);
    }

}
