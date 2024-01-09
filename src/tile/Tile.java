package tile;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage image;
    private boolean collisionPossible = false;
    
    public Tile (){}


    public BufferedImage getImage() {
        return image;
    }


    public void setImage(BufferedImage image) {
        this.image = image;
    }


    public boolean isCollisionPossible() {
        return collisionPossible;
    }


    public void setCollisionPossible(boolean collisionPossible) {
        this.collisionPossible = collisionPossible;
    }

    

}