package edu.rpi.legup.puzzle.nurikabe;

import edu.rpi.legup.controller.ElementController;
import edu.rpi.legup.model.gameboard.Element;

import java.awt.event.MouseEvent;

public class NurikabeController extends ElementController
{

    @Override
    public void changeCell(MouseEvent e, Element data)
    {
        NurikabeCell cell = (NurikabeCell)data;
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            if(e.isControlDown())
            {
                this.boardView.getSelectionPopupMenu().show(boardView, this.boardView.getCanvas().getX() + e.getX(), this.boardView.getCanvas().getY() + e.getY());
            }
            else
            {
                data.setData((Integer)data.getData() + 1);
            }
        }
        else if(e.getButton() == MouseEvent.BUTTON3)
        {
            if((Integer)data.getData() > -2)
            {
                data.setData((Integer)data.getData() - 1);
            }
        }
    }
}
