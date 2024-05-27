import java.util.ArrayList;

public class TicTacToeBoard {
    private int[][] board;
    private int movesRemaining;

    public TicTacToeBoard() {
        board = new int[3][3];
        movesRemaining = 9;
    }

    public boolean makeMove(int x, int y, int playerId) {
        if (board[x][y] == 0) {
            board[x][y] = playerId;
            movesRemaining--;
            return true;
        }

        return false;
    }

    public boolean isFull() {
        return movesRemaining == 0;
    }

    


    public ArrayList<Integer> getBoardAsInputs() {
        ArrayList<Integer> result = new ArrayList<>();
        for (int[] row: board) {
            for (int val : row) {
                result.add(val)
            }
        }
        return result;
    }
}
