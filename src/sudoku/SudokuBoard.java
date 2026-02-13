package sudoku;

public class SudokuBoard {
    private int[][] board;
    private boolean[][] fixed;
    private static final int SIZE = 9;
    private static final int SUBGRID = 3;

    public SudokuBoard() {
        board = new int[SIZE][SIZE];
        fixed = new boolean[SIZE][SIZE];
    }

    public SudokuBoard(int[][] initialBoard) {
        this();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = initialBoard[i][j];
                fixed[i][j] = (initialBoard[i][j] != 0);
            }
        }
    }

    public int get(int row, int col) {
        return board[row][col];
    }

    public boolean set(int row, int col, int value) {
        if (fixed[row][col]) {
            return false;
        }
        if (value < 0 || value > 9) {
            return false;
        }
        board[row][col] = value;
        return true;
    }

    public boolean isFixed(int row, int col) {
        return fixed[row][col];
    }

    public boolean isValidMove(int row, int col, int value) {
        if (value < 1 || value > 9) {
            return false;
        }

        // Check row
        for (int j = 0; j < SIZE; j++) {
            if (j != col && board[row][j] == value) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < SIZE; i++) {
            if (i != row && board[i][col] == value) {
                return false;
            }
        }

        // Check 3x3 subgrid
        int startRow = (row / SUBGRID) * SUBGRID;
        int startCol = (col / SUBGRID) * SUBGRID;
        for (int i = startRow; i < startRow + SUBGRID; i++) {
            for (int j = startCol; j < startCol + SUBGRID; j++) {
                if ((i != row || j != col) && board[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isFilled() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != 0) {
                    int temp = board[i][j];
                    board[i][j] = 0;
                    if (!isValidMove(i, j, temp)) {
                        board[i][j] = temp;
                        return false;
                    }
                    board[i][j] = temp;
                }
            }
        }
        return true;
    }

    public void display() {
        System.out.println("\n   0 1 2   3 4 5   6 7 8");
        System.out.println("  ┌───────┬───────┬───────┐");
        for (int i = 0; i < SIZE; i++) {
            if (i == 3 || i == 6) {
                System.out.println("  ├───────┼───────┼───────┤");
            }
            System.out.print(i + " │");
            for (int j = 0; j < SIZE; j++) {
                if (j == 3 || j == 6) {
                    System.out.print("│");
                }
                if (board[i][j] == 0) {
                    System.out.print(" .");
                } else {
                    System.out.print(" " + board[i][j]);
                }
                if (j == 8) {
                    System.out.println("│");
                }
            }
        }
        System.out.println("  └───────┴───────┴───────┘");
    }

    public int getSize() {
        return SIZE;
    }
}
