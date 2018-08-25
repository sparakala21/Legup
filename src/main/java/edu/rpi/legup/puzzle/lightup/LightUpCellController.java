package edu.rpi.legup.puzzle.lightup;

import edu.rpi.legup.controller.ElementController;
import edu.rpi.legup.model.gameboard.Element;

import java.awt.event.MouseEvent;

public class LightUpCellController extends ElementController
{
    @Override
    public void changeCell(MouseEvent e, Element data)
    {
        LightUpCell cell = (LightUpCell)data;
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            if(cell.getData() >= 4) {
                cell.setData(-2);
            } else {
                cell.setData(cell.getData() + 1);
            }
        }
        else if(e.getButton() == MouseEvent.BUTTON3)
        {
            if(cell.getData() <= -2) {
                cell.setData(4);
            } else {
                cell.setData(cell.getData() - 1);
            }
        }
    }
}
