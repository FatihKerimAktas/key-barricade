package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class GameKey extends GameObject {

    int gameKeyNumber;

    public GameKey(int gameKeyNumber) {
        ImageIcon icon = new ImageIcon("images/gamekey.png");
        Image image = icon.getImage();
        setGameObjectIcon(image);

        this.gameKeyNumber = gameKeyNumber;
    }

    public int getGameKeyPin(){
        return this.gameKeyNumber;
    }

    @Override
    public void draw(Graphics g, int screenX, int screenY) {
        String pinNumber = Integer.toString(this.gameKeyNumber);
        g.setColor(Color.WHITE);
        g.drawString(pinNumber, screenX, screenY);
    }

}