import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class circle {
    public static void main(String[] args) {
        int middle_x = 1440;
        int middle_y = 557;
        int radius = 400;
        try {
            Robot robot = new Robot();
            robot.mouseMove((int) (Math.cos(0) * radius) + middle_x, (int) (Math.sin(0) * radius) + middle_y);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);       
            for(double i = 0; i < 2*3.14159; i += 0.0001) {
                robot.mouseMove((int) (Math.cos(i) * radius) + middle_x, (int) (Math.sin(i) * radius) + middle_y);
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}