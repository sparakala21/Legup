package edu.rpi.legup.ui.boardview;

import edu.rpi.legup.controller.BoardController;
import edu.rpi.legup.controller.ElementController;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class GridBoardView extends BoardView
{
    protected Dimension2D gridSize;
    protected Dimension2D elementSize;

    /**
     * GridBoardView Constructor - creates a GridBoardView object using
     * the controller handle the ui events
     *
     * @param boardController controller that handles the ui events
     * @param gridSize dimension of the grid
     */
    public GridBoardView(BoardController boardController, ElementController elementController, Dimension2D gridSize)
    {
        this(boardController, elementController);
        this.gridSize = gridSize;
        this.elementSize = new Dimension2D(30,30);
        initSize();
    }

    /**
     * GridBoardView Constructor - creates a GridBoardView object using
     * the controller handle the ui events
     *
     * @param boardController controller that handles the ui events
     */
    private GridBoardView(BoardController boardController, ElementController elementController)
    {
        super(boardController, elementController);
    }

    /**
     * Gets the GridElementView from the puzzleElement index or
     * null if out of bounds
     *
     * @param index index of the ElementView
     * @return GridElementView at the specified index
     */
    public GridElementView getElement(int index)
    {
        if(index < elementViews.size())
        {
            return (GridElementView) elementViews.get(index);
        }
        return null;
    }

    /**
     * Gets the GridElementView from the location specified or
     * null if one does not exists at that location
     *
     * @param point location on the viewport
     * @return GridElementView at the specified location
     */
    public GridElementView getElement(Point2D point)
    {
        for(ElementView element: elementViews)
        {
            if(element.isWithinBounds(point))
            {
                return (GridElementView) element;
            }
        }
        return null;
    }

    /**
     * Initializes the initial dimension of the viewport for the GridBoardView
     */
    @Override
    public void initSize()
    {
    }

    /**
     * Helper method to determine the proper dimension of the grid view
     *
     * @return proper dimension of the grid view
     */
    protected Dimension2D getProperSize()
    {
        Dimension2D boardViewSize = new Dimension2D(gridSize.getWidth() * elementSize.getWidth(),
                gridSize.getHeight() * elementSize.getHeight());
        return boardViewSize;
    }
}

