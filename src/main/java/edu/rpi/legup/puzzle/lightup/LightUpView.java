package edu.rpi.legup.puzzle.lightup;

import edu.rpi.legup.controller.BoardController;
import edu.rpi.legup.model.gameboard.Board;
import edu.rpi.legup.model.gameboard.CaseBoard;
import edu.rpi.legup.model.gameboard.PuzzleElement;
import edu.rpi.legup.ui.boardview.GridBoardView;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class LightUpView extends GridBoardView
{
    static Image lightImage;
    static
    {
        lightImage = new Image(ClassLoader.getSystemClassLoader().getResourceAsStream("images/lightup/light.png"));
    }

    public LightUpView(LightUpBoard board)
    {
        super(new BoardController(), new LightUpCellController(), new Dimension2D(board.getWidth(), board.getHeight()));

        for(PuzzleElement puzzleElement : board.getPuzzleElements())
        {
            LightUpCell cell = (LightUpCell) puzzleElement;
            int x = cell.getX();
            int y = cell.getY();
            LightUpElementView elementView = new LightUpElementView(cell);
            elementView.setIndex(cell.getIndex());
            elementView.setSize(elementSize);
            elementView.setLocation(new Point2D(x * elementSize.getWidth(), y * elementSize.getHeight()));
            elementViews.add(elementView);
        }
    }

    @Override
    public void onBoardChanged(Board board)
    {
        setBoard(board);
        LightUpBoard lightUpBoard = board instanceof CaseBoard ? (LightUpBoard)((CaseBoard)board).getBaseBoard() : (LightUpBoard)board;
        lightUpBoard.fillWithLight();
    }
}
