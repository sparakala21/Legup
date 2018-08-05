package edu.rpi.legup.puzzle.sudoku;

import edu.rpi.legup.controller.BoardController;
import edu.rpi.legup.controller.ElementController;
import javafx.geometry.Dimension2D;
import edu.rpi.legup.model.gameboard.Element;
import edu.rpi.legup.ui.boardview.*;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class SudokuView extends GridBoardView
{
    private static final Color STROKE_COLOR = new Color(0,0,0,1);
    private static final double MINOR_STOKE = 1.0;
    private static final double MAJOR_STOKE = 4.0;

    public SudokuView(SudokuBoard board)
    {
        super(new BoardController(), new ElementController(), board.getDimension());

        for(Element element : board.getElementData())
        {
            SudokuCell cell = (SudokuCell)element;
            Point2D loc = cell.getLocation();
            SudokuElementView elementView = new SudokuElementView(cell);
            elementView.setIndex(cell.getIndex());
            elementView.setSize(elementSize);
            elementView.setLocation(new Point2D(loc.getX() * elementSize.getWidth(), loc.getY() * elementSize.getHeight()));
            elementViews.add(elementView);
        }
    }

    /**
     * Gets the SudokuElementView from the element index or
     * null if out of bounds
     *
     * @param index index of the ElementView
     * @return SudokuElementView at the specified index
     */
    @Override
    public SudokuElementView getElement(int index)
    {
        return (SudokuElementView ) super.getElement(index);
    }
}
