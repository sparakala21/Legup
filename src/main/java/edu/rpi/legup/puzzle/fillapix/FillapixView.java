package edu.rpi.legup.puzzle.fillapix;

import edu.rpi.legup.controller.BoardController;
import edu.rpi.legup.model.gameboard.Board;
import edu.rpi.legup.model.gameboard.Element;
import edu.rpi.legup.ui.boardview.ElementView;
import edu.rpi.legup.ui.boardview.GridBoardView;
import javafx.geometry.Point2D;

public class FillapixView extends GridBoardView
{
    public FillapixView(FillapixBoard board)
    {
        super(new BoardController(), new FillapixCellController(), board.getDimension());

        for(Element element : board.getElementData())
        {
            FillapixCell cell = (FillapixCell)element;
            Point2D loc = cell.getLocation();
            FillapixElementView elementView = new FillapixElementView(cell);
            elementView.setIndex(cell.getIndex());
            elementView.setSize(elementSize);
            elementView.setLocation(new Point2D(loc.getX() * elementSize.getWidth(), loc.getY() * elementSize.getHeight()));
            elementViews.add(elementView);
        }
    }

    /**
     * Board Data changed
     *
     * @param board board to update the BoardView
     */
    public void onBoardChanged(Board board)
    {
        FillapixBoard fillapixBoard = (FillapixBoard) board;
        for(ElementView element : elementViews)
        {
            element.setElement(fillapixBoard.getElementData(element.getElement()));
        }
    }
}