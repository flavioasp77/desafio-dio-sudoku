package sudoku;

import java.util.Random;

public class SudokuGame {
    private SudokuBoard board;
    private Random random;

    public SudokuGame() {
        random = new Random();
        board = generateBoard();
    }

    private SudokuBoard generateBoard() {
        // Create a solved board first
        int[][] solvedBoard = new int[9][9];
        fillBoard(solvedBoard, 0, 0);

        // Remove numbers to create a puzzle (keeping around 30-40 numbers visible)
        int[][] puzzle = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(solvedBoard[i], 0, puzzle[i], 0, 9);
        }

        int cellsToRemove = 45 + random.nextInt(6); // Remove 45-50 cells
        while (cellsToRemove > 0) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            if (puzzle[row][col] != 0) {
                puzzle[row][col] = 0;
                cellsToRemove--;
            }
        }

        return new SudokuBoard(puzzle);
    }

    private boolean fillBoard(int[][] board, int row, int col) {
        if (row == 9) {
            return true; // Board filled successfully
        }

        int nextRow = (col == 8) ? row + 1 : row;
        int nextCol = (col == 8) ? 0 : col + 1;

        // Try numbers 1-9 in random order
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        shuffleArray(numbers);

        for (int num : numbers) {
            if (isValid(board, row, col, num)) {
                board[row][col] = num;
                if (fillBoard(board, nextRow, nextCol)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }

        return false;
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        // Check row
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == num) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // Check 3x3 subgrid
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private void shuffleArray(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public SudokuBoard getBoard() {
        return board;
    }

    public boolean isGameWon() {
        return board.isFilled() && board.isValid();
    }
}
