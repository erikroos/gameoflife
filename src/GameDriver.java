import controllers.ControllerFactory;
import controllers.ControllerInterface;
import models.BoardModel;
import java.util.Scanner;

public class GameDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please provide the horizontal size (X): ");
        String xString = scanner.nextLine();
        int x = Integer.parseInt(xString);

        System.out.println("Please provide the vertical size (Y): ");
        String yString = scanner.nextLine();
        int y = Integer.parseInt(yString);

        System.out.println("Would you like the CLI (1) or Graphical (2) interface? ");
        String type = scanner.nextLine();

        ControllerInterface controller = ControllerFactory.getController(type);
        controller.setVariables(new BoardModel(), x, y);
        controller.run();
    }
}
