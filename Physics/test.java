package Physics;

import javax.swing.*;
import java.awt.*;
import java.util.*;

class ball {
    double x;
    double y;
    double xspeed;
    double yspeed;
    int radius;
    int red;
    int green;
    int blue;
    ball(double x, double y, double xspeed, double yspeed, int radius, int red, int green, int blue) {
        this.x = x;
        this.y = y;
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.radius = radius;
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
        balls.add(new ball(30.0, 100.0, 2, 0, 30, 0, 0, 0));
        balls.add(new ball(230.0, 120.0, -2, 0, 30, 0, 0, 0));

        while (true) {
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
            }

            g.setColor(Color.white);
            g.fillRect(0, 0, 540, 960);

            for (int i = 0; i < balls.size(); i++) { 
                if (balls.get(i).xspeed > 0) {
                    balls.get(i).xspeed -= 0.005;
                } else if (balls.get(i).xspeed < 0) {
                    balls.get(i).xspeed += 0.005;
                }
                balls.get(i).yspeed += 0.16;
                for (int d = 0; d < balls.size(); d++) {
                    if (i != d) {
                        if (Math.sqrt(Math.pow(balls.get(i).x - balls.get(d).x, 2) + Math.pow(balls.get(i).y - balls.get(d).y, 2)) < balls.get(i).radius + balls.get(d).radius) {
                            double xDistance = Math.abs(balls.get(i).x - balls.get(d).x);
                            double yDistance = Math.abs(balls.get(i).y - balls.get(i).y);
                            balls.get(i).xspeed *= -0.8 * (xDistance/2) / (xDistance/2 + yDistance/2);
                            balls.get(d).xspeed *= -0.8 * (xDistance/2) / (xDistance/2 + yDistance/2);
                            balls.get(i).yspeed *= -0.8 * (yDistance/2) / (xDistance/2 + yDistance/2) + 1;
                            balls.get(d).yspeed *= -0.8 * (yDistance/2) / (xDistance/2 + yDistance/2) + 1;
                        }
                    }
                }

                balls.get(i).x += balls.get(i).xspeed;
                balls.get(i).y += balls.get(i).yspeed;
                g.setColor(new Color(balls.get(i).red, balls.get(i).green, balls.get(i).blue));
                g.fillOval((int)balls.get(i).x-balls.get(i).radius, (int)balls.get(i).y-balls.get(i).radius, balls.get(i).radius*2, balls.get(i).radius*2);
            }
        }
    }
}
