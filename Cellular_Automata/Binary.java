package Cellular_Automata;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Binary extends JPanel implements KeyListener {
    static final int width = 200;
    static final int height = 200;
    static final int scale = 4;

    static int wait = 50;

    static boolean[][] prev_board = new boolean[width][height];
    static boolean[][] new_board = new boolean[width][height];

    public void keyPressed (KeyEvent event) {}
    public void keyReleased (KeyEvent event) {}
    public void keyTyped (KeyEvent event) {
        System.out.println(wait);
        if (event.getKeyChar() == 97) {
            wait += 10;
        } else if (event.getKeyChar() == 101 && wait >= 10) {
            wait -= 10;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int living = 0;
                for (int x_dif = -1; x_dif <= 1; x_dif++) {
                    for (int y_dif = -1; y_dif <= 1; y_dif++) {
                        if ((x_dif == 0 && y_dif == 0) || (x+x_dif >= width || x+x_dif < 1 || y+y_dif >= height || y+y_dif < 1)) {continue;}
                        if (prev_board[x+x_dif][y+y_dif] == true) {living++;}
                    }
                }
                if (Math.random() > 0.7) {
                    if (!prev_board[x][y] && living > 4) {new_board[x][y] = true;}
                    else if (prev_board[x][y] && (living < 4)) {new_board[x][y] = false;}
                    else {new_board[x][y] = prev_board[x][y];}
                } else {
                    if (!prev_board[x][y] && living > 3) {new_board[x][y] = true;}
                    else if (prev_board[x][y] && (living < 5)) {new_board[x][y] = false;}
                    else {new_board[x][y] = prev_board[x][y];}
                }
                if (!new_board[x][y]) {g.setColor(new Color(0, 0, 0));}
                else if (new_board[x][y]) {g.setColor(new Color(190, 190, 190));}
                g.fillRect(x*scale, y*scale, 1*scale, 1*scale);
            }
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                prev_board[x][y] = new_board[x][y];
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                prev_board[x][y] = ((int) (Math.random()*2) == 0);
            }
        }

        JFrame frame = new JFrame("Binary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new Binary());
        frame.setUndecorated(true);
        frame.setSize(width*scale, height*scale);
        frame.add(new Binary());
        frame.setVisible(true);
        while (true) {
            Thread.sleep(wait);
            frame.repaint();
        }
    }
}