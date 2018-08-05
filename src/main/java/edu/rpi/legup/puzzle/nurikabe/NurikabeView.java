package edu.rpi.legup.puzzle.nurikabe;

import edu.rpi.legup.controller.BoardController;
import edu.rpi.legup.ui.boardview.GridBoardView;
import javafx.geometry.Point2D;
import edu.rpi.legup.model.gameboard.PuzzleElement;

public class NurikabeView extends GridBoardView
{

    public NurikabeView(NurikabeBoard board)
    {
        super(new BoardController(), new NurikabeController(), board.getDimension());

        for(PuzzleElement puzzleElement : board.getPuzzleElements())
        {
            NurikabeCell cell = (NurikabeCell)element;
            Point2D loc = cell.getLocation();
            NurikabeElementView elementView = new NurikabeElementView(cell);
            elementView.setIndex(cell.getIndex());
            elementView.setSize(elementSize);
            elementView.setLocation(new Point2D(loc.getX() * elementSize.getWidth(), loc.getY() * elementSize.getHeight()));
            elementViews.add(elementView);
        }
    }
}
