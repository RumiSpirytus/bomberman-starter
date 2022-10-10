package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;
    protected boolean alive = true;
    protected Image img;
    protected int layer;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public Entity(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getLayer() { return layer; }
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();
    public boolean isAlive(){
        return this.alive;
    }

    /**
     * check va cham.
     */
    public abstract boolean collide(Entity e);
    public Rectangle bound(){
        return new Rectangle(x,y,Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }
    public Rectangle bound(int a, int b){
        return new Rectangle(a,b,Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

}
