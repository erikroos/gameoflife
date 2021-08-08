package views;

public class ViewFactory {
    public final static String CLI = "cli";

    public static View get(String type) {
        switch (type) {
            case CLI:
                return new CLIView();
            default:
                return new CLIView();
        }
    }
}
