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

class border {
    double x1;
    double y1;
    double x2;
    double y2;
    double a;
    double b;
    double c;
    double slope;
    double bouncy;
    border(double x1, double y1, double x2, double y2, double bouncy) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.slope = -(y2 - y1)/(x2 - x1);
        double a = -x1 * slope;
        double b = 1;
        double c = y1 + x1 * slope;
        this.bouncy = bouncy;
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
        balls.add(new ball(230.0, 100.0, -2, 0, 30, 0, 0, 0));
        ArrayList<border> borders = new ArrayList<border>();

        while (true) {
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
            }

            g.setColor(Color.white);
            g.fillRect(0, 0, 540, 960);

            for (int i = 0; i < borders.size(); i++) {
                g.setColor(Color.black);
                g.drawLine((int)borders.get(i).x1, (int)borders.get(i).y1, (int)borders.get(i).x2, (int)borders.get(i).y2);
            }

            for (int i = 0; i < balls.size(); i++) { 
                if (balls.get(i).xspeed > 0) {
                    balls.get(i).xspeed -= 0.005;
                } else if (balls.get(i).xspeed < 0) {
                    balls.get(i).xspeed += 0.005;
                }

                for (int d = 0; d < balls.size(); d++) {
                    if (i != d) {
                        if (Math.sqrt(Math.pow((balls.get(d).x - balls.get(i).x), 2) + Math.pow((balls.get(d).y - balls.get(i).y), 2)) < balls.get(d).radius + balls.get(i).radius) {
                            balls.get(i).xspeed = (balls.get(i).xspeed * (balls.get(i).radius - balls.get(d).radius) + 2 * balls.get(d).radius * balls.get(d).xspeed)/(balls.get(i).radius + balls.get(d).radius);
                            balls.get(i).yspeed = (balls.get(i).yspeed * (balls.get(i).radius - balls.get(d).radius) + 2 * balls.get(d).radius * balls.get(d).yspeed)/(balls.get(i).radius + balls.get(d).radius);
                            balls.get(d).xspeed = (balls.get(d).xspeed * (balls.get(d).radius - balls.get(i).radius) + 2 * balls.get(i).radius * balls.get(i).xspeed)/(balls.get(d).radius + balls.get(i).radius);
                            balls.get(d).yspeed = (balls.get(d).yspeed * (balls.get(d).radius - balls.get(i).radius) + 2 * balls.get(i).radius * balls.get(i).yspeed)/(balls.get(d).radius + balls.get(i).radius);
                        }
                    }
                }

                balls.get(i).yspeed += 0.16;
                balls.get(i).x += balls.get(i).xspeed;
                balls.get(i).y += balls.get(i).yspeed;
                g.setColor(new Color(balls.get(i).red, balls.get(i).green, balls.get(i).blue));
                g.fillOval((int)balls.get(i).x-balls.get(i).radius, (int)balls.get(i).y-balls.get(i).radius, balls.get(i).radius*2, balls.get(i).radius*2);
            }
        }
    }
}
