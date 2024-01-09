package main;

import entity.Entity;

public class CollisionChecker {
    
    private GamePanel gp;

    public CollisionChecker(GamePanel gp){

        this.gp = gp;
    }

    public void checkTile(Entity entity, KeyHandler keyH){
        
        int entityLeftWorldX = entity.getWorldX() + entity.getCollisionArea().x;
        int entityRightWorldX = entityLeftWorldX + entity.getCollisionArea().width;
        int entityTopWorldY = entity.getWorldY() + entity.getCollisionArea().y;
        int entityBottomWorldY = entityTopWorldY + entity.getCollisionArea().height;

        int entityLeftCol = entityLeftWorldX/gp.getTileSize();
        int entityRightCol = entityRightWorldX/gp.getTileSize();
        int entityTopRow = entityTopWorldY/gp.getTileSize();
        int entityBottomRow = entityBottomWorldY/gp.getTileSize();

        int tileNum1, tileNum2;

        if (keyH.getUpPressed() == 1) {
            entityTopRow = (entityTopWorldY - entity.getSpeed())/gp.getTileSize();
            tileNum1 = gp.getTileM().getCurrentMapTileNum()[entityTopRow][entityLeftCol];
            tileNum2 = gp.getTileM().getCurrentMapTileNum()[entityTopRow][entityRightCol];
            if (gp.getTileM().getTile()[tileNum1].isCollisionPossible() || gp.getTileM().getTile()[tileNum2].isCollisionPossible()) {
                entity.setCollisionOn(true);
            }
        }
        
        if (keyH.getDownPressed() == 1) {
            entityBottomRow = (entityBottomWorldY + entity.getSpeed())/gp.getTileSize();
            tileNum1 = gp.getTileM().getCurrentMapTileNum()[entityBottomRow][entityLeftCol];
            tileNum2 = gp.getTileM().getCurrentMapTileNum()[entityBottomRow][entityRightCol];
            if (gp.getTileM().getTile()[tileNum1].isCollisionPossible() || gp.getTileM().getTile()[tileNum2].isCollisionPossible()) {
                entity.setCollisionOn(true);
            }
        }

        if (keyH.getLeftPressed() == 1) {
            entityLeftCol = (entityLeftWorldX - entity.getSpeed())/gp.getTileSize();
            tileNum1 = gp.getTileM().getCurrentMapTileNum()[entityTopRow][entityLeftCol];
            tileNum2 = gp.getTileM().getCurrentMapTileNum()[entityBottomRow][entityLeftCol];
            if (gp.getTileM().getTile()[tileNum1].isCollisionPossible() || gp.getTileM().getTile()[tileNum2].isCollisionPossible()) {
                entity.setCollisionOn(true);
            }
        }

        if (keyH.getRightPressed() == 1) {
            entityRightCol = (entityRightWorldX + entity.getSpeed())/gp.getTileSize();
            tileNum1 = gp.getTileM().getCurrentMapTileNum()[entityTopRow][entityRightCol];
            tileNum2 = gp.getTileM().getCurrentMapTileNum()[entityBottomRow][entityRightCol];
            if (gp.getTileM().getTile()[tileNum1].isCollisionPossible() || gp.getTileM().getTile()[tileNum2].isCollisionPossible()) {
                entity.setCollisionOn(true);
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
