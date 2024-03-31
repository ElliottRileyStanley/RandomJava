import java.util.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.*;

public class sodoku {
    static ArrayList<int[][]> boards = new ArrayList<>();
    static int[][] first_board = {
        {1,0,0,0,0,0,0,6,5},
        {3,4,2,0,7,0,1,0,0},
        {9,0,0,8,1,4,0,7,0},
        {0,5,0,7,0,0,8,0,3},
        {4,9,0,1,0,0,0,0,6},
        {0,7,0,3,4,2,0,1,0},
        {0,0,0,0,0,6,9,5,1},
        {6,1,9,0,0,7,0,3,0},
        {0,0,4,0,2,1,0,0,0}};

        /* 
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0}
        */
    
    public static void split(int x, int y) {
        for (int i = 1; i <= 9; i++) {
            boards.add(new int[9][9]);
            for(int x_ind = 0; x_ind <= 8; x_ind++) {
                for(int y_ind = 0; y_ind <= 8; y_ind++) {
                    if (x != x_ind || y != y_ind) {
                        boards.get(boards.size()-1)[x_ind][y_ind] = boards.get(0)[x_ind][y_ind];
                    } else {
                        boards.get(boards.size()-1)[x_ind][y_ind] = i;
                    }
                }
            }
        }
        boards.remove(0);
    }
    public static boolean check(int[][] board, int x, int y) {
        int num = board[x][y];
        for (int i = (x + 1) % 9; i != x; i = (i+1) % 9) {
            if (board[i][y] == num) {
                return false;
            }
        }
        for (int i = (y + 1) % 9; i != y; i = (i+1) % 9) {
            if (board[x][i] == num) {
                return false;
            }
        }
        for (int x_ind = Math.round(x/3)*3; x_ind <= Math.round(x/3)*3+2; x_ind++) {
            for (int y_ind = Math.round(y/3)*3; y_ind <= Math.round(y/3)*3+2; y_ind++) {
                if ((x != x_ind || y != y_ind) && board[x_ind][y_ind] == num) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) throws AWTException {
        Point p = MouseInfo.getPointerInfo().getLocation();
        Scanner scan = new Scanner(System.in);
        Robot robot = new Robot(MouseInfo.getPointerInfo().getDevice());
        boards.add(first_board);
        int size;
        int x_place = 0;
        int y_place = 0;
        int x = 0;
        int y = 0;
        while (true) {
            while (boards.get(0)[x_place][y_place] != 0) {
                if (x_place == 8) {
                    x_place = 0;
                    y_place++;
                } else {
                    x_place++;
                }
            }
            x = x_place;
            y = y_place;
            split(x, y);
            size = boards.size();
            System.out.println(x + "   " + y + " - " + size);
            for (int i = size-1; i >= size-9; i--) {
                if (!check(boards.get(i), x, y)) {
                    boards.remove(i);
                }
            }
            
            if (x_place == 8 && y_place == 8) {
                break;
            }
        }
        for (int[][] board : boards) {
            System.out.println(Arrays.deepToString(board));
        }
        scan.nextLine();
        int x1 = (int) MouseInfo.getPointerInfo().getLocation().getX();
        int y1 = (int) MouseInfo.getPointerInfo().getLocation().getY();
        scan.nextLine();
        int x2 = (int) MouseInfo.getPointerInfo().getLocation().getX();
        int y2 = (int) MouseInfo.getPointerInfo().getLocation().getY();
        for (x = 0; x <= 8; x++) {
            for (y = 0; y <= 8; y++) {
                try {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                robot.mouseMove(x1 + (x2-x1/8*x) - (int) MouseInfo.getPointerInfo().getLocation().getX(), y1 + (y2-y1/8*y) - (int) MouseInfo.getPointerInfo().getLocation().getY());
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot.keyPress(KeyEvent.VK_1);
            }
        }
    }
}