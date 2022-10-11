package uet.oop.bomberman.entities.Animated_Entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class AnimatedEntities extends Entity {
    protected int newX = x;
    protected int newY = y;
    protected int velocity;
    protected int left = 0;
    protected int right = 0;
    protected int up = 0;
    protected int down = 0;

    public AnimatedEntities(int x, int y, Image img) {
        super(x, y, img);
    }

    /**
     * update toa do.
     */
    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }
    public void move(){
        x = newX;
        y = newY;
    }
    public void moveLeft(){
        newX = x - velocity;
    }
    public void moveRight(){
        newX = x + velocity;
    }
    public void moveUp(){
        newY = y + velocity;
    }
    public void moveDown(){
        newY = y - velocity;
    }
    public void stay(){
        newX = x;
        newY = y;
    }
    public int getNewX(){
        return newX;
    }
    public int getNewY(){
        return newY;
    }
}
