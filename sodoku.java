import java.util.*;

public class sodoku {
    static ArrayList<int[][]> boards = new ArrayList<>();
    static int[][] first_board = {
        {1,0,0,0,0,0,0,0,0},
        {0,0,0,1,0,0,0,0,0},
        {0,0,0,0,0,0,1,0,0},
        {0,1,0,0,0,0,0,0,0},
        {0,0,0,0,1,0,0,0,0},
        {0,0,0,0,0,0,0,1,0},
        {0,0,1,0,0,0,0,0,0},
        {0,0,0,0,0,1,0,0,0},
        {0,0,0,0,0,0,0,0,1}};
    
    public static void split() {
        int x = (int) (Math.random() * 9);
        int y = (int) (Math.random() * 9);
        while (boards.get(0)[x][y] == 0) {
            x = (int) (Math.random() * 9);
            y = (int) (Math.random() * 9);
        }
        for (int i = 1; i <= 9; i++) {
            boards.add(new int[9][9]);
            for(int x_ind = 0; x_ind <= 8; x_ind++) {
                for(int y_ind = 0; y_ind <= 8; y_ind++) {
                    if (x != x_ind && y != y_ind) {
                        boards.get(boards.size()-1)[x_ind][y_ind] = boards.get(0)[x_ind][y_ind];
                    } else {
                        boards.get(boards.size()-1)[x_ind][y_ind] = i;
                    }
                }
            }
        }
        boards.remove(0);
    }
    public static boolean check(int[][] board) {
        int num;
        for (int x = 0; x <= 8; x++) {
            for (int y = 0; y <= 8; y++) {
                num = board[x][y];
                if (num == 0) {
                    break;
                }
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
            }
        }
        return true;
    }
    public static void main(String[] args) {
        boards.add(first_board);
        split();
        int size = boards.size();
        for (int i = size-1; i >= size-9; i--) {
            if (!check(boards.get(i))) {
                boards.remove(i);
            }
        }
        System.out.println(boards.size());
    }
}