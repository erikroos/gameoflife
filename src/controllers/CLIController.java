package controllers;

import models.BoardModel;

import java.util.Scanner;

public class CLIController implements ControllerInterface {
    private BoardModel boardModel;
    private int xSize;
    private int ySize;
    private int counter = 0;

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input;
        int[][] board;

        boardModel.initBoard(xSize, ySize);

        while (true) {
            boardModel.updateBoard();
            board = boardModel.getBoard();
            for (int y = 0; y < ySize; y++) {
                for (int x = 0; x < xSize; x++) {
                    if (board[x][y] == 1) {
                        System.out.print("*");
                    } else {
                        System.out.print(".");
                    }
                }
                System.out.print("\n");
            }

            System.out.println("Generation: " + counter++);
            System.out.println("Press <q> to quit or any other key to continue: ");
            input = scanner.nextLine();
            if (input.equals("q")) {
                break;
            }
        }
    }

    @Override
    public void setVariables(BoardModel boardModelToSet, int x, int y) {
        boardModel = boardModelToSet;
        xSize = x;
        ySize = y;
    }
}
