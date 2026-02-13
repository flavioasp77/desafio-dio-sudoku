package sudoku.debug;

import sudoku.domain.Board;
import sudoku.domain.Cell;
import sudoku.service.SudokuService;

public class DebugRunner {
  public static void main(String[] args) {
    Board board = new Board();
    SudokuService service = new SudokuService(board);
    service.start();
    System.out.println("Before insert:");
    printBoard(board);

    boolean ok = service.insert(0, 0, 5);
    System.out.println("Insert returned: " + ok);
    System.out.println("After insert:");
    printBoard(board);
  }

  private static void printBoard(Board board) {
    for (int r = 0; r < 9; r++) {
      for (int c = 0; c < 9; c++) {
        Cell cell = board.getCell(r, c);
        Integer v = cell.getValue();
        System.out.print((v == null ? '.' : v) + (c == 8 ? "\n" : " "));
      }
    }
  }
}

