package game;

import city.cs.engine.*;


public class Door extends StaticBody {

    private static final Shape doorShape = new PolygonShape(    //determines the shape
            1.64f,-2.26f,
            -1.8f,-2.31f,
            -1.82f,1.32f,
            -0.19f,2.47f,
            1.54f,1.8f,
            1.66f,-2.21f);

    private static final BodyImage image =
            new BodyImage("data/door.png", 5f);  //image for door


    public Door(World w) {
        super(w, doorShape);
        addImage(image);
    }


}