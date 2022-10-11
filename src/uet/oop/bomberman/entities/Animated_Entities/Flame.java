package uet.oop.bomberman.entities.Animated_Entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;

public class Flame extends Entity {
    @Override
    public void update() {
        collideCheck();
    }
    public void flameExplode(){
        // 4 void trai phai tren duoi.
//        Right();
//        Left();
//        Top();
//        Down();
//        createFlame();
    }
    // 4 void trai phai tren duoi.
    // createFlame.
    // meet wall, brick.
    //collide(Entity e) de check va cham khoi.
    private void collideCheck() {
        for(int i = 0; i< BombermanGame.Objects.size(); i++){
            this.collide(BombermanGame.Objects.get(i));
        }
        for(int i = 0; i<BombermanGame.enemies.size(); i++){
            this.collide(BombermanGame.enemies.get(i));
        }
        for(int i = 0; i<Bomber.bombs.size(); i++){
            this.collide(Bomber.bombs.get(i));
        }
        this.collide(BombermanGame.bomber);
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }

    private String direction;
    private int radius;
    private int right;
    private int left;
    private int top;
    private int down;
    private int timeToDisappear = 20;
    private int timeToAnimate = 0;

    public Flame(int x, int y, Image img, String direction){
        super(x,y,img);
        this.direction = direction;
    }
    public Flame(int x, int y){
        super(x,y);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
