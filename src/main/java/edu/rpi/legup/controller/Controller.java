package edu.rpi.legup.controller;

import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import edu.rpi.legup.ui.DynamicViewer;

public abstract class Controller implements IMouseHandler
{
    protected DynamicViewer viewer;
    private double x, y;
    private boolean pan;

    /**
     * Controller Constructor - creates a controller object to listen
     * to ui events from a DynamicViewer
     */
    public Controller()
    {
        x = y = -1;
        pan = false;
    }

    public void setViewer(DynamicViewer viewer)
    {
        this.viewer = viewer;
    }

    /**
     * Mouse Clicked event - no default action
     *
     * @param e MouseEvent object
     */
    @Override
    public void onMouseClicked(MouseEvent e)
    {

    }

    public void onMouseDragEntered(MouseEvent e)
    {

    }
    public void onMouseDragExited(MouseEvent e)
    {

    }
    public void onMouseDragOver(MouseEvent e){

    }
    public void onMouseDragReleased(MouseEvent e){

    }

    /**
     * Mouse Pressed event - sets the cursor to the move cursor and stores
     * info for possible panning
     *
     * @param e MouseEvent object
     */
    @Override
    public void onMousePressed(MouseEvent e)
    {
        if(e.getButton() == MouseButton.PRIMARY)
        {
            pan = true;
            x = e.getX();
            y = e.getY();
            viewer.setCursor(javafx.scene.Cursor.CLOSED_HAND);
        }
    }

    /**
     * Mouse Released event - sets the cursor back to the default cursor and reset
     * info for panning
     *
     * @param e MouseEvent object
     */
    @Override
    public void onMouseReleased(MouseEvent e)
    {
        if(e.getButton() == MouseButton.PRIMARY)
        {
            pan = false;
            viewer.setCursor(Cursor.DEFAULT);
        }
    }

    /**
     * Mouse Entered event - no default action
     *
     * @param e MouseEvent object
     */
    @Override
    public void onMouseEntered(MouseEvent e)
    {

    }

    /**
     * Mouse Exited event - no default action
     *
     * @param e MouseEvent object
     */
    @Override
    public void onMouseExited(MouseEvent e)
    {

    }

    /**
     * Mouse Dragged event - adjusts the viewport
     *
     * @param e MouseEvent object
     */
    @Override
    public void onMouseDragged(MouseEvent e)
    {

    }

    /**
     * Mouse Moved event - no default action
     *
     * @param e MouseEvent object
     */
    @Override
    public void onMouseMoved(MouseEvent e)
    {

    }

}