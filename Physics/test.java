package Physics;

import javax.swing.*;
import java.awt.*;
import java.util.*;

class ball {
    double x;
    double y;
    double xspeed;
    double yspeed;
    int red;
    int green;
    int blue;
    ball(double x, double y, double xspeed, double yspeed, int red, int green, int blue) {
        this.x = x;
        this.y = y;
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
}

public class test {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setUndecorated(true);
        window.setVisible(true);
        window.setSize(540, 960);

        Graphics g = window.getGraphics();

        ArrayList<ball> balls = new ArrayList<ball>();
        for (int i = 0; i < 100; i++) { 
            balls.add(new ball(150.0, 150.0, Math.random() * 10, Math.random() * 10, 0, 0, 0));
        }
        while (true) {
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
            }

            g.setColor(Color.white);
            g.fillRect(0, 0, 540, 960);

            for (int i = 0; i < balls.size(); i++) { 
                if (balls.get(i).x > 535) {
                    balls.get(i).xspeed *= -0.8;
                    balls.get(i).x = 535;
                } else if (balls.get(i).x < 5) {
                    balls.get(i).xspeed *= -0.8;
                    balls.get(i).x = 5;
                }
                if (balls.get(i).y > 955) {
                    balls.get(i).yspeed *= -0.8;
                    balls.get(i).y = 955;
                } else if (balls.get(i).y < 5) {
                    balls.get(i).yspeed *= -0.8;
                    balls.get(i).y = 5;
                }
                if (balls.get(i).xspeed > 0) {
                    balls.get(i).xspeed -= 0.005;
                } else if (balls.get(i).xspeed < 0) {
                    balls.get(i).xspeed += 0.005;
                }
                balls.get(i).yspeed += 0.16;
                balls.get(i).x += balls.get(i).xspeed;
                balls.get(i).y += balls.get(i).yspeed;
                g.setColor(new Color(balls.get(i).red, balls.get(i).green, balls.get(i).blue));
                g.fillOval((int)balls.get(i).x-5, (int)balls.get(i).y-5, 10, 10);
            }
        }
    }
}
