package util;

public class Print {

    public static void print(int[][] board) {
        for (int i=0; i<board.length; i++) {
            for (int j = 0; j < board[i].length; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }

    public static void print(char[][] board) {
        for (int i=0; i<board.length; i++) {
            for (int j = 0; j < board[i].length; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }
}
