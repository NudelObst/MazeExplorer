package entity;

import main.KeyHandler;

import java.awt.Color;
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
    
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    Map<String, Image> imageByString;
    String imageToBePainted;

    public Player(GamePanel gp, KeyHandler keyH){
        
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        collisionArea = new Rectangle(12, 24,21,18);
        
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        speed = 4;
        direction = "down";
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

            up0 = ImageIO.read(f0);
            up1 = ImageIO.read(f1);
            up2 = ImageIO.read(f2);
            down0 = ImageIO.read(f3);
            down1 = ImageIO.read(f4);
            down2 = ImageIO.read(f5);
            left0 = ImageIO.read(f6);
            left1 = ImageIO.read(f7);
            left2 = ImageIO.read(f8);
            right0 = ImageIO.read(f9);
            right1 = ImageIO.read(f10);
            right2 = ImageIO.read(f11);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageByString = new HashMap<String, Image>();
        imageByString.put("down0", down0);
        imageByString.put("down1", down1);
        imageByString.put("down2", down2);
        imageByString.put("up0", up0);
        imageByString.put("up1", up1);
        imageByString.put("up2", up2);
        imageByString.put("left0", left0);
        imageByString.put("left1", left1);
        imageByString.put("left2", left2);
        imageByString.put("right0", right0);
        imageByString.put("right1", right1);
        imageByString.put("right2", right2);
    }

    public void update(){
        
        int dx = speed * keyH.rightPressed - speed * keyH.leftPressed;
        int dy = speed * keyH.downPressed - speed * keyH.upPressed;
        
        if (dx != 0 || dy != 0) {
            
            direction = (dx !=0)? ((dx <0)? "left" : "right") : ((dy <0)? "up" : "down");
            
            //Check Collision
            collisionOn = false;
            gp.cChecker.checkTile(this, keyH);
            
            if (!collisionOn) { 
                
                worldX += dx;   //Diagonale schneller durch gleichzeitiges drücken oder gleich schnell
                worldY += dy;  
                
                spriteCounter++;
                if (spriteCounter > 20 && spriteNum == 1) {
                    spriteNum = 2;
                    spriteCounter = 0;
                } else if(spriteCounter > 20 && spriteNum == 2) {
                    spriteNum = 1;
                    spriteCounter = 0;
                }
                
                imageToBePainted = direction + spriteNum;
                return;
            }
        }
        
        imageToBePainted = direction + "0";

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

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
