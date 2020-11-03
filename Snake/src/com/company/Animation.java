package com.company;

public class Animation {
    private int frames = 0;

    public void increaseFrame() {
        frames++;
    }

    public void resetFrames() {
        frames = 0;
    }

    public boolean finished() {
        int NUMBER_OF_ANIMATION_FRAMES = 10;
        return frames == NUMBER_OF_ANIMATION_FRAMES;
    }
}
