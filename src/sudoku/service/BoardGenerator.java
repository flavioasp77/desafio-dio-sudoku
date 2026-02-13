package sudoku.service;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import sudoku.domain.Board;

public class BoardGenerator {
  private final Random random = new Random();

  public void generate(Board board, int fixedCells) {
    int inserted = 0;
    while (inserted < fixedCells) {
      int row = random.nextInt(Board.SIZE);
      int col = random.nextInt(Board.SIZE);
      // If the cell already has a value, skip
      if (board.getCell(row, col).getValue() != null) {
        continue;
      }

      List<Integer> numbers = new java.util.ArrayList<>(
          IntStream.rangeClosed(1, Board.SIZE).boxed().toList());

      Collections.shuffle(numbers);

      for (Integer number : numbers) {
        if (isValid(board, row, col, number)) {
          board.setFixed(row, col, number);
          inserted++;
          break;
        }
      }
    }
  }

  private boolean isValid(Board board, int row, int col, int value) {
    boolean rowConflict = IntStream.range(0, Board.SIZE)
      .anyMatch(c -> board.getCell(row, c).getValue() != null && board.getCell(row, c).getValue() == value);

    // Check column
    boolean colConflict = IntStream.range(0, Board.SIZE)
      .anyMatch(r -> board.getCell(r, col).getValue() != null && board.getCell(r, col).getValue() == value);
    // Check 3x3 box
    int boxRowStart = (row / 3) * 3;
    int boxColStart = (col / 3) * 3;

    boolean blockConflict = IntStream.range(boxRowStart, boxRowStart + 3)
      .anyMatch(r -> IntStream.range(boxColStart, boxColStart + 3)
        .anyMatch(c -> board.getCell(r, c).getValue() != null && board.getCell(r, c).getValue() == value));

    return !(rowConflict || colConflict || blockConflict);
  }
}
