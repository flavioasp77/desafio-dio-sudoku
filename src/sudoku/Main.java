package sudoku;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════");
        System.out.println("   BEM-VINDO AO SUDOKU - DIO.ME");
        System.out.println("═══════════════════════════════════════");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        SudokuGame game = new SudokuGame();
        SudokuBoard board = game.getBoard();

        boolean playing = true;
        while (playing) {
            board.display();
            System.out.println();
            System.out.println("Comandos:");
            System.out.println("  Digite 'p linha coluna numero' para jogar (ex: p 0 0 5)");
            System.out.println("  Digite 'r linha coluna' para remover um número (ex: r 0 0)");
            System.out.println("  Digite 's' para sair");
            System.out.print("\nComando: ");

            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            String[] parts = input.split("\\s+");
            String command = parts[0].toLowerCase();

            try {
                if (command.equals("s")) {
                    System.out.println("\nObrigado por jogar! Até logo!");
                    playing = false;
                } else if (command.equals("p") && parts.length == 4) {
                    int row = Integer.parseInt(parts[1]);
                    int col = Integer.parseInt(parts[2]);
                    int value = Integer.parseInt(parts[3]);

                    if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
                        System.out.println("\n❌ Posição inválida! Use linha e coluna entre 0 e 8.");
                        continue;
                    }

                    if (board.isFixed(row, col)) {
                        System.out.println("\n❌ Esta célula não pode ser modificada!");
                        continue;
                    }

                    if (value < 1 || value > 9) {
                        System.out.println("\n❌ Valor inválido! Use números de 1 a 9.");
                        continue;
                    }

                    if (!board.isValidMove(row, col, value)) {
                        System.out.println("\n❌ Movimento inválido! Este número já existe na linha, coluna ou quadrante 3x3.");
                        continue;
                    }

                    board.set(row, col, value);
                    System.out.println("\n✓ Número inserido com sucesso!");

                    if (game.isGameWon()) {
                        board.display();
                        System.out.println("\n╔════════════════════════════════════╗");
                        System.out.println("║  🎉 PARABÉNS! VOCÊ VENCEU! 🎉    ║");
                        System.out.println("╚════════════════════════════════════╝");
                        playing = false;
                    }
                } else if (command.equals("r") && parts.length == 3) {
                    int row = Integer.parseInt(parts[1]);
                    int col = Integer.parseInt(parts[2]);

                    if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
                        System.out.println("\n❌ Posição inválida! Use linha e coluna entre 0 e 8.");
                        continue;
                    }

                    if (board.isFixed(row, col)) {
                        System.out.println("\n❌ Esta célula não pode ser modificada!");
                        continue;
                    }

                    board.set(row, col, 0);
                    System.out.println("\n✓ Número removido com sucesso!");
                } else {
                    System.out.println("\n❌ Comando inválido! Use 'p', 'r' ou 's'.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n❌ Formato inválido! Certifique-se de usar números.");
            } catch (Exception e) {
                System.out.println("\n❌ Erro: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
