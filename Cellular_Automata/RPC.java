package Cellular_Automata;

import javax.swing.*;
import java.awt.*;

public class RPC extends JPanel {
    static int width = 500;
    static int height = 500;

    static int[][] prev_board = new int[width][height];
    static int[][] new_board = new int[width][height];
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int losses = 0;
                for (int x_dif = -1; x_dif <= 1; x_dif++) {
                    for (int y_dif = -1; y_dif <= 1; y_dif++) {
                        if ((x_dif == 0 && y_dif == 0) || (x+x_dif >= width || x+x_dif < 1 || y+y_dif >= height || y+y_dif < 1)) {continue;}
                        if (prev_board[x][y] == 0 && prev_board[x+x_dif][y+y_dif] == 1) {losses++;}
                        if (prev_board[x][y] == 1 && prev_board[x+x_dif][y+y_dif] == 2) {losses++;}
                        if (prev_board[x][y] == 2 && prev_board[x+x_dif][y+y_dif] == 0) {losses++;}
                    }
                }
                if (losses > 2) {new_board[x][y] = ((prev_board[x][y]+1) % 3);}
                else {new_board[x][y] = prev_board[x][y];}
                if (new_board[x][y] == 0) {g.setColor(new Color(255, 0, 0));}
                else if (new_board[x][y] == 1) {g.setColor(new Color(0, 255, 0));}
                else if (new_board[x][y] == 2) {g.setColor(new Color(0, 0, 255));}
                g.fillRect(x, y, 3, 3);
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
                prev_board[x][y] = (int) (Math.random()*3);
            }
        }

        JFrame frame = new JFrame("RPC");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setSize(width, height);
        frame.add(new RPC());
        frame.setVisible(true);
        while (true) {
            frame.repaint();
        }
    }
}