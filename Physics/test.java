package Physics;

import javax.swing.*;
import java.awt.*;
import java.util.*;

class ball {
    double x;
    double y;
    double xspeed;
    double yspeed;
    ball(double x_value, double y_value, double xspeed_value, double yspeed_value) {
        x = x_value;
        y = y_value;
        xspeed = xspeed_value;
        yspeed = yspeed_value;

    }
}

public class test {
    public static void main(String[] args) {
        /*Initialize window */
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setUndecorated(true);
        window.setVisible(true);
        window.setSize(540, 960);

        /*Initilize graphics */
        Graphics g = window.getGraphics();

        /*Initilize objects */
        ArrayList<ball> balls = new ArrayList<ball>();
        balls.add(new ball(150, 150, (Math.random()-0.5)*5, (Math.random()-0.5)*5));
        System.out.println(balls.get(0).y);

        while (true) {
            try {/*Pause so that it can update (framerate) */
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            g.setColor(Color.white);
            g.fillRect(0, 0, 540, 960);
            g.setColor(new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255), 255));
            for (int i = 0; i < balls.size(); i++) { /*Loops through all balls to update physics */
                g.fillOval((int)balls.get(i).x-5, (int)balls.get(i).y-5, 10, 10);
                if (balls.get(i).x > 535) {
                    balls.get(i).xspeed *= -0.8;
                    balls.get(i).x = 535;
                    balls.add(new ball(150, 150, (Math.random()-0.5)*5, (Math.random()-0.5)*5));
                } else if (balls.get(i).x < 5) {
                    balls.get(i).xspeed *= -0.8;
                    balls.get(i).x = 5;
                    balls.add(new ball(150, 150, (Math.random()-0.5)*5, (Math.random()-0.5)*5));
                }
                if (balls.get(i).y > 960) {
                    balls.get(i).yspeed *= -0.8;
                    balls.get(i).y = 960;
                    balls.add(new ball(150, 150, (Math.random()-0.5)*5, (Math.random()-0.5)*5));
                } else if (balls.get(i).y < 5) {
                    balls.get(i).yspeed *= -0.8;
                    balls.get(i).y = 5;
                    balls.add(new ball(150, 150, (Math.random()-0.5)*5, (Math.random()-0.5)*5));
                }
                if (balls.get(i).xspeed > 0) {
                    balls.get(i).xspeed -= 0.005;
                } else if (balls.get(i).xspeed < 0) {
                    balls.get(i).xspeed += 0.005;
                }
                balls.get(i).yspeed += 0.05;
                balls.get(i).x += balls.get(i).xspeed;
                balls.get(i).y += balls.get(i).yspeed;
            }
        }
    }
}
