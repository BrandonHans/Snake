package Snake;

import com.company.Direction;

import javax.swing.*;
import java.awt.*;

public class Snake {
    private Image head;
    private Image body;
    private int starterSnake = 3;
    private Direction direction = Direction.RIGHT;

    public Snake() {

    }

    private void moveSnake() {

    }

    private void initSnake() {
        loadImage();
    }

    private void loadImage() {
        ImageIcon headImage = new ImageIcon("C:\\Users\\brans\\Documents\\Programming\\IntelliJ\\Snake\\src\\com\\company\\snakehead.png");
        head = headImage.getImage();

        ImageIcon bodyImage = new ImageIcon("C:\\Users\\brans\\Documents\\Programming\\IntelliJ\\Snake\\src\\com\\company\\snakebody.png");
        body = bodyImage.getImage();

    }

    private void addTail() {

    }

    public int size() {
        return starterSnake;
    }

    public void grow() {
        starterSnake++;

    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Image getHead() {
        return head;
    }

    public void setHead(Image head) {
        this.head = head;
    }

    public Image getBody() {
        return body;
    }

    public void setBody(Image body) {
        this.body = body;
    }

    public int getStarterSnake() {
        return starterSnake;
    }

    public void setStarterSnake(int starterSnake) {
        this.starterSnake = starterSnake;
    }
}
