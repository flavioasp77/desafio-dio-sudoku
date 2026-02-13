package sudoku.domain;

public class Cell {
  private Integer value;
  private final boolean fixed;

  public Cell(Integer value, boolean fixed) {
    this.value = value;
    this.fixed = fixed;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    if (!fixed) {
      this.value = value;
    }
  }

  public boolean isFixed() {
    return fixed;
  }
}
