import java.awt.*;
import java.awt.event.InputEvent;


public class test {
    public static void main(String args[]) {
        try {
            Robot robot = new Robot();
            while (true) {
                PointerInfo a = MouseInfo.getPointerInfo();
                System.out.println();
                if (robot.getPixelColor((int) a.getLocation().getX(), (int) a.getLocation().getY()).getGreen() > 200) {
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    break;
                }
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }
}
