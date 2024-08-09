package com.ingsystemcix.backgammon.utils;

/**
 *
 * @author IngSystemCix
 */
public class Board {
    private final String cross;
    private final String circle;
    private int topCounter;
    private int bottomCounter;
    private final Object[][] board;
    
    public Board() {
        this.cross = "X";
        this.circle = "O";
        this.topCounter = 13;
        this.bottomCounter = 12;
        this.board = new Object[12][12];
    }

    private String getCross() {
        return cross;
    }

    private String getCircle() {
        return circle;
    }

    private int getTopCounter() {
        return topCounter++;
    }

    private int getBottomCounter() {
        return bottomCounter--;
    }
    
    private void fillBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (row == 0 || row == 11) {
                    board[row][column] = (row == 0) ? getTopCounter() : getBottomCounter();
                } else {
                    // Evitar los espacios que no se deben llenar
                    if ((0 < column && column < 4) || (4 < column && column < 6) || (6 < column && column < 11)) continue;

                    if (row < 6) {
                        switch (column) {
                            case 0 -> board[row][column] = getCircle();
                            case 4 -> {
                                if (row < 4) board[row][column] = getCross();
                            }
                            case 6 -> board[row][column] = getCross();
                            case 11 -> {
                                if (row < 3) board[row][column] = getCircle();
                            }
                        }
                    } else {
                        switch (column) {
                            case 0 -> board[row][column] = getCross();
                            case 4 -> {
                                if (row > 7) board[row][column] = getCircle();
                            }
                            case 6 -> board[row][column] = getCircle();
                            case 11 -> {
                                if (row > 8) board[row][column] = getCross();
                            }
                        }
                    }
                }
            }
        }
    }

    
    private void playerScoreboard(String piece, int numberOfChips, int capturedChips) {
        System.out.printf("\t\t\t    %sFichas: %s | Libres: %d | Capturados: %d%s", ColoredConsoleText.ANSI_YELLOW, piece, numberOfChips, capturedChips, ColoredConsoleText.ANSI_RESET);
    }
    
    private String frame(int size) {
        String equals = "=";
        for (int i = 0; i < (size * 8) + 1; i++) {
            equals += "=";
        }
        return equals;
    }
    
    public void printBoard(String pice, int numberOfChipsPlayer1, int capturedChipsPlayer1, int numberOfChipsPlayer2, int capturedChipsPlayer2) {
        int countRow = 0;
        int size = 12;
        fillBoard();
        String piceComputer = (pice == null ? cross == null : pice.equals(cross)) ? circle : cross;
        playerScoreboard(piceComputer, numberOfChipsPlayer1, capturedChipsPlayer1);
        System.out.println("\n" + frame(size) + "=");
        for (Object[] objects : board) {
            System.out.print("|");
            for (int i = 0; i < objects.length; i++) {
                Object object = objects[i];
                if (object instanceof Integer num) System.out.print(num);
                if (object instanceof String str) System.out.print(str);
                if (object == null) System.out.print(" ");
                if (i == 5) System.out.print("\t||\t");
                else if (i < objects.length - 1) System.out.print("\t");
                else if (countRow == 0) System.out.print("");
                else System.out.print(" ");
            }
            System.out.println("|");
            countRow++;
            if (countRow == 6) System.out.println(frame(size) + "=");
        }
        System.out.println(frame(size) + "=");
        playerScoreboard(pice, numberOfChipsPlayer2, capturedChipsPlayer2);
    }


}