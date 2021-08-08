package views;

import java.util.Scanner;

public class CLIView extends View {
    Scanner scanner = new Scanner(System.in);

    @Override
    public boolean userContinues() {
        System.out.println("Press <q> to stop or any other key to continue...");
        String input = scanner.nextLine();
        if (input.equals("q")) {
            return false;
        }
        return true;
    }

    @Override
    public void display(int[][] board, int xSize, int ySize) {
        for (int y = 0; y < ySize; y++) {
            System.out.print("|");
            for (int x = 0; x < xSize; x++) {
                System.out.print(board[x][y] == 1 ? "#" : " ");
            }
            System.out.print("|\n");
        }
    }
}
