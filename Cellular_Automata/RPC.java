package Cellular_Automata;

import javax.swing.*;
import java.awt.*;

class panel extends JPanel {
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

    }
}

public class RPC {

    public static int width = 50;
    public static int height = 50;

    static Graphics graphics(Color[][] colors, Graphics oldGraphics) {
        Graphics newGraphics = oldGraphics.create();

        for (int i = 0; i < width; i++) {
            for (int d = 0; d < height; d++) {
                int count = 0;
                int red = 0;
                int green = 0;
                int blue = 0;
                if (i > 0) {
                    count++;
                    red += colors[i-1][d].getRed();
                    green += colors[i-1][d].getGreen();
                    blue += colors[i-1][d].getBlue();
                }
                if (i < width-1) {
                    count++;
                    red += colors[i+1][d].getRed();
                    green += colors[i+1][d].getGreen();
                    blue += colors[i+1][d].getBlue();
                }
                if (d > 0) {
                    count++;
                    red += colors[i][d-1].getRed();
                    green += colors[i][d-1].getGreen();
                    blue += colors[i][d-1].getBlue();
                }
                if (d < height-1) {
                    count++;
                    red += colors[i][d+1].getRed();
                    green += colors[i][d+1].getGreen();
                    blue += colors[i][d+1].getBlue();
                }
                red /= count;
                green /= count;
                blue /= count;

                colors [i][d] = new Color(red, green, blue);
            }
        }

        for (int i = 0; i < width; i++) {
            for (int d = 0; d < height; d++) {
                newGraphics.setColor(colors[i][d]);
                newGraphics.drawRect(i, d, 1, 1);
            }
        }

        return newGraphics;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setSize(width, height);
        frame.setVisible(true);
        panel panel = new panel();
        frame.add(panel);
        Graphics g = frame.getGraphics();

        Color[][] colors = new Color[width][height];

        for (int i = 0; i < width; i++) {
            for (int d = 0; d < height; d++) {
                colors[i][d] = new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256));
            }
        }

        while (true) {
            g = graphics(colors, g);
            panel.paintComponent(g);
            System.out.println(1);
        }
    }
}
