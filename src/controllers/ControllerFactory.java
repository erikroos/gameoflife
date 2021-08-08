package controllers;

public class ControllerFactory {
    public static ControllerInterface getController(String type) {
        switch (type) {
            case "1":
                return new CLIController();
            case "2":
            default:
                return new FXController();
        }
    }
}
