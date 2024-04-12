package Cellular_Automata;

import javax.swing.*;
import java.awt.*;

public class Binary extends JPanel {
    static int width = 500;
    static int height = 500;
    static int scale = 1;

    static int[][] prev_board = new int[width][height];
    static int[][] new_board = new int[width][height];
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int living = 0;
                for (int x_dif = -1; x_dif <= 1; x_dif++) {
                    for (int y_dif = -1; y_dif <= 1; y_dif++) {
                        if ((x_dif == 0 && y_dif == 0) || (x+x_dif >= width || x+x_dif < 1 || y+y_dif >= height || y+y_dif < 1)) {continue;}
                        if (prev_board[x+x_dif][y+y_dif] == 1) {living++;}
                    }
                }
                if (prev_board[x][y] == 0 && (living == 3)) {new_board[x][y] = 1;}
                else if (prev_board[x][y] == 1 && (living != 1 && living != 2 && living != 3 && living != 4 && living != 5)) {new_board[x][y] = 0;}
                else {new_board[x][y] = prev_board[x][y];}
                if (new_board[x][y] == 0) {g.setColor(new Color(0, 0, 0));}
                else if (new_board[x][y] == 1) {g.setColor(new Color(255, 255, 255));}
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
                prev_board[x][y] = (int) (Math.random()*2);
            }
        }

        JFrame frame = new JFrame("Binary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setSize(width*scale, height*scale);
        frame.add(new Binary());
        frame.setVisible(true);
        while (true) {
            Thread.sleep(100);
            frame.repaint();
        }
    }
}