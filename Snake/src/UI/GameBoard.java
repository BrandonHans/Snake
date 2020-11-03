package UI;

import Coordinate.Coordinate;
import Snake.Snake;
import com.company.Animation;
import com.company.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

class MyPoint extends Point {
    public MyPoint(int x, int y) {
        super(x, y);
    }

    public int deltaX = 0;
    public int deltaY = 0;

}

public class GameBoard extends JPanel {
    private final int BOARD_WIDTH = 300;
    private final int BOARD_HEIGHT = 300;
    private final int SNAKE_SIZE = 10;
    private final int GRID_SIZE = 900;
    private final int START_POS = 50;


    private final int FPS = 60;
    private final int PERIOD = 1000 / FPS;

    private final MyPoint points[] = new MyPoint[GRID_SIZE];

    private boolean inGame = true;

    private Timer timer;

    private Snake snake;
    private Coordinate apple;
    private Animation anim;

    public GameBoard() {
        initGameBoard();
    }

    public void initGameBoard() {
        this.setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));

        initGame();

    }

    private void initGame() {
        snake = new Snake();
        apple = new Coordinate();
        anim = new Animation();


        //Make Grid
        for (int i = 0; i < GRID_SIZE; i++) {
            points[i] = new MyPoint(0, 0);
        }

        //Make Snake
        for (int k = 0; k < snake.size(); k++) {
            points[k] = new MyPoint(50 - k * SNAKE_SIZE, 50);
        }

        //Generate Apple
        findApple();

        timer = new Timer(PERIOD, new GameCycle());
        timer.start();


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        draw(g);


    }

    private void draw(Graphics g) {
        if (inGame) {
            drawApple(g);
            drawSnake(g);
            Toolkit.getDefaultToolkit().sync();

        } else {
            gameOver(g);
        }
    }

    private void drawSnake(Graphics g) {
        for (int i = 0; i < snake.size(); i++) {
            if (i == 0) {
                g.drawImage(snake.getHead(), points[i].x, points[i].y, this);
            } else {
                g.drawImage(snake.getBody(), points[i].x, points[i].y, this);
            }
        }
    }

    private void drawApple(Graphics g) {
        g.drawImage(apple.getApple(), apple.getX(), apple.getY(), this);

    }

    private void gameOver(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        String gameOverMsg = "Game Over!";
        Font smFont = new Font("Consolas", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(smFont);

        g2d.setColor(Color.white);
        g2d.setFont(smFont);
        g2d.drawString(gameOverMsg, (BOARD_WIDTH - fm.stringWidth(gameOverMsg)) / 2, BOARD_HEIGHT / 2);


    }

    private void checkApple() {
        if (points[0].x == apple.getX() && points[0].y == apple.getY()) {
            Point lastPoint = points[snake.size() - 1];

        }

        snake.grow();
        findApple();
    }

    private void move() {
        if (anim.finished()) {
            anim.resetFrames();

            if (snake.getDirection() == Direction.RIGHT) {
                points[0].deltaX = 1;
                points[0].deltaY = 0;
            }
            if (snake.getDirection() == Direction.LEFT) {
                points[0].deltaX = -1;
                points[0].deltaY = 0;
            }
            if (snake.getDirection() == Direction.UP) {
                points[0].deltaX = 0;
                points[0].deltaY = 1;
            }
            if (snake.getDirection() == Direction.DOWN) {
                points[0].deltaX = 0;
                points[0].deltaY = -1;

            }

            for (int i = 0; i < snake.size(); i++) {
                if (points[i + 1].x == points[i].x) {
                    points[i + 1].deltaX = 0;

                } else if (points[i + 1].x > points[i].x) {
                    points[i + 1].deltaX = -1;

                } else {
                    points[i + 1].deltaX = 1;

                }

                if (points[i + 1].y == points[i].y) {
                    points[i + 1].deltaY = 0;

                } else if (points[i + 1].y > points[i].y) {
                    points[i + 1].deltaY = -1;

                } else {
                    points[i + 1].deltaY = 1;

                }
            }
        } else {
            anim.increaseFrame();

            for (int i = 0; i < snake.size(); i++) {
                points[i].translate(points[i].deltaX, points[i].deltaY);
            }
        }
    }

    private void checkCollision() {
        for (int i = snake.size(); i > 0; i--) {
            if (i > 4 && points[0].x == points[i].x && points[0].y == points[i].y) {
                inGame = false;

            }

        }

        if (points[0].y >= BOARD_HEIGHT) {
            inGame = false;
        }
        if (points[0].y < 0) {
            inGame = false;
        }
        if (points[0].x >= BOARD_WIDTH) {
            inGame = false;
        }
        if (points[0].x < BOARD_WIDTH) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    private void findApple() {
        Random rand = new Random();
        int r = rand.nextInt(START_POS);
        apple.setX(r * SNAKE_SIZE);

        r = rand.nextInt(START_POS);
        apple.setY(r * SNAKE_SIZE);
    }

    private void gameCycle() {
        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();

            if(key == KeyEvent.VK_LEFT && snake.getDirection() != Direction.LEFT){
                snake.setDirection(Direction.RIGHT);

            }
            if(key == KeyEvent.VK_RIGHT && snake.getDirection() != Direction.RIGHT){
                snake.setDirection(Direction.LEFT);

            }
            if(key == KeyEvent.VK_DOWN && snake.getDirection() != Direction.DOWN){
                snake.setDirection(Direction.UP);

            }
            if(key == KeyEvent.VK_UP && snake.getDirection() != Direction.UP){
                snake.setDirection(Direction.DOWN);

            }

        }

    }

    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            initGame();
        }
    }


}
