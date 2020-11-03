package com.company;

import UI.SnakeGame;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SnakeGame game = new SnakeGame();
            game.setVisible(true);
        });


    }
}
