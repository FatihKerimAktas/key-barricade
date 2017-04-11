package tile;

import gameobject.GameObject;
import player.Player;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Tile {

    final public static int ICON_WIDTH = 50;                                 // Set width of object
    final public static int ICON_HEIGHT = 50;                                // Set height of object

    final public static int BOARD_X_OFFSET = 10;
    final public static int BOARD_Y_OFFSET = 10;

    public int x;
    public int y;

    private Color color = Color.GRAY;

    private GameObject go;
    private Player player;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Get the 'Tile' X coordinate.
    public int getX() {
        return x;
    }

    // Get the 'Tile' Y coordinate.
    public int getY() {
        return y;
    }

    // Get the Screen X coordinate.
    private int getScreenX() {
        return BOARD_X_OFFSET + x * ICON_WIDTH;
    }

    private int getScreenY() {
        return BOARD_Y_OFFSET + y * ICON_HEIGHT;
    }

    public void setGameObject(GameObject go) {
        this.go = go;
    }

    public GameObject getGameObject() {
        return this.go;
    }

    void setColor(Color c) {
        this.color = c;
    }

    public void draw(Graphics g) {
        g.setColor(color);      // Set Tile

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1));   // Set border '1'
        g.fillRect(getScreenX(), getScreenY(), ICON_WIDTH - 1, ICON_HEIGHT - 1);

        if (go != null) {
            g.drawImage(go.getGameObjectIcon(), getScreenX(), getScreenY(), null);
            go.draw(g, getScreenX(), getScreenY() + 10);
        }
        if (player != null) {
            g.drawImage(player.getPlayerIcon(), getScreenX(), getScreenY(), null);
        }
    }

}
