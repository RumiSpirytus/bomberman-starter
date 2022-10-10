package uet.oop.bomberman.entities.Animated_Entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Sound_Bomberman.Sound;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends AnimatedEntities{

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }
    int timeToExplode = 120;
    private int radius;
    int animate = 0;

    public Bomb(int x, int y, Image img, int radius){
        super(x,y,img);
        layer = 2;
        this.radius = radius;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void update(){
        timeToExplode--;
        if(timeToExplode < 0 ){
            explode();
        }
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1,
                Sprite.bomb_2, animate++, 52).getFxImage();
    }
    public void explode(){
        Flame flame = new Flame(x,y);
        // Sound
        flame.setRadius(radius);
        flame.flameExplode();
        alive = false;
    }
    public boolean collide(Entity e) {
        if (e instanceof Flame) {
            this.timeToExplode = 0;
        }
        return true;
    }
}
