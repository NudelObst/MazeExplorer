package tile;

import java.io.BufferedReader;
import java.io.FileReader;

import main.GamePanel;

public class Map {
        
    private GamePanel gp;
    private int[][] mapTileNum;
    private int maxWorldCol;
    private int maxWorldRow;

    private int playerSpawnX;
    private int playerSpawnY;

    public Map(GamePanel gp){
        this.gp = gp;
        initializeMap("./res/maps/WorldmapSettings.txt");
        mapTileNum = new int[maxWorldRow][maxWorldCol];
        loadMap("./res/maps/WorldmapBackground.txt");
    }

    
    public GamePanel getGp() {
        return gp;
    }


    public void setGp(GamePanel gp) {
        this.gp = gp;
    }


    public int[][] getMapTileNum() {
        return mapTileNum;
    }


    public void setMapTileNum(int[][] mapTileNum) {
        this.mapTileNum = mapTileNum;
    }


    public int getMaxWorldCol() {
        return maxWorldCol;
    }


    public void setMaxWorldCol(int maxWorldCol) {
        this.maxWorldCol = maxWorldCol;
    }


    public int getMaxWorldRow() {
        return maxWorldRow;
    }


    public void setMaxWorldRow(int maxWorldRow) {
        this.maxWorldRow = maxWorldRow;
    }


    public int getPlayerSpawnX() {
        return playerSpawnX;
    }


    public void setPlayerSpawnX(int playerSpawnX) {
        this.playerSpawnX = playerSpawnX;
    }


    public int getPlayerSpawnY() {
        return playerSpawnY;
    }


    public void setPlayerSpawnY(int playerSpawnY) {
        this.playerSpawnY = playerSpawnY;
    }


    public void initializeMap(String filePath){
        try {
            FileReader is = new FileReader(filePath);
            BufferedReader br = new BufferedReader(is);
            String line = br.readLine();
            while (line != null) {
                if (line.contains("width:")) {
                    line = line.replaceAll("[^0-9]","");
                    maxWorldCol = Integer.parseInt(line);
                } else if(line.contains("height:")){                    
                    line = line.replaceAll("[^0-9]","");
                    maxWorldRow = Integer.parseInt(line);
                } else if(line.contains("spawnX:")){                    
                    line = line.replaceAll("[^0-9]","");
                    gp.getPlayer().setWorldX(gp.getTileSize() * (Integer.parseInt(line) - 1));
                } else if(line.contains("spawnY:")){                    
                    line = line.replaceAll("[^0-9]","");
                    gp.getPlayer().setWorldY(gp.getTileSize() * (Integer.parseInt(line) - 1));
                } 
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void loadMap(String filePath){

        try {
            String map = filePath;
            FileReader is = new FileReader(map);
            BufferedReader br = new BufferedReader(is);

            for (int i = 0; i < maxWorldRow; i++) {
                
                String line = br.readLine();

                for (int j = 0; j < maxWorldCol; j++) {
                    
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[j]);

                    mapTileNum[i][j] = num;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
