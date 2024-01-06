package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    
    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenRow][gp.maxScreenCol];

        getTileImage();
        loadMap("./res/maps/map01.txt");
    }

    public void getTileImage(){

        try {

            tile[0] = new Tile();
            File f1 = new File("./res/tiles/earth.png");
            tile[0].image = ImageIO.read(f1);

            tile[1] = new Tile();
            File f2 = new File("./res/tiles/wall.png");
            tile[1].image = ImageIO.read(f2);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        
        try {
            String map = filePath;
            FileReader is = new FileReader(map);
            BufferedReader br = new BufferedReader(is);

            for (int i = 0; i < gp.maxScreenRow; i++) {
                
                String line = br.readLine();

                for (int j = 0; j < gp.maxScreenCol; j++) {
                    
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
        
        for (int i = 0; i < gp.maxScreenRow; i++) {
            for (int j = 0; j < gp.maxScreenCol; j++) {
                g2.drawImage(tile[mapTileNum[i][j]].image, j*48, i*48, gp.tileSize, gp.tileSize, null);
            }
        }

        /* g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 48, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 96, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 144, 0, gp.tileSize, gp.tileSize, null); */
    }
}
