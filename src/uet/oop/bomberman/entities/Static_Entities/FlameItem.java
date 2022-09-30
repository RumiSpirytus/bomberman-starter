package uet.oop.bomberman.entities.Static_Entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class FlameItem extends Entity {

    public FlameItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean collide(Entity e) {
        // TODO Auto-generated method stub
        return false;
    }
}
