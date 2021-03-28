package game;

import city.cs.engine.DebugViewer;
import city.cs.engine.SoundClip;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;



public class Game {


    private JFrame frame;

    /** The World in which the bodies move and interact. */
    private GameLevel level;

    /** A graphical display of the world (a specialised JPanel). */
    private GameView view;

    private KnightController knightController;
    private DragonController dragonController;

    private SoundClip backgroundMusic;

    private Knight knight;
    private Dragon dragon;

    JFrame window;
    Container con;
    JPanel titleNamePanel, startButtonPanel, quitButtonPanel;
    JLabel titleNameLabel;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 60);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 25);
    JButton startButton;
    JButton quitButton;


    /** Run the game. */

    public static void main(String[] args) {

        new Game();
    }


    /** Initialise a new Game. */
    public Game() {

        /** Makes the Game Screen */

        try {
            backgroundMusic = new SoundClip("data/backgroundmusic.mp3");                                         // Open an audio input stream
            backgroundMusic.loop();                                                                                      // Set it to continuous playback (looping)
            backgroundMusic.setVolume(0.1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        window = new JFrame();
        try {
            window.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("data/gamescreen.jpeg")))));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        window.setSize(940, 460);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);

        window.setLayout(null);
        window.setVisible(true);
        con = window.getContentPane();


        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(120, 100, 750, 75);
        titleNamePanel.setBackground(Color.darkGray);
        titleNameLabel = new JLabel("MEDIEVAL ADVENTURES!");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(290, 310, 150, 50);
        startButtonPanel.setBackground(Color.lightGray);

        quitButtonPanel = new JPanel();
        quitButtonPanel.setBounds(530, 310, 150, 50);
        quitButtonPanel.setBackground(Color.lightGray);

        startButton = new JButton("START");   //start button
        startButton.setBackground(Color.white);
        startButton.setForeground(Color.black);
        startButton.setFont(normalFont);
        startButton.addActionListener(e -> createGameButton());


        quitButton = new JButton("QUIT");    //quit button
        quitButton.setBackground(Color.white);
        quitButton.setForeground(Color.black);
        quitButton.setFont(normalFont);
        quitButton.addActionListener(e -> System.exit(0));

        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);
        quitButtonPanel.add(quitButton);


        con.add(titleNamePanel);
        con.add(startButtonPanel);
        con.add(quitButtonPanel);


    }

    public void createGameButton() {
        window.dispose();                                                                                                //disposes of the game screen
        createGame();                                                                                                    //the game begins
    }

    public void createGame() {
        // makes the world
        level = new Level1(this);
        level.populate(this);

        // makes a view
        view = new GameView(level, 940, 460);
        view.setZoom(20);
        view.setBack(level.paintBackground());

        try {
            backgroundMusic = new SoundClip("data/backgroundmusic.mp3");                                         // Open an audio input stream
            backgroundMusic.loop();                                                                                      // Set it to continuous playback (looping)
            backgroundMusic.setVolume(0.1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        knightController = new KnightController(this);
        view.addKeyListener(knightController);

        dragonController = new DragonController(level.getDragon());
        view.addKeyListener(dragonController);

        view.addMouseListener(new MouseHandler(level, view));
        view.addMouseListener(new GiveFocus(view));


        // adds view to a frame (Java top level window)
        frame = new JFrame("Basic world");
        frame.add(view);
        // frame can quit the application when x is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ControlPanel buttons = new ControlPanel(this);
        frame.add(buttons.getMainPanel(), BorderLayout.WEST);


        frame.setLocationByPlatform(true);
        // frame can't be resized
        frame.setResizable(false);
        // adjusts size of frame to fit the world view
        frame.pack();
        // makes the frame visible
        frame.setVisible(true);


        // uncomment this to make a debugging view
        //JFrame debugView = new DebugViewer(level, 940, 460);

        level.start();                                                                                                   //starts the level

    }

    public GameLevel getLevel() {

        return level;
    }

    public void setLevel(GameLevel level) {
        this.level.stop();                                                                                               //stop the current level
        this.level = level;                                                                                              //level now refers to new level
        JFrame debugView = new DebugViewer(level, 940, 460);
        //change the view to look into new level
        //level.populate(this);
        view.setWorld(this.level);
        view.setBack(this.level.paintBackground());
        knightController.updateKnight(this.level.getKnight());                                                           //knight in the new world
        dragonController.updateDragon(this.level.getDragon());                                                           //dragon in the new world
        this.level.start();                                                                                              //start the simulation in the new level
    }



    public void goToNextLevel(){

        if (level instanceof Level1){                                                                                    //if the level is level 1
            level.stop();                                                                                                //stop the current level
            //create the new (appropriate) level
            level = new Level2(this);                                                                              //level now refers to new level
            level.populate(this);
            //JFrame debugView = new DebugViewer(level, 940, 460);
            //change the view to look into new level
            //level.populate(this);
            view.setWorld(level);
            view.setBack(level.paintBackground());
            //knight in the new world
            //knightController.updateKnight(level.getKnight());
            dragonController.updateDragon(level.getDragon());                                                            //dragon in the new world
            level.start();                                                                                               //start the simulation in the new level
        }
        else if (level instanceof Level2){                                                                               //if the level is level 2
            level.stop();                                                                                                //stop the current level
            //create the new (appropriate) level
            level = new Level3(this);                                                                              //level now refers to new level
            level.populate(this);
            //JFrame debugView = new DebugViewer(level, 940, 460);
            //change the view to look into new level
            view.setWorld(level);
            view.setBack(level.paintBackground());
            //knight in the new world
            //knightController.updateKnight(level.getKnight());
            dragonController.updateDragon(level.getDragon());                                                            //dragon in the new world
            level.start();                                                                                               //start the simulation in the new level
        }
        else if (level instanceof Level3){                                                                               //if the level is level 3
            System.out.println("Well done! Game complete.");                                                             //prints a line
            frame.dispose();                                                                                             //disposes of the game frame
            JDialog diaScore = new JDialog(frame, true);
            HighScore highScore = new HighScore(this);
            diaScore.getContentPane().add(highScore.getPnlScores());                                                     //allows user to add their score
            diaScore.pack();
            diaScore.setVisible(true);
        }

    }

    public void save() {                                                                                                 //allows the game to be saved using a button
        try {
            GameSaverLoader.save(level, "data/save.txt");
            System.out.println("Saved using button");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public GameLevel load() {                                                                                            //allows the game to be loaded using a button
        try {
            GameLevel level = GameSaverLoader.load(this, "data/save.txt");
            this.setLevel(level);
            System.out.println("Loaded using button");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return level;
    }


    public void pause(){                                                                                                 //allows the game to be paused using a button

        level.stop();
    }

    public void start(){                                                                                                 //allows the game to be started using a button

        level.start();
    }

    public boolean isComplete() {

        return true;
    }


    public Knight getKnight() {

        return knight;
    }

    public Dragon getDragon() {

        return dragon;
    }


}
