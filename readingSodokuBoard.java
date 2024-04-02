import java.awt.*;
import java.util.Arrays;

public class readingSodokuBoard {
    public static void main(String[] args) throws AWTException {
        int[][] first_board = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}};
        Robot robot = new Robot(MouseInfo.getPointerInfo().getDevice());
        int x1 = 991;
        int y1 = 274;
        int x2 = 1537;
        int y2 = 819;
        for (int x = 0; x <= 8; x++) {
            for (int y = 0; y <= 8; y++) {
                System.out.println(robot.getPixelColor(x1+(x2-x1)/8*x, y1+(y2-y1)/8*y));
                if (robot.getPixelColor(x1+(x2-x1)/8*x, y1+(y2-y1)/8*y).getGreen() != 218 && robot.getPixelColor(x1+(x2-x1)/8*x, y1+(y2-y1)/8*y).getGreen() != 255) {
                    first_board[y][x] = 1;
                }
            }
        }
        System.out.println(Arrays.deepToString(first_board));
    }
}
