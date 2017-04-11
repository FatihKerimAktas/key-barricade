package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Barricade extends GameObject {

    private int barricadePin;

    public Barricade(int barricadeNumber) {
        ImageIcon icon = new ImageIcon("images/barricade.jpg");
        Image image = icon.getImage();
        setGameObjectIcon(image);

        this.barricadePin = barricadeNumber;
    }

    public int getBarricadePin(){
        return this.barricadePin;
    }

    @Override
    public boolean canMoveTo() {
        return false;
    }

    @Override
    public void draw(Graphics g, int screenX, int screenY) {
        String pinNumber = Integer.toString(this.barricadePin);
        g.setColor(Color.WHITE);
        g.drawString(pinNumber, screenX, screenY);
    }

}