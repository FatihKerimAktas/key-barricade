package menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import game.PlayGround;
import menu.LevelSelect;
import static menu.LevelSelect.levelNumber;
import static menu.LevelSelect.levelchoice;

public class MainMenu {

    public static void create_mainmenu() {

        JFrame menu = new JFrame("Sleutel barricade");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("Sleutel barricade");

        JButton btnBegin = new JButton("begin");
        JButton btnLevelselect = new JButton("levels");
        JButton btnCredits = new JButton("Credits");

        menu.add(title);
        menu.add(btnBegin);
        menu.add(btnLevelselect);
        menu.add(btnCredits);

        title.setLocation(250, 100);
        btnBegin.setLocation(250, 300);
        btnLevelselect.setLocation(250, 400);
        btnCredits.setLocation(250, 500);

        title.setSize(300, 075);
        btnBegin.setSize(100, 025);
        btnLevelselect.setSize(100, 025);
        btnCredits.setSize(100, 025);

        JLabel emptyLabel = new JLabel("");

        emptyLabel.setPreferredSize(new Dimension(600, 650));

        menu.getContentPane().add(emptyLabel, BorderLayout.CENTER);

        btnBegin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelNumber = 1;
                levelchoice = "levels/level" + levelNumber;
                PlayGround.setupPlayGround();
                menu.setVisible(false);
            }
        });

        btnLevelselect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LevelSelect.setupLevelSelect();
                menu.setVisible(false);
            }
        });

        menu.pack();
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
    }
}

