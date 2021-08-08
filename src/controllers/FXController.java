package controllers;

import models.BoardModel;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class FXController extends Application implements ControllerInterface {
    private static BoardModel boardModel;
    private static Stage primaryStage;
    private static int xSize;
    private static int ySize;
    private int counter = 0;
    private static boolean isPaused = false;

    @Override
    public void start(Stage stage) throws Exception {
        boardModel.initBoard(xSize, ySize);

        initScreen();

        AnimationTimer at = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                if (!isPaused) {
                    GridPane lifeGrid = new GridPane();
                    boardModel.updateBoard();
                    int[][] board = boardModel.getBoard();
                    String style;
                    for (int y = 0; y < ySize; y++) {
                        for (int x = 0; x < xSize; x++) {
                            Pane cell = new Pane();
                            style = "-fx-border-color: black;";
                            if (board[x][y] == 1) {
                                style += " -fx-background-color: black;";
                            }
                            cell.setStyle(style);
                            cell.setPrefSize(600 / xSize, 600 / ySize);
                            lifeGrid.add(cell, x, y);
                        }
                    }
                    updateScreen(lifeGrid);
                }
            }
        };
        at.start();
    }

    private void initScreen() throws IOException {
        primaryStage = new Stage();
        primaryStage.setTitle("The Game of Life");

        Parent root = FXMLLoader.load(getClass().getResource("../views/main_screen.fxml"));
        Scene scene = new Scene(root, 600, 650);

        String css = this.getClass().getResource("../views/gameoflife.css").toExternalForm();
        scene.getStylesheets().add(css);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateScreen(GridPane grid) {
        BorderPane borderPane = (BorderPane)primaryStage.getScene().getRoot();
        borderPane.setCenter(grid);
        HBox hbox = (HBox)borderPane.getBottom();
        Pane filler = (Pane)hbox.getChildren().get(1);
        Text t = (Text)filler.getChildren().get(0);
        t.setText("Generation " + counter++);
    }

    @FXML
    protected void handlePauseButtonAction(ActionEvent event) {
        BorderPane borderPane = (BorderPane)primaryStage.getScene().getRoot();
        HBox hbox = (HBox)borderPane.getBottom();
        Button pauseButton = (Button)hbox.getChildren().get(0);
        if (isPaused) {
            isPaused = false;
            pauseButton.setText("Pause game");
        } else {
            isPaused = true;
            pauseButton.setText("Resume game");
        }
    }

    @FXML
    protected void handleStopButtonAction(ActionEvent event) {
        try {
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void setVariables(BoardModel boardModelToSet, int x, int y) {
        boardModel = boardModelToSet;
        xSize = x;
        ySize = y;
    }

    /**
     * Not used, does not work because you cannot set variables
     */
    @Override
    public void run() {
        this.launch(FXController.class);
    }
}
