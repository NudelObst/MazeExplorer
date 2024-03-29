package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    //Screeen settings
    private final int originalTileSize = 16; //16x16 tile
    private final int scale = 3;

    private final int tileSize = originalTileSize * scale; //48x48 tile
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 12;
    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;
    //FPS
    private final int FPS = 60;

    private TileManager tileM;
    private KeyHandler keyH = new KeyHandler();
    private Thread gameThread;
    private CollisionChecker cChecker = new CollisionChecker(this);
    private Player player = new Player(this, keyH);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        tileM = new TileManager(this);
    }
    
    
    public int getOriginalTileSize() {
        return originalTileSize;
    }


    public int getScale() {
        return scale;
    }


    public int getTileSize() {
        return tileSize;
    }


    public int getMaxScreenCol() {
        return maxScreenCol;
    }


    public int getMaxScreenRow() {
        return maxScreenRow;
    }


    public int getScreenWidth() {
        return screenWidth;
    }


    public int getScreenHeight() {
        return screenHeight;
    }


    public int getFPS() {
        return FPS;
    }


    public TileManager getTileM() {
        return tileM;
    }


    public void setTileM(TileManager tileM) {
        this.tileM = tileM;
    }


    public KeyHandler getKeyH() {
        return keyH;
    }


    public void setKeyH(KeyHandler keyH) {
        this.keyH = keyH;
    }


    public Thread getGameThread() {
        return gameThread;
    }


    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }


    public CollisionChecker getcChecker() {
        return cChecker;
    }


    public void setcChecker(CollisionChecker cChecker) {
        this.cChecker = cChecker;
    }


    public Player getPlayer() {
        return player;
    }


    public void setPlayer(Player player) {
        this.player = player;
    }


    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        
        double drawInterval = 1000000000/FPS; // 0.01666 Sekunden
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    private void update(){
        player.update();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        
        tileM.draw(g2);

        player.draw(g2);
        g2.setColor(Color.green);
        //g2.drawRect(player.getScreenX() + player.getCollisionArea().x, player.getScreenY() + player.getCollisionArea().y, player.getCollisionArea().width, player.getCollisionArea().height);

        g2.dispose();

        //g2.setColor(getBackground());
    }
}