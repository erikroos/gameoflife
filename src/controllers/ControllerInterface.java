package controllers;

import models.BoardModel;

public interface ControllerInterface {
    void setVariables(BoardModel boardModelToSet, int x, int y);

    void run();
}
