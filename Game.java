import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    static String board[][];
    static String EMPTY = " ";
    static String X = "X";
    static String O = "O";

    public Game() {
        board = new String[3][3];
        dashBoard();
    }

    // initialize with empty
    public void dashBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (j == board.length - 1) {
                    System.out.println(board[i][j]);
                } else {
                    System.out.print(board[i][j] + "  | ");
                }
            }
            if (i != board.length - 1) {
                System.out.println("---|----|---");
            }
        }
    }

    public boolean setPiece(int row, int col, String Step) {
        boolean isPlace = false;
        if (row < 0 || col < 0 || row >= board.length || col >= board.length) {
            System.out.println("Invalid row or colomn, Please try again:");
        } else if (!board[row][col].equals(EMPTY)) {
            System.out.println("Invalid, already fill, Please try again:");
        } else {
            board[row][col] = Step;
            isPlace = true;
        }
        return isPlace;
    }

    public boolean isWin() {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) &&
                    !board[i][0].equals(EMPTY)) {
                return true;
            }
        }
        // vertical
        for (int j = 0; j < board[0].length; j++) {
            if (board[0][j].equals(board[1][j]) && board[0][j].equals(board[2][j]) &&
                    !board[0][j].equals(EMPTY)) {
                return true;
            }
        }
        // diagonals
        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) &&
                !board[0][0].equals(EMPTY)) {
            return true;
        }
        if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) &&
                !board[0][2].equals(EMPTY)) {
            return true;
        }
        return false;
    }

    public boolean isDraw() {
        boolean finish = true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].equals(EMPTY)) {
                    finish = false;
                }
            }
        }
        return finish;
    }

    public String getNextPiece(String currStep) {
        if (currStep.equals(X)) {
            return O;
        } else {
            return X;
        }
    }

    public void play() {
        boolean isGameOver = false;
        Scanner sc = new Scanner(System.in);
        String currStep = X;
        while (!isGameOver) {
            displayBoard();
            System.out.println();
            System.out.print("Player " + currStep + ", enter your position:- ");
            try {
                int i = sc.nextInt();
                int j = sc.nextInt();
                if (setPiece(i, j, currStep)) {
                    if (isWin()) {
                        displayBoard();
                        System.out.println("Player " + currStep + " won!");
                        isGameOver = true;
                    } else if (isDraw()) {
                        displayBoard();
                        System.out.println("It is Finish!");
                        isGameOver = true;
                    } else {
                        currStep = getNextPiece(currStep);
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid row and colonms, Please try again: ");
            }
        }
    }

    public static void main(String[] args) {
        Game gm = new Game();
        gm.play();
    }
}