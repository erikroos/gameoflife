package models;

import java.util.Random;

public class BoardModel {
    private int[][] board;
    private int xSize, ySize;

    public int[][] getBoard() {
        return board;
    }

    public void initBoard(int xSize, int ySize) {
        Random randomizer = new Random();

        this.xSize = xSize;
        this.ySize = ySize;
        board = new int[xSize][ySize];
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                board[x][y] = randomizer.nextInt(2);
            }
        }
    }

    /**
     * Any live cell with fewer than two live neighbours dies, as if by underpopulation.
     * Any live cell with two or three live neighbours lives on to the next generation.
     * Any live cell with more than three live neighbours dies, as if by overpopulation.
     * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
     */
    public void updateBoard() {
        int[][] newBoard = new int[xSize][ySize];
        int sumOfNeighbors;
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                int xMinus1 = x >= 1 ? x - 1 : xSize - 1;
                int yMinus1 = y >= 1 ? y - 1 : ySize - 1;
                int xPlus1 = (x + 1) % xSize;
                int yPlus1 = (y + 1) % ySize;

                sumOfNeighbors = 0;
                sumOfNeighbors += board[xMinus1][yMinus1];
                sumOfNeighbors += board[x][yMinus1];
                sumOfNeighbors += board[xPlus1][yMinus1];
                sumOfNeighbors += board[xMinus1][y];
                sumOfNeighbors += board[xPlus1][y];
                sumOfNeighbors += board[xMinus1][yPlus1];
                sumOfNeighbors += board[x][yPlus1];
                sumOfNeighbors += board[xPlus1][yPlus1];

                if (board[x][y] == 1 && sumOfNeighbors < 2) {
                    newBoard[x][y] = 0;
                } else if (board[x][y] == 1 && (sumOfNeighbors == 2 || sumOfNeighbors == 3)) {
                    newBoard[x][y] = 1; // spurious
                } else if (board[x][y] == 1 && sumOfNeighbors > 3) {
                    newBoard[x][y] = 0;
                } else if (board[x][y] == 0 && sumOfNeighbors == 3) {
                    newBoard[x][y] = 1;
                }
            }
        }
        board = newBoard;
    }
}
