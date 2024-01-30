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

    ball(ball copy) {
        this.x = copy.x;
        this.y = copy.y;
        this.xspeed = copy.xspeed;
        this.yspeed = copy.yspeed;
        this.radius = copy.radius;
        this.red = copy.red;
        this.green = copy.green;
        this.blue = copy.blue;
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

        ArrayList<ball> oldBalls = new ArrayList<ball>();
        ArrayList<ball> newBalls = new ArrayList<ball>();
        oldBalls.add(new ball(100, 100, 2, -3, 30, 0, 0, 0));
        oldBalls.add(new ball(300.0, 100.0, -2, -3, 30, 0, 0, 0));

        newBalls.addAll(oldBalls);


        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
        }

        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }

            g.setColor(Color.white);
            g.fillRect(0, 0, 540, 960);

            for (int i = 0; i < oldBalls.size(); i++) { 
                for (int d = 0; d < oldBalls.size(); d++) {
                    if (i != d) {
                        if (Math.sqrt(Math.pow(oldBalls.get(i).x - oldBalls.get(d).x, 2) + Math.pow(oldBalls.get(i).y - oldBalls.get(d).y, 2)) < oldBalls.get(i).radius + oldBalls.get(d).radius) {
                            double xDistance = Math.abs(oldBalls.get(i).x - oldBalls.get(d).x);
                            double yDistance = Math.abs(oldBalls.get(i).y - oldBalls.get(d).y);
                            double xChange = (xDistance/2) / (xDistance/2 + yDistance/2);
                            double yChange = (yDistance/2) / (xDistance/2 + yDistance/2);
                            newBalls.get(i).xspeed = (oldBalls.get(d).xspeed + oldBalls.get(i).xspeed * -1) * 0.8 * xChange;
                            newBalls.get(i).yspeed = (oldBalls.get(d).yspeed + oldBalls.get(i).yspeed * -1) * 0.8 * yChange;
                        }
                    }
                }
            }
            for (int i = 0; i < oldBalls.size(); i++) {
                if (oldBalls.get(i).xspeed > 0) {
                    newBalls.get(i).xspeed -= 0.005;
                } else if (oldBalls.get(i).xspeed < 0) {
                    newBalls.get(i).xspeed += 0.005;
                }
                newBalls.get(i).yspeed += 0.16;
                newBalls.get(i).x += newBalls.get(i).xspeed;
                newBalls.get(i).y += newBalls.get(i).yspeed;
                g.setColor(new Color(newBalls.get(i).red, newBalls.get(i).green, newBalls.get(i).blue));
                g.fillOval((int)newBalls.get(i).x-newBalls.get(i).radius, (int)newBalls.get(i).y-newBalls.get(i).radius, newBalls.get(i).radius*2, newBalls.get(i).radius*2);
            }

            oldBalls.clear();
            for (int i = 0; i < newBalls.size(); i++) {
                oldBalls.add(new ball(newBalls.get(i)));
            }
        }
    }
}
