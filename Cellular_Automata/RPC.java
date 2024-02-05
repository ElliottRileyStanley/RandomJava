package Cellular_Automata;

import javax.swing.*;
import java.awt.*;
import java.util.*;

class frame extends JFrame {
    @Override
    public void paint (Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 50, 50);
    }
}

public class RPC {
    public static void main(String[] args) {
        frame frame = new frame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setSize(540, 960);
        frame.setVisible(true);
        Graphics g = frame.getGraphics();

        ArrayList<ArrayList<Color>> main = new ArrayList<ArrayList<Color>>();

        for (int i = 0; i < 100; i++) {
            main.add(ArrayList<Color>(new Color(255, 255, 255)));
            for (int d = 0; d < 100; d++) {
                main.get(i).add(new Color(255, 255, 255));
            }
        }

        frame.paint(g);
    }
}
