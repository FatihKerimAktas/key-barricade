package gameobject;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Wall extends GameObject {

    public Wall() {
        ImageIcon icon = new ImageIcon("images/wall.jpg");
        Image image = icon.getImage();
        setGameObjectIcon(image);
    }

    @Override
    public boolean canMoveTo() {
        return false;
    }

}