package main;

import entity.Entity;

public class CollisionChecker {
    
    GamePanel gp;

    public CollisionChecker(GamePanel gp){

        this.gp = gp;
    }

    public void checkTile(Entity entity, KeyHandler keyH){
        
        int entityLeftWorldX = entity.worldX + entity.collisionArea.x;
        int entityRightWorldX = entityLeftWorldX + entity.collisionArea.width;
        int entityTopWorldY = entity.worldY + entity.collisionArea.y;
        int entityBottomWorldY = entityTopWorldY + entity.collisionArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        if (keyH.upPressed == 1) {
            entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
            tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
        }
        
        if (keyH.downPressed == 1) {
            entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
            tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
        }

        if (keyH.leftPressed == 1) {
            entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
            tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
        }

        if (keyH.rightPressed == 1) {
            entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
            tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
        }

        /* switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            } */
    }
}
