# Desafio DIO - Sudoku em Java

Simples jogo de Sudoku em Java para ser jogado no terminal como parte do Boot Camp Java da Dio.me

## 📋 Sobre o Projeto

Este é um jogo de Sudoku interativo desenvolvido em Java que pode ser jogado diretamente no terminal. O jogo gera tabuleiros de Sudoku válidos e permite que o jogador preencha as células vazias seguindo as regras tradicionais do Sudoku.

## 🎮 Regras do Sudoku

- O tabuleiro é uma grade 9×9 dividida em 9 sub-grades 3×3
- Cada linha deve conter os números de 1 a 9, sem repetições
- Cada coluna deve conter os números de 1 a 9, sem repetições
- Cada sub-grade 3×3 deve conter os números de 1 a 9, sem repetições

## 🚀 Como Executar

### Pré-requisitos

- Java Development Kit (JDK) 8 ou superior instalado
- Terminal/Prompt de Comando

### Compilar o Projeto

```bash
javac -d bin src/sudoku/*.java
```

### Executar o Jogo

```bash
java -cp bin sudoku.Main
```

## 🎯 Como Jogar

Quando o jogo iniciar, você verá o tabuleiro de Sudoku exibido no terminal:

```
   0 1 2   3 4 5   6 7 8
  ┌───────┬───────┬───────┐
0 │ . . .│ . 6 7│ . 2 4│
1 │ 5 7 6│ 4 . .│ 3 . .│
2 │ 1 . .│ . 9 8│ 7 . .│
  ├───────┼───────┼───────┤
3 │ 7 9 .│ 1 8 3│ 2 . 5│
4 │ . . 1│ . . .│ 4 . 7│
5 │ 2 6 .│ . . .│ . . .│
  ├───────┼───────┼───────┤
6 │ . . .│ . . .│ 9 . 3│
7 │ . . 7│ . 1 .│ . 4 2│
8 │ . 1 9│ . . .│ . 7 .│
  └───────┴───────┴───────┘
```

### Comandos Disponíveis

- **Colocar um número**: `p linha coluna numero`
  - Exemplo: `p 0 0 3` (coloca o número 3 na linha 0, coluna 0)
  
- **Remover um número**: `r linha coluna`
  - Exemplo: `r 0 0` (remove o número da linha 0, coluna 0)
  
- **Sair do jogo**: `s`

### Observações

- As células com números pré-definidos (iniciais) não podem ser modificadas
- O jogo valida automaticamente se o número pode ser colocado naquela posição
- Quando você completar corretamente o tabuleiro, o jogo exibirá uma mensagem de vitória

## 🏗️ Estrutura do Projeto

```
desafio-dio-sudoku/
├── src/
│   └── sudoku/
│       ├── Main.java          # Interface do usuário no terminal
│       ├── SudokuBoard.java   # Representação e validação do tabuleiro
│       └── SudokuGame.java    # Lógica do jogo e geração de tabuleiros
├── bin/                       # Arquivos compilados (.class)
├── .gitignore                 # Arquivos ignorados pelo Git
└── README.md                  # Este arquivo
```

## 🛠️ Tecnologias Utilizadas

- Java
- Scanner (para entrada do usuário)
- Random (para geração de tabuleiros)

## 📝 Licença

Este projeto foi desenvolvido como parte do Boot Camp Java da [DIO.me](https://dio.me)

## 👨‍💻 Autor

Desenvolvido como desafio do Boot Camp Java da DIO.me