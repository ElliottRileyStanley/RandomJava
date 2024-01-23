import javax.swing.*;
import java.awt.*;

public class test {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setUndecorated(true);
        window.setVisible(true);
        window.setSize(540, 960);
        Graphics g = window.getGraphics();
        double x = 100;
        double y = 200;
        double xspeed = 5;
        double yspeed = 1;
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            g.setColor(Color.white);
            g.fillRect(0, 0, 300, 300);
            g.setColor(Color.black);
            g.fillOval((int)x-5, (int)y-5, 10, 10);
            if (x > 295) {
                xspeed *= -0.8;
                x = 295;
            } else if (x < 5) {
                xspeed *= -0.8;
                x = 5;
            }
            if (y > 295) {
                yspeed *= -0.8;
                y = 295;
            } else if (y < 5) {
                yspeed *= -0.8;
                y = 5;
            }
            if (xspeed > 0) {
                xspeed -= 0.005;
            } else if (xspeed < 0) {
                xspeed += 0.005;
            }
            yspeed += 0.05;
            x += xspeed;
            y += yspeed;
        }
    }
}
