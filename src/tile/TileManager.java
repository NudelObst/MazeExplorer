package tile;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    
    private GamePanel gp;
    private Tile[] tile;
    private int[][] currentMapTileNum;
    private Map currentMap;

    public TileManager(GamePanel gp){

        this.gp = gp;
        currentMap = new Map(gp);
        currentMapTileNum = currentMap.getMapTileNum();

        tile = new Tile[10];

        getTileImage();
    }

    
    public GamePanel getGp() {
        return gp;
    }


    public void setGp(GamePanel gp) {
        this.gp = gp;
    }


    public Tile[] getTile() {
        return tile;
    }


    public void setTile(Tile[] tile) {
        this.tile = tile;
    }


    public int[][] getCurrentMapTileNum() {
        return currentMapTileNum;
    }


    public void setCurrentMapTileNum(int[][] currentMapTileNum) {
        this.currentMapTileNum = currentMapTileNum;
    }


    public Map getCurrentMap() {
        return currentMap;
    }


    public void setCurrentMap(Map currentMap) {
        this.currentMap = currentMap;
    }


    public void getTileImage(){

        try {

            tile[0] = new Tile();
            File f1 = new File("./res/tiles/earth.png");
            tile[0].setImage(ImageIO.read(f1));

            tile[1] = new Tile();
            File f2 = new File("./res/tiles/wall.png");
            tile[1].setImage(ImageIO.read(f2));
            tile[1].setCollisionPossible(true);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void draw(Graphics2D g2){
        
        for (int i = 0; i < currentMap.getMaxWorldRow(); i++) {
            for (int j = 0; j < currentMap.getMaxWorldCol(); j++) {  

                int worldX = j * gp.getTileSize();
                int worldY = i * gp.getTileSize();
                int screenX = worldX + gp.getPlayer().getScreenX() - gp.getPlayer().getWorldX();
                int screenY = worldY + gp.getPlayer().getScreenY() - gp.getPlayer().getWorldY();

                if (worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                    worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                    worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                    worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {

                    g2.drawImage(tile[getCurrentMapTileNum()[i][j]].getImage(), screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
                }
            }
        }
    }
}
