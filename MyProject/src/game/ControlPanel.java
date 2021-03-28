package game;

import javax.swing.*;

public class ControlPanel extends javax.swing.JPanel{

    Game game;
    private JPanel mainPanel;
    private javax.swing.JButton startButton;
    private javax.swing.JButton pauseButton;
    private javax.swing.JButton quitButton;
    private JButton saveButton;
    private JButton loadButton;

    public ControlPanel(Game game) {
        this.game = game;
        quitButton.addActionListener(e -> System.exit(0));
        pauseButton.addActionListener(e -> game.pause());
        startButton.addActionListener(e -> game.start());
        saveButton.addActionListener(e -> game.save());
        loadButton.addActionListener(e -> game.load());
    }

    private void JButton1ActionPerformed(java.awt.event.ActionEvent evt){
        System.out.println("Pause");
        game.pause();
    }


    public JPanel getMainPanel() {

        return mainPanel;
    }

}