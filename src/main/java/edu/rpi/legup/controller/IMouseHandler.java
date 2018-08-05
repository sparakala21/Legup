package edu.rpi.legup.controller;

import javafx.scene.input.MouseEvent;

public interface IMouseHandler{

    void onMouseClicked(MouseEvent e);
    void onMouseDragEntered(MouseEvent e);
    void onMouseDragExited(MouseEvent e);
    void onMouseDragged(MouseEvent e);
    void onMouseDragOver(MouseEvent e);
    void onMouseDragReleased(MouseEvent e);
    void onMouseEntered(MouseEvent e);
    void onMouseExited(MouseEvent e);
    void onMouseMoved(MouseEvent e);
    void onMousePressed(MouseEvent e);
    void onMouseReleased(MouseEvent e);
}
