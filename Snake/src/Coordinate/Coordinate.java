package Coordinate;

import javax.swing.*;
import java.awt.*;

public class Coordinate {
    private int x, y;
    private Image apple;

    public Coordinate() {
        loadImage();
    }

    private void loadImage() {
        ImageIcon appleImage = new ImageIcon("C:\\Users\\brans\\Documents\\Programming\\IntelliJ\\Snake\\src\\com\\company\\apple.png");
        apple = appleImage.getImage();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getApple() {
        return apple;
    }
}
