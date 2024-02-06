package Cellular_Automata;

import javax.swing.*;
import java.awt.*;

class frame extends JFrame {
    @Override
    public void paint (Graphics g) {
        super.paint(g);
        
    }
}

public class RPC {

    public static int width = 100;
    public static int height = 100;

    static Graphics graphics(Color[][] colors, Graphics oldGraphics) {
        Graphics newGraphics = oldGraphics.create();
        for (int i = 0; i < width; i++) {
            for (int d = 0; d < width; d++) {
                newGraphics.setColor(colors[i][d]);
                newGraphics.drawRect(i, d, 1, 1);
            }
        }

        return newGraphics;
    }

    public static void main(String[] args) {
        frame frame = new frame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setSize(width, height);
        frame.setVisible(true);
        Graphics g = frame.getGraphics();

        Color[][] colors = new Color[width][height];

        for (int i = 0; i < width; i++) {
            for (int d = 0; d < width; d++) {
                colors[i][d] = new Color(255, 0, 0);
            }
        }

        while (true) {
            g = graphics(colors, g);
            frame.paint(g);
        }
    }
}
