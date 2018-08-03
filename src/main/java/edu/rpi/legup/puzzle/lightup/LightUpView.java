package edu.rpi.legup.puzzle.lightup;

import edu.rpi.legup.controller.BoardController;
import edu.rpi.legup.model.gameboard.Board;
import edu.rpi.legup.model.gameboard.CaseBoard;
import edu.rpi.legup.model.gameboard.PuzzleElement;
import edu.rpi.legup.ui.boardview.DataSelectionView;
import edu.rpi.legup.ui.boardview.GridBoardView;
import edu.rpi.legup.ui.boardview.SelectionItemView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LightUpView extends GridBoardView
{
    static Image lightImage;
    static
    {
        try
        {
            lightImage = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("images/lightup/light.png"));
        }
        catch(IOException e)
        {

        }
    }

    public LightUpView(LightUpBoard board)
    {
        super(new BoardController(), new LightUpCellController(), board.getDimension());

        for(PuzzleElement puzzleElement : board.getPuzzleElements())
        {
            LightUpCell cell = (LightUpCell) puzzleElement;
            Point loc = cell.getLocation();
            LightUpElementView elementView = new LightUpElementView(cell);
            elementView.setIndex(cell.getIndex());
            elementView.setSize(elementSize);
            elementView.setLocation(new Point(loc.x * elementSize.width, loc.y * elementSize.height));
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
