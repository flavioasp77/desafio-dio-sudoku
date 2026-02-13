package sudoku.app;

import java.util.Scanner;
import sudoku.domain.Board;
import sudoku.service.BoardGenerator;
import sudoku.service.SudokuService;
import sudoku.ui.ConsoleMenu;

public class SudokuApplication {
  public static  void main(String[] args) {
    Board board = new Board();

    System.out.println("Welcome to Sudoku!");
    System.out.println("Set fixed numbers total (20-60):");
    int number = 0;
    do {
      Scanner sc = new Scanner(System.in);
      number = sc.nextInt();
      if (number < 20 || number > 60) {
        System.out.println("Invalid number. Please enter a number between 20 and 60.");
      }
    } while (number < 20 || number > 60);

    BoardGenerator generator = new BoardGenerator();
    generator.generate(board, number);
    SudokuService sudokuService = new SudokuService(board);
    new ConsoleMenu(sudokuService, board).run();
  }
}
