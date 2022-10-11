package uet.oop.bomberman.entities.Animated_Entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated_Entities.AnimatedEntities;
import uet.oop.bomberman.entities.Animated_Entities.Bomb;
import uet.oop.bomberman.entities.Animated_Entities.Bomber;
import uet.oop.bomberman.entities.Animated_Entities.Flame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Static_Entities.Brick;
import uet.oop.bomberman.entities.Static_Entities.Wall;
import uet.oop.bomberman.graphics.Sprite;
import java.awt.*;
public abstract class Enemy extends AnimatedEntities {
    protected int dir; // direction
    protected int animate = 0;
    protected int _x;
    protected int _y;
    protected int timeToDisappear = 60;
    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }

    /**
     * check collide voi tung doi tuong, bomber, wall, brick, enemy.
     * intersects tra ve true neu 2 phuong thuc giao nhau va false cho cai con lai.
     */
    public boolean collide(Entity e){
        if(e instanceof Flame){
            if(this.alive) {
                //sound
                //enemyDead.play();
            } this.alive = false;
            return true;
        }
        if(e.bound().intersects(this.bound()) && e instanceof Bomber){
            return e.collide(this);
        }
        if(e instanceof Wall || e instanceof Brick){
            return e.collide(this);
        }
        if(e instanceof Enemy){
            return false;
        }
        return true;
    }

    /**
     * ham bound la tra ve vung bao quanh hcn.
     */
    public Rectangle bound(){
        return new Rectangle(newX, newY, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }
    /**
     * quay lai check va cham bomber cua bomberman.
     */
    public void collideCheck(){
        this.collide(BombermanGame.bomber);
    }
    public abstract void spriteLeft();
    public abstract void spriteRight();
    public abstract void spriteUp();
    public abstract void spriteDown();
    public void update(){
        if(!isAlive()){
            timeToDisappear--;
            img = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2,
                    Sprite.mob_dead3, animate++, 100 ).getFxImage();
        } else {
            collideCheck();
            if(dir == 0){
                moveLeft();
                spriteLeft();
            }
            if(dir == 1){
                moveRight();
                spriteRight();
            }
            if(dir == 2){
                moveUp();
                spriteUp();
            }
            if(dir == 3){
                moveDown();
                spriteDown();
            }
            calMove();
        }
        if(timeToDisappear == 0) BombermanGame.enemies.remove(this);
    }
    public void stay(){
        super.stay();
        chooseDir();
    }
    public void calMove() {
        for (Enemy e : BombermanGame.enemies) {
            for (Bomb o : Bomber.bombs) {
                if (e.bound().intersects(o.bound())) {
                    e.stay();
                }
            }
            for (Enemy o : BombermanGame.enemies) {
                if (e.equals(o)) continue;
                if (e instanceof Ghost || o instanceof Ghost) continue;
                if (e.bound().intersects(o.bound())) {
                    if (e.collide(o)) {
                        e.move();
                    } else {
                        e.stay();
                    }
                }
            }
        }
    }
    public abstract void chooseDir();
//    public abstract void spriteRight();
//    public abstract void spriteLeft();
//    public abstract void spriteUp();
//    public abstract void spriteDown();

    /**
     * tranh va cham.
     */
    public void tranh_xa(){
        super.stay();
        x = _x * Sprite.SCALED_SIZE;
        y = _y * Sprite.SCALED_SIZE;
    }

}
