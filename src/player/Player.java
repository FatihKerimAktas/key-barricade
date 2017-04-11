package player;

import game.PlayGround;
import menu.LevelSelect;
import static menu.LevelSelect.levelNumber;
import tile.EndTile;
import tile.Tile;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Player {

    private final PlayGround playGround;

    public int x;
    public int y;

    public Image playerUp;
    public Image playerRight;
    public Image playerLeft;
    public Image playerDown;
    public Image current;

    public static int levelwon = 1;

    public int keyPinPickedUp;

    public Player(PlayGround playGround, int x, int y) {   // Variable can be set from PlayGround
        this.playGround = playGround;
        this.x = x;
        this.y = y;
        setupPlayerPositionImage();
    }

    public void setupPlayerPositionImage() {
        ImageIcon imageDown = new ImageIcon("images/player_down.png");
        playerDown = imageDown.getImage();
        ImageIcon imageUp = new ImageIcon("images/player_up.png");
        playerUp = imageUp.getImage();
        ImageIcon imageRight = new ImageIcon("images/player_right.png");
        playerRight = imageRight.getImage();
        ImageIcon imageLeft = new ImageIcon("images/player_left.png");
        playerLeft = imageLeft.getImage();

        setPlayerIcon(this.playerRight);
    }

    public int getKeyPinPickedUp() {
        return keyPinPickedUp;
    }

    public void setKeyPinPickedUp(int keyPinPickedUp) {
        this.keyPinPickedUp = keyPinPickedUp;
    }

    // Tile Coordinate X
    public int getX() {
        return x;
    }

    // Tile Coordinate Y
    public int getY() {
        return y;
    }

    public int getScreenX() {
        return Tile.BOARD_X_OFFSET + x * Tile.ICON_WIDTH;
    }

    public int getScreenY() {
        return Tile.BOARD_Y_OFFSET + y * Tile.ICON_HEIGHT;
    }

    public void move(int x, int y) {

        // Get all tiles ( Tiles[0][0] - Tiles [9][9] )
        Tile movingTo = this.playGround.getTile(x, y);
        // If there is a tile && if there are gameObjects && canMoveTo is false
        if (movingTo != null && movingTo.getGameObject() != null && !movingTo.getGameObject().canMoveTo()) {
            return;  // do nothing
        }

        if (movingTo instanceof EndTile && levelwon > 0) {
            this.levelwon = 0;
            final JFrame finish = new JFrame("Feeling Proud?");
            JLabel finished = new JLabel("You have won the level");
            JButton nextLevel = new JButton("Next Level");
            finish.add(nextLevel);
            finish.add(finished);
            nextLevel.setLocation(100, 075);
            finished.setLocation(100, 0);
            nextLevel.setSize(200, 050);
            finished.setSize(400, 100);
            JLabel emptyLabel = new JLabel("");

            emptyLabel.setPreferredSize(new Dimension(400, 100));

            finish.getContentPane().add(emptyLabel, BorderLayout.CENTER);

            finish.pack();
            finish.setVisible(true);
            finish.setLocationRelativeTo(null);

            nextLevel.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (levelNumber < 5 && levelNumber > 0) {
                        levelwon = 1;
                        levelNumber = levelNumber + 1;
                        LevelSelect.levelchoice = "levels/level" + levelNumber;
                        finish.setVisible(false);
                        PlayGround.setupPlayGround();
                    }
                }
            });
        }

        this.x = x;
        this.y = y;
    }

    public void moveLeft() {
        if (x > 0) {
            move(x - 1, y);
            this.current = playerLeft;
        }
    }

    public void moveRight() {
        if (x < playGround.tiles.length - 1) {
            move(x + 1, y);
            this.current = playerRight;
        }
    }

    public void moveUp() {
        if (y > 0) {
            move(x, y - 1);
            this.current = playerUp;
        }
    }

    public void moveDown() {
        if (y < playGround.tiles.length - 1) {
            move(x, y + 1);
            this.current = playerDown;
        }
    }

    public void setPlayerIcon(Image iconI) {
        this.current = iconI;
    }

    public Image getPlayerIcon() {
        return this.current;
    }

    public void draw(Graphics g) {
        g.drawImage(current, getScreenX(), getScreenY(), null);
    }

}
