package menu;

import game.PlayGround;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

public class LevelSelect {

    private static int currentLevel = 0;
    private static int totalLevels = 0;
    private static String levelName;

    void setupLevelSelect() {
        File folder = new File("levels");
        File[] listOfFiles = folder.listFiles();

        JFrame lvlSelect = new JFrame("Key Barricade");
        lvlSelect.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Get all file names; e.g. level2
        List<String> list = new ArrayList<>();
        // TODO: Check if the if this if-statement is needed
        if(listOfFiles != null) {
            for (File listOfFile : listOfFiles) {
                String fileName = listOfFile.toString();
                list.add(fileName.substring(7, fileName.length() - 4));
                totalLevels = Integer.parseInt(fileName.substring(12, fileName.length() - 4));
            }
            Collections.sort(list);

            JButton[] levelButton = new JButton[listOfFiles.length];
            for (int i = 0; i < levelButton.length; i++) {
                levelButton[i] = new JButton(list.get(i));
            }

            for (JButton levelButtons : levelButton) {
                lvlSelect.add(levelButtons);
            }

            int buttonLocationX = 250;
            int buttonLocationY = 100;

            int buttonSizeX = 100;
            int buttonSizeY = 75;

            JLabel emptyLabel = new JLabel("");
            emptyLabel.setPreferredSize(new Dimension(600, 650));
            lvlSelect.getContentPane().add(emptyLabel, BorderLayout.CENTER);

            for (JButton b : levelButton) {
                b.setSize(buttonSizeX, buttonSizeY);
                b.setLocation(buttonLocationX, buttonLocationY);
                buttonLocationY += 100;

                b.addActionListener(e -> {
                    currentLevel = Integer.parseInt(b.getText().substring(5));
                    levelName =  b.getText();
                    PlayGround.setupPlayGround();
                    lvlSelect.setVisible(false);
                });
            }
        }

        lvlSelect.pack();
        lvlSelect.setVisible(true);
        lvlSelect.setLocationRelativeTo(null);
    }

    public static int getTotalLevels() {
        return totalLevels;
    }

    public static String getLevelName() {
        return levelName;
    }

    public static void setLevelName(String levelName) {
        LevelSelect.levelName = levelName;
    }

    public static int getCurrentLevel() {
        return currentLevel;
    }

    public static void setCurrentLevel(int currentLevel) {
        LevelSelect.currentLevel = currentLevel;
    }

}