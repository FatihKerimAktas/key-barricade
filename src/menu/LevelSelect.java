package menu;

import game.PlayGround;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import menu.MainMenu;

public class LevelSelect {

    public static int levelNumber = 0;
    public static String levelchoice;

    public static void setupLevelSelect() {
        JFrame lvlSelect = new JFrame("Sleutel Barricade");
        lvlSelect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton[] levelButton = new JButton[6];
        levelButton[0] = new JButton("Level1");
        levelButton[1] = new JButton("Level2");
        levelButton[2] = new JButton("Level3");
        levelButton[3] = new JButton("Level4");
        levelButton[4] = new JButton("Level5");
        levelButton[5] = new JButton("Back");

        for (JButton levelButtons : levelButton) {
            lvlSelect.add(levelButtons);
        }

        int buttinLocationX = 250;
        levelButton[0].setLocation(buttinLocationX, 125);
        levelButton[1].setLocation(buttinLocationX, 225);
        levelButton[2].setLocation(buttinLocationX, 325);
        levelButton[3].setLocation(buttinLocationX, 425);
        levelButton[4].setLocation(buttinLocationX, 525);
        levelButton[5].setLocation(buttinLocationX + 150, 325);

        int buttonSizeX = 100;
        int buttonSizeY = 75;
        levelButton[0].setSize(buttonSizeX, buttonSizeY);
        levelButton[1].setSize(buttonSizeX, buttonSizeY);
        levelButton[2].setSize(buttonSizeX, buttonSizeY);
        levelButton[3].setSize(buttonSizeX, buttonSizeY);
        levelButton[4].setSize(buttonSizeX, buttonSizeY);
        levelButton[5].setSize(buttonSizeX, buttonSizeY);

        JLabel emptyLabel = new JLabel("");

        emptyLabel.setPreferredSize(new Dimension(600, 650));

        lvlSelect.getContentPane().add(emptyLabel, BorderLayout.CENTER);

        // Level 1
        levelButton[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelNumber = 1;
                levelchoice = "levels/level" + levelNumber;
                PlayGround.setupPlayGround();
                lvlSelect.setVisible(false);
            }
        });

        // Level 2
        levelButton[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelNumber = 2;
                levelchoice = "levels/level" + levelNumber;
                PlayGround.setupPlayGround();
                lvlSelect.setVisible(false);
            }
        });

        // Level 3
        levelButton[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelNumber = 3;
                levelchoice = "levels/level" + levelNumber;
                PlayGround.setupPlayGround();
                lvlSelect.setVisible(false);
            }
        });

        // Level 4
        levelButton[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelNumber = 4;
                levelchoice = "levels/level" + levelNumber;
                PlayGround.setupPlayGround();
                lvlSelect.setVisible(false);
            }
        });

        // Level 5
        levelButton[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelNumber = 5;
                levelchoice = "levels/level" + levelNumber;
                PlayGround.setupPlayGround();
                lvlSelect.setVisible(false);
            }
        });

        // Back
        levelButton[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu.create_mainmenu();
                lvlSelect.setVisible(false);
            }
        });

        lvlSelect.pack();
        lvlSelect.setVisible(true);
        lvlSelect.setLocationRelativeTo(null);
    }
}
