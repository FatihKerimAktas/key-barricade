package gameobject;

import java.awt.Graphics;
import java.awt.Image;

public abstract class GameObject {

    private Image icon;

    public abstract boolean canMoveTo();

    void setGameObjectIcon(Image iconI) {
        this.icon = iconI;
    }

    public Image getGameObjectIcon() {
        return this.icon;
    }

    public void draw(Graphics g, int screenX, int screenY) {}

}
