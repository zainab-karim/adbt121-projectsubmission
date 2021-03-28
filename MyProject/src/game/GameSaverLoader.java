package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;
import jdk.dynalink.beans.StaticClass;
import org.jbox2d.common.Vec2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameSaverLoader {


    public static void save (GameLevel level, String fileName)
            throws IOException
    {
        boolean append = false;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, append);

            writer.write(level.getLevelName()  + "\n");

            for (StaticBody body : level.getStaticBodies()) {
                //not worth saving
            }

            for (DynamicBody body : level.getDynamicBodies()) {
                if (body instanceof Knight) {
                    writer.write("Knight," + body.getPosition().x + "," +
                            body.getPosition().y + "," +
                            ((Knight) body).getCoinCount() + "\n");
                }
                else if (body instanceof Dragon) {
                    writer.write("Dragon," + body.getPosition().x + "," +
                            body.getPosition().y + "\n");

                }
                else if (body instanceof Coin) {
                    writer.write("Coin," + body.getPosition().x + "," +
                            body.getPosition().y + "\n");
                }
            }

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static GameLevel load(Game game, String fileName)
            throws IOException
    {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();

            GameLevel level = null;
            if (line.equals("Level1"))
                level = new Level1(game);
            else if (line.equals("Level2"))
                level = new Level2(game);
            else if (line.equals("Level3"))
                level = new Level3(game);

            line = reader.readLine();
            while (line != null) {


                String[] tokens = line.split(",");

                if(tokens[0].equals("Knight")){
                    Knight k = new Knight(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    k.setPosition(new Vec2(x,y));
                    int cc = Integer.parseInt(tokens[3]);
                    k.setCoinCount(cc);

                    level.setKnight(k);
                    k.addCollisionListener(new CatchCoin(k));
                    DoorListener listener = new DoorListener(level, game);
                    k.addCollisionListener(listener);



                }else if(tokens[0].equals("Dragon")){
                    Knight k = new Knight(level);
                    Dragon d = new Dragon(level);
                    Health h = new Health(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    d.setPosition(new Vec2(x,y));
                    h.setPosition(new Vec2(-21,9));
                    k.setPosition(new Vec2(-55,-55));

                    level.setDragon(d);
                    level.setHealth(h);


                    DragonCollide Knight = new DragonCollide(d, k, h);
                    d.addCollisionListener(Knight);


                }else if(tokens[0].equals("Coin")){
                    Coin c = new Coin (level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    c.setPosition(new Vec2(x,y));



                }


                line = reader.readLine();
            }


            return level;



        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }

}
