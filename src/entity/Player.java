package entity;

import main.KeyHandler;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity{
    
    private GamePanel gp;
    private KeyHandler keyH;

    private final int screenX;
    private final int screenY;
    private Map<String, Image> imageByString;
    private String imageToBePainted;

    public Player(GamePanel gp, KeyHandler keyH){
        
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.getScreenWidth()/2 - (gp.getTileSize()/2);
        screenY = gp.getScreenHeight()/2 - (gp.getTileSize()/2);

        int collisionAreaX = (int) 12 * (gp.getScale()/3);
        int collisionAreaY = (int) 24 * (gp.getScale()/3);
        int collisionAreaWidth = (int) 23 * (gp.getScale()/3);
        int collisionAreaHeight = (int) 18 * (gp.getScale()/3);

        setCollisionArea(new Rectangle(collisionAreaX, collisionAreaY,collisionAreaWidth ,collisionAreaHeight));
        
        setDefaultValues();
        getPlayerImage();
    }

    
    public GamePanel getGp() {
        return gp;
    }


    public void setGp(GamePanel gp) {
        this.gp = gp;
    }


    public KeyHandler getKeyH() {
        return keyH;
    }


    public void setKeyH(KeyHandler keyH) {
        this.keyH = keyH;
    }


    public int getScreenX() {
        return screenX;
    }


    public int getScreenY() {
        return screenY;
    }


    public Map<String, Image> getImageByString() {
        return imageByString;
    }


    public void setImageByString(Map<String, Image> imageByString) {
        this.imageByString = imageByString;
    }


    public String getImageToBePainted() {
        return imageToBePainted;
    }


    public void setImageToBePainted(String imageToBePainted) {
        this.imageToBePainted = imageToBePainted;
    }


    public void setDefaultValues(){
        setSpeed(4, gp);
        setDirection("down");
    }


    public void getPlayerImage(){        
        
        File f0 = new File("./res/player/boy_up_0.png");
        File f1 = new File("./res/player/boy_up_1.png");
        File f2 = new File("./res/player/boy_up_2.png");
        File f3 = new File("./res/player/boy_down_0.png");
        File f4 = new File("./res/player/boy_down_1.png");
        File f5 = new File("./res/player/boy_down_2.png");
        File f6 = new File("./res/player/boy_left_0.png");
        File f7 = new File("./res/player/boy_left_1.png");
        File f8 = new File("./res/player/boy_left_2.png");
        File f9 = new File("./res/player/boy_right_0.png");
        File f10 = new File("./res/player/boy_right_1.png");
        File f11= new File("./res/player/boy_right_2.png");
        
        try {

            setUp0(ImageIO.read(f0));
            setUp1(ImageIO.read(f1));
            setUp2(ImageIO.read(f2));
            setDown0(ImageIO.read(f3));
            setDown1(ImageIO.read(f4));
            setDown2(ImageIO.read(f5));
            setLeft0(ImageIO.read(f6));
            setLeft1(ImageIO.read(f7));
            setLeft2(ImageIO.read(f8));
            setRight0(ImageIO.read(f9));
            setRight1(ImageIO.read(f10));
            setRight2(ImageIO.read(f11));
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageByString = new HashMap<String, Image>();
        imageByString.put("down0", getDown0());
        imageByString.put("down1", getDown1());
        imageByString.put("down2", getDown2());
        imageByString.put("up0", getUp0());
        imageByString.put("up1", getUp1());
        imageByString.put("up2", getUp2());
        imageByString.put("left0", getLeft0());
        imageByString.put("left1", getLeft1());
        imageByString.put("left2", getLeft2());
        imageByString.put("right0", getRight0());
        imageByString.put("right1", getRight1());
        imageByString.put("right2", getRight2());
    }

    public void update(){
        
        int dx = getSpeed() * keyH.getRightPressed() - getSpeed() * keyH.getLeftPressed();
        int dy = getSpeed() * keyH.getDownPressed() - getSpeed() * keyH.getUpPressed();
        
        if (dx != 0 || dy != 0) {
            
            setDirection((dx !=0)? ((dx <0)? "left" : "right") : ((dy <0)? "up" : "down"));
            
            //Check Collision
            setCollisionOn(false);
            gp.getcChecker().checkTile(this, keyH);
            
            if (!isCollisionOn()) { 
                
                /* int temp = getWorldX() + dx;
                setWorldX(temp); */
                setWorldX(getWorldX() + dx);//Diagonale schneller durch gleichzeitiges drücken oder gleich schnell
                setWorldY(getWorldY() + dy); 
                
                setSpriteCounter(getSpriteCounter() + 1);
                if (getSpriteCounter() > 20 && getSpriteNum() == 1) {
                    setSpriteNum(2);
                    setSpriteCounter(0);
                } else if(getSpriteCounter() > 20 && getSpriteNum() == 2) {
                    setSpriteNum(1);
                    setSpriteCounter(0);
                }
                
                imageToBePainted = getDirection() + getSpriteNum();
                return;
            }
        }
        
        imageToBePainted = getDirection() + "0";

        /* if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            if(keyH.upPressed == true){
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            //Check Collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            if (collisionOn) { return; }

            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }

            spriteCounter++;
            if (spriteCounter > 15) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            } 
        } */
    }

    public void draw(Graphics2D g2){
        BufferedImage image = (BufferedImage) imageByString.get(imageToBePainted);

        /* switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break; 
        }*/

        g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
    }
}



//ökajhgoahüoihsgüa
