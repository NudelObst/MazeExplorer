package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];

        getTileImage();
        loadMap("./res/maps/WorldmapBackground.txt");
    }

    public void getTileImage(){

        try {

            tile[0] = new Tile();
            File f1 = new File("./res/tiles/earth.png");
            tile[0].image = ImageIO.read(f1);

            tile[1] = new Tile();
            File f2 = new File("./res/tiles/wall.png");
            tile[1].image = ImageIO.read(f2);
            tile[1].collision = true;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        
        try {
            String map = filePath;
            FileReader is = new FileReader(map);
            BufferedReader br = new BufferedReader(is);

            for (int i = 0; i < gp.maxWorldRow; i++) {
                
                String line = br.readLine();

                for (int j = 0; j < gp.maxWorldCol; j++) {
                    
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[j]);

                    mapTileNum[i][j] = num;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void draw(Graphics2D g2){
        
        for (int i = 0; i < gp.maxWorldRow; i++) {
            for (int j = 0; j < gp.maxWorldCol; j++) {  

                int worldX = j * gp.tileSize;
                int worldY = i * gp.tileSize;
                int screenX = worldX + gp.player.screenX - gp.player.worldX;
                int screenY = worldY + gp.player.screenY - gp.player.worldY;

                if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                        
                    g2.drawImage(tile[mapTileNum[i][j]].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }
}
