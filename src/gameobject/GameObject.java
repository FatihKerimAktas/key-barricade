package gameobject;

import java.awt.Graphics;
import java.awt.Image;

public abstract class GameObject {

    public Image icon;

    public boolean canMoveTo() {
        return true;
    }

    public void setGameObjectIcon(Image iconI) {
        this.icon = iconI;
    }

    public Image getGameObjectIcon() {
        return this.icon;
    }

    public void draw(Graphics g, int screenX, int screenY) {}

}
