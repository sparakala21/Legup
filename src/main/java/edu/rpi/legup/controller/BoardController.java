package edu.rpi.legup.controller;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class BoardController extends Controller
{
    protected Point2D lastLeftMousePoint;
    protected Point2D lastRightMousePoint;

    /**
     * BoardController Constructor - creates a edu.rpi.legup.controller object to listen
     * to edu.rpi.legup.ui events from a DynamicViewer
     */
    public BoardController()
    {
        super();
        lastLeftMousePoint = null;
        lastRightMousePoint = null;
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

    /**
     * Mouse Pressed event - sets the cursor to the move cursor and stores
     * info for possible panning
     *
     * @param e MouseEvent object
     */
    @Override
    public void onMousePressed(MouseEvent e)
    {
        super.onMousePressed(e);
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
        super.onMouseReleased(e);
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
        super.onMouseDragged(e);
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
