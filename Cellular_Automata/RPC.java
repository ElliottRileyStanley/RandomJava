package Cellular_Automata;

import javax.swing.*;
import java.awt.*;

public class RPC extends JPanel {

    static int[][] prev_board = new int[100][100];
    static int[][] new_board = new int[100][100];
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(150, 150, 150));
        
        while (true) {
            for (int x = 0; x < 100; x++) {
                for (int y = 0; y < 100; y++) {
                    int losses = 0;
                    for (int x_dif = -1; x_dif <= 1; x_dif++) {
                        for (int y_dif = -1; y_dif <= 1; y_dif++) {
                            if ((x_dif == 0 && y_dif == 0) || (x+x_dif > 99 || x+x_dif < 1 || y+y_dif > 99 || y+y_dif < 1)) {continue;}
                            if (prev_board[x][y] == 1 && prev_board[x+x_dif][y+y_dif] == 2) {losses++;}
                            if (prev_board[x][y] == 2 && prev_board[x+x_dif][y+y_dif] == 3) {losses++;}
                            if (prev_board[x][y] == 3 && prev_board[x+x_dif][y+y_dif] == 1) {losses++;}
                        }
                    }
                    if (losses > 3) {prev_board[x][y] = (prev_board[x][y] % 3) + 1;}
                    if (new_board[x][y] == 0) {g.setColor(new Color(255, 0, 0));}
                    else if (new_board[x][y] == 1) {g.setColor(new Color(0, 255, 0));}
                    else if (new_board[x][y] == 2) {g.setColor(new Color(0, 0, 255));}
                    g.fillRect(x, y, 1, 1);
                }
            }
        }

    }

    public static void main(String[] args) {
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                prev_board[x][y] = (int) (Math.random()*3);
            }
        }

        JFrame frame = new JFrame("RPC");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setSize(100, 100);
        frame.add(new RPC());
        frame.setVisible(true);
    }
}