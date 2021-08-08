package views;

public abstract class View {
    public abstract boolean userContinues();

    public abstract void display(int[][] board, int x, int y);
}
