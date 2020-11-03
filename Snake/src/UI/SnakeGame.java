package UI;

import javax.swing.*;

public class SnakeGame extends JFrame {
    public SnakeGame() {
        initUI();

    }

    private void initUI() {
        add(new GameBoard());
        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
