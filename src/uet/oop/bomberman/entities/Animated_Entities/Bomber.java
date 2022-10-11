package uet.oop.bomberman.entities.Animated_Entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends AnimatedEntities {
    private KeyCode dir;
    private int bombRemain;
    public static List<Bomb> bombs = new ArrayList<>();
    public int timeBetweenNewBombs;
    private int radius;
    private boolean bombSet = false;
    private int animate = 0;
    private boolean soundPlayed = false;
    public int cntStep = 0;
    private int timeToDisappear = 40;
    public void playSoundDead(){};
    public List<Bomb> getBombs(){
        return bombs;
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        layer = 1;
        velocity = 2;
        bombRemain = 1;
        radius = 1;
    }

    @Override
    public void update() {
        if(!isAlive()) {
            if (timeToDisappear > 0) {
                if (!soundPlayed) playSoundDead();
                timeToDisappear--;
                img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2
                        , Sprite.player_dead3, animate++, 60).getFxImage();
            } else {
                BombermanGame.bomber = new Bomber(1, 1, Sprite.player_left.getFxImage());
            }
        } else {
                if(dir == KeyCode.LEFT){
                    moveLeft();
                    img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1
                    ,Sprite.player_left_2, left++, 20).getFxImage();
                }
                if (dir == KeyCode.RIGHT) {
                    moveRight();
                    img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, right++, 20).getFxImage();
                }
                if (dir == KeyCode.UP) {
                    moveUp();
                    img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, up++, 20).getFxImage();
                }
                if (dir == KeyCode.DOWN) {
                    moveDown();
                    img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, down++, 20).getFxImage();
                }
                if(dir != null){
                    cntStep++;
                    if(cntStep % 10 == 0){
                        //Sound
                        if(cntStep > 1000) cntStep =0;
                    }
                }
                calMove();
            }
        if (timeBetweenNewBombs < -10000) timeBetweenNewBombs = 0;
        else timeBetweenNewBombs--;
        appearPlaceBomb();
        checkBomb();
        }
    public void KeyPressedEvent(KeyCode keyCode) {
        if (keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT
                || keyCode == KeyCode.UP || keyCode == KeyCode.DOWN) {
            dir = keyCode;
        }
        if (keyCode == KeyCode.SPACE) bombSet = true;
    }
    public void KeyReleasedEvent(KeyCode keyCode) {
        if (dir == keyCode) {
            if (dir == KeyCode.LEFT) {
                img = Sprite.player_left.getFxImage();
            }
            if (dir == KeyCode.RIGHT) {
                img = Sprite.player_right.getFxImage();
            }
            if (dir == KeyCode.UP) {
                img = Sprite.player_up.getFxImage();
            }
            if (dir == KeyCode.DOWN) {
                img = Sprite.player_down.getFxImage();
            }
        }
        dir = null;
        bombSet = false;
    }

    public void calMove(){
        for (int i = 0; i < BombermanGame.Objects.size(); i++) {
            if (BombermanGame.bomber.bound().intersects(BombermanGame.Objects.get(i).bound())) {
                if (BombermanGame.bomber.collide(BombermanGame.Objects.get(i))) {
                    BombermanGame.bomber.move();
                } else {
                    BombermanGame.bomber.stay();
                }
            }
        }
    }
    public int canvasToBomb(int x){
        return Math.round(x+10) / Sprite.SCALED_SIZE;
    }
    @Override
    public boolean collide(Entity e) {
        return false;
    }
    public void checkBomb(){
        for(int i = 0; i<bombs.size(); i++){
            Bomb bomb = bombs.get(i);
            if (!bomb.isAlive()) {
                bombs.remove(bomb);
                bombRemain++;
            }
        }
    }
    public void appearPlaceBomb(){
        if(bombSet && timeBetweenNewBombs < 0){
            placeBomb();
            timeToDisappear = 50;
        }
    }
    public void placeBomb(){
        if(bombRemain > 0 ){
            Bomb bomb = new Bomb(canvasToBomb(x), canvasToBomb(y), Sprite.bomb.getFxImage(), radius);
            for (Bomb b : bombs) {
                if (canvasToBomb(x) == b.getX() && canvasToBomb(y) == b.getY()) return;
            }
            //Sound
            bombRemain--;
            bombs.add(bomb);
        }
    }
}
