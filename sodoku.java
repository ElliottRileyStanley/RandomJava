import java.util.*;

public class sodoku {
    static ArrayList<int[][]> boards = new ArrayList<>();
    static int[][] first_board = {
        {8,6,9,1,3,5,7,4,2},
        {5,2,1,4,7,8,9,3,6},
        {3,4,7,9,2,6,0,8,0},
        {6,7,8,3,4,1,5,2,9},
        {1,3,4,5,9,2,8,6,7},
        {9,5,2,8,6,7,3,1,4},
        {0,0,3,2,8,4,6,5,1},
        {4,1,6,7,5,3,2,9,8},
        {2,8,5,6,1,9,4,7,3}};
    
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
    public static void main(String[] args) {
        boards.add(first_board);
        int size;
        while (true) {
            int x = (int) (Math.random() * 9);
            int y = (int) (Math.random() * 9);
            while (boards.get(0)[x][y] == 0) {
                x = (int) (Math.random() * 9);
                y = (int) (Math.random() * 9);
            }
            System.out.println(boards.size());
            split(x, y);
            size = boards.size();
            for (int i = size-1; i >= size-9; i--) {
                if (!check(boards.get(i), x, y)) {
                    boards.remove(i);
                }
            }
            
            int min = 10;
            for (int x_ind = 0; x_ind <= 8; x_ind++) {
                for (int y_ind = 0; y_ind <= 8; y_ind++) {
                    if (boards.get(0)[x_ind][y_ind] < min) {
                        min = boards.get(0)[x_ind][y_ind];
                    }
                }
            }
            if (min != 0) {
                break;
            }
        }
        System.out.println(Arrays.deepToString(boards.get(0)));
    }
}