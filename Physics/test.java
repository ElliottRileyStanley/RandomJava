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

        ArrayList<ball> oldBalls = new ArrayList<ball>();
        ArrayList<ball> newBalls = new ArrayList<ball>();
        oldBalls.add(new ball(100.0, 100.0, 2, 0, 30, 0, 0, 0));
        oldBalls.add(new ball(300, 100, -2, 0, 30, 0, 0, 0));


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }


        while (true) {
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
            }

            newBalls.clear();
            newBalls.addAll(oldBalls);

            g.setColor(Color.white);
            g.fillRect(0, 0, 540, 960);

            for (int i = 0; i < oldBalls.size(); i++) { 
                for (int d = 0; d < oldBalls.size(); d++) {
                    if (i != d) {
                        if (Math.sqrt(Math.pow(oldBalls.get(i).x - oldBalls.get(d).x, 2) + Math.pow(oldBalls.get(i).y - oldBalls.get(d).y, 2)) < oldBalls.get(i).radius + oldBalls.get(d).radius) {
                            double xDistance = Math.abs(oldBalls.get(i).x - oldBalls.get(d).x);
                            double yDistance = Math.abs(oldBalls.get(i).y - oldBalls.get(i).y);
                            double xChange = (xDistance/2) / (xDistance/2 + yDistance/2);
                            double yChange = (yDistance/2) / (xDistance/2 + yDistance/2);
                            newBalls.get(i).xspeed += oldBalls.get(i).xspeed * -1.1 * xChange + oldBalls.get(d).xspeed * 0.8 * xChange;
                            newBalls.get(i).yspeed += oldBalls.get(i).yspeed * -1.1 * yChange + oldBalls.get(d).yspeed * 0.8 * yChange;
                            System.out.println(oldBalls.get(i).yspeed * -0.8 * yChange + oldBalls.get(d).yspeed * 0.8 * yChange);
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
                newBalls.get(i).x += oldBalls.get(i).xspeed;
                newBalls.get(i).y += oldBalls.get(i).yspeed;
                g.setColor(new Color(newBalls.get(i).red, newBalls.get(i).green, newBalls.get(i).blue));
                g.fillOval((int)newBalls.get(i).x-newBalls.get(i).radius, (int)newBalls.get(i).y-newBalls.get(i).radius, newBalls.get(i).radius*2, newBalls.get(i).radius*2);
            }

            oldBalls.clear();
            oldBalls.addAll(newBalls);
        }
    }
}
