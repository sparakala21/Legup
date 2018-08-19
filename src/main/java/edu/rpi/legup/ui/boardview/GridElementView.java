package edu.rpi.legup.ui.boardview;

import edu.rpi.legup.model.gameboard.GridCell;

public abstract class GridElementView extends ElementView
{
    public GridElementView(GridCell cell)
    {
        super(cell);
    }
}
