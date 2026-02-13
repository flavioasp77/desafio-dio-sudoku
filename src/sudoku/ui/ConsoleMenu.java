package sudoku.ui;

import java.util.InputMismatchException;
import java.util.stream.IntStream;
import sudoku.domain.Board;
import sudoku.domain.Cell;
import sudoku.service.SudokuService;
import sudoku.template.BoardTemplate;
import java.util.Scanner;

public class ConsoleMenu {
  private final SudokuService sudokuService;
  private final Board board;
  private final Scanner scanner = new Scanner(System.in);

  public ConsoleMenu(SudokuService sudokuService, Board board) {
    this.sudokuService = sudokuService;
    this.board = board;
  }

  public void run() {
    while (true) {
      menu();
      int choice;
      try {
        choice = scanner.nextInt();
      } catch (InputMismatchException e) {
        System.out.println("Please enter a number for menu option.");
        scanner.nextLine();
        continue;
      }
      switch (choice) {
        case 1 -> start();
        case 2 -> insert();
        case 3 -> remove();
        case 4 -> print(board);
        case 5 -> status();
        case 6 -> {
          try {
            sudokuService.clearUserNumbers();
          } catch (IllegalStateException ex) {
            System.out.println(ex.getMessage());
          }
          print(board);
        }
        case 7 -> {
          if (finish()) {
            return;
          }
        }
        default -> System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private void start() {
    sudokuService.start();
    print(board);
  }

  private void insert() {
    System.out.println("Row Col Value");
    try {
      int row = scanner.nextInt();
      int col = scanner.nextInt();
      int value = scanner.nextInt();
      try {
        if (!sudokuService.insert(row, col, value)) {
          System.out.println("Cannot insert into fixed cell.");
        }
      } catch (IllegalStateException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (InputMismatchException e) {
      System.out.println("Invalid input. Please enter three integers: row col value.");
      scanner.nextLine();
    } finally {
      print(board);
    }
  }

  private void remove() {
    System.out.println("Row Col");
    try {
      int row = scanner.nextInt();
      int col = scanner.nextInt();
      try {
        if (!sudokuService.remove(row, col)) {
          System.out.println("Cannot remove from fixed cell.");
        }
      } catch (IllegalStateException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (InputMismatchException e) {
      System.out.println("Invalid input. Please enter two integers: row col.");
      scanner.nextLine();
    } finally {
      print(board);
    }
  }

  private void status() {
    System.out.println("Status:" + sudokuService.getStatus() +
        " | " + (sudokuService.hasError() ? "Has Errors" : "No Errors"));
  }

  private boolean finish() {
    if (!board.isComplete() || sudokuService.hasError()) {
      System.out.println("Game is not complete or has errors.");
      return false;
    }
    System.out.println("Congratulations! You have completed the game.");
    return true;
  }

  private void menu() {
    System.out.println(
        """
        1. Start Game
        2. Insert Number
        3. Remove Number
        4. View
        5. Status
        6. Clear
        7. Exit
        """
    );
  }

  private void print(Board board) {
    IntStream.range(0, Board.SIZE)
        .forEach(row -> {
          if (row % 3 == 0) {
            System.out.println("+-------+-------+-------+");
          }
          String line = IntStream.range(0, Board.SIZE)
              .mapToObj(col -> {
                Cell cell = board.getCell(row, col);
                String value = cell.getValue() == null ? ".": cell.getValue().toString();
                if (col % 3 == 0) {
                  return "| " + value + " ";
                }

                return value + " ";

              }).reduce("", String::concat) + "|";
          System.out.println(line);
        });
    System.out.println("+-------+-------+-------+");
  }
}
