package uet.oop.bomberman.entities.Animated_Entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.peer.ScrollbarPeer;
import java.util.Scanner;

public class Ghost extends Enemy{
    public Ghost(int x, int y, Image img) {
        super(x, y, img);
        velocity = 1;
    }

    @Override
    public void chooseDir() {
        if(BombermanGame.bomber.getX() / Sprite.SCALED_SIZE - x / Sprite.SCALED_SIZE < 0) dir = 0;
        if (BombermanGame.bomber.getX() / Sprite.SCALED_SIZE - x / Sprite.SCALED_SIZE > 0) dir = 1;
        if (BombermanGame.bomber.getY() / Sprite.SCALED_SIZE - y / Sprite.SCALED_SIZE < 0) dir = 2;
        if (BombermanGame.bomber.getY() / Sprite.SCALED_SIZE - y / Sprite.SCALED_SIZE > 0) dir = 3;
    }

    @Override
    public void calMove() {
        if (newX / Sprite.SCALED_SIZE == 0 || newX / Sprite.SCALED_SIZE == 29
                || newY / Sprite.SCALED_SIZE == 0 || newY / Sprite.SCALED_SIZE == 11
                || newX / Sprite.SCALED_SIZE == (BombermanGame.bomber.getNewX() / Sprite.SCALED_SIZE) - 1
                || newY / Sprite.SCALED_SIZE == (BombermanGame.bomber.getNewY() / Sprite.SCALED_SIZE) - 1) {
            stay();
        }
        if (dir == 0) moveLeft();
        if (dir == 1) moveRight();
        if (dir == 2) moveUp();
        if (dir == 3) moveDown();
    }
    public void spriteUp() {
        img = Sprite.movingSprite(Sprite.ghost_left1, Sprite.ghost_left2, Sprite.ghost_left3, up++, 60).getFxImage();
    }

    public void spriteDown() {
        img = Sprite.movingSprite(Sprite.ghost_right1, Sprite.ghost_right2, Sprite.ghost_right3, down++, 60).getFxImage();
    }

    public void spriteLeft() {
        img = Sprite.movingSprite(Sprite.ghost_left1, Sprite.ghost_left2, Sprite.ghost_left3, left++, 60).getFxImage();
    }

    public void spriteRight() {
        img = Sprite.movingSprite(Sprite.ghost_right1, Sprite.ghost_right2, Sprite.ghost_right3, right++, 60).getFxImage();
    }
}

