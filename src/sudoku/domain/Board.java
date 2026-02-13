package sudoku.domain;

import java.util.List;
import java.util.stream.IntStream;

public class Board {
  public static final int SIZE = 9;
  private final List<List<Cell>> grid;

  public Board() {
    this.grid = IntStream.range(0, SIZE)
      .mapToObj(i -> IntStream.range(0, SIZE)
        .mapToObj(j -> new Cell(null, false))
        .collect(java.util.stream.Collectors.toCollection(java.util.ArrayList::new)))
      .collect(java.util.stream.Collectors.toCollection(java.util.ArrayList::new));
  }

  public List<List<Cell>> rows() {
    return grid;
  }

  public void setFixed(int row, int col, Integer value) {
    grid.get(row).set(col, new Cell(value, true));
  }

  public Cell getCell(int row, int col) {
    return grid.get(row).get(col);
  }

  public boolean isComplete() {
    return grid.stream()
      .flatMap(List::stream)
      .noneMatch(cell -> cell.getValue() == null);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int row = 0; row < SIZE; row++) {
      if (row % 3 == 0) {
        sb.append("+-------+-------+-------+\n");
      }
      for (int col = 0; col < SIZE; col++) {
        if (col % 3 == 0) {
          sb.append("| ");
        }
        Cell cell = getCell(row, col);
        String value = cell.getValue() == null ? "." : cell.getValue().toString();
        sb.append(value);
        if (col % 3 == 2) {
          sb.append(" ");
        } else {
          sb.append(" ");
        }
      }
      sb.append("|\n");
    }
    sb.append("+-------+-------+-------+\n");
    return sb.toString();
  }
}
