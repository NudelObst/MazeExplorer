package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    private final int PRESSED = 1;
    private final int UNPRESSED = 0;

    private int upPressed, downPressed, leftPressed, rightPressed;
    
    public int getUpPressed() {
        return upPressed;
    }


    public int getDownPressed() {
        return downPressed;
    }


    public int getLeftPressed() {
        return leftPressed;
    }


    public int getRightPressed() {
        return rightPressed;
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = PRESSED;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = PRESSED;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = PRESSED;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = PRESSED;
        }
    }

    
    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W) {
            upPressed = UNPRESSED;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = UNPRESSED;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = UNPRESSED;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = UNPRESSED;
        }
    }
    
}
