package sudoku.service;

import sudoku.domain.Board;
import sudoku.domain.Cell;
import sudoku.domain.GameStatus;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class SudokuService {
  private final Board board;
  private boolean started = false;

  public SudokuService(Board board) {
    this.board = board;
  }

  public  void start() {
    if (!started) {
      started = true;
    }
  }

  public boolean insert(int row, int col, int value) {
    if (!started) {
      throw new IllegalStateException("Game not started");
    }
    if (board.getCell(row, col).isFixed()) {
      return false;
    }
    board.getCell(row, col).setValue(value);
    return true;
  }

  public boolean remove(int row, int col) {
    if (!started) {
      throw new IllegalStateException("Game not started");
    }
    if (board.getCell(row, col).isFixed()) {
      return false;
    }
    board.getCell(row, col).setValue(null);
    return true;
  }

  public void clearUserNumbers() {
    if (!started) {
      throw new IllegalStateException("Game not started");
    }
    board.rows().stream().flatMap(
      row -> row.stream().filter(cell -> !cell.isFixed())
    ).forEach(
      cell -> {
        if (!cell.isFixed()) {
          cell.setValue(null);
        }
      }
    );
  }

  public GameStatus getStatus() {
    if (!started) {
      return GameStatus.NOT_STARTED;
    }
    return board.isComplete() ? GameStatus.COMPLETE : GameStatus.IMCOMPLETE;
  }

  public boolean hasError() {
    return rowsValid() || colsValid() || blocksValid();
  }

  private  boolean rowsValid() {
    return board.rows().stream().allMatch(this::unique);
  }

  private boolean colsValid() {
    return IntStream.range(0, Board.SIZE).allMatch(
      col -> unique(IntStream.range(0, Board.SIZE)
        .mapToObj(row -> board.getCell(row, col))
        .toList()
      )
    );
  }

  public boolean blocksValid() {
    return IntStream.range(0, Board.SIZE)
        .filter(index -> index % 3 == 0)
        .allMatch(row ->
            IntStream.range(0, Board.SIZE)
                .filter(col -> col % 3 == 0)
                .allMatch(col -> unique(block(row, col)))
        );
  }

  private boolean unique(Iterable<Cell> cells) {
    Set<Integer> seen = new HashSet<>();
    for (Cell cell : cells) {
      if (cell.getValue() != null && !seen.add(cell.getValue())) {
        return false;
      }
    }
    return true;
  }

  private Iterable<Cell> block(int row, int col) {
    return IntStream.range(row, row + 3)
        .boxed()
        .flatMap(r -> IntStream.range(col, col + 3)
            .mapToObj(c -> board.getCell(r, c)))
        .toList();
  }
}
