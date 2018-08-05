package edu.rpi.legup.puzzle.fillapix;

import edu.rpi.legup.controller.ElementController;
import edu.rpi.legup.model.gameboard.Element;

import java.awt.*;
import java.awt.event.MouseEvent;

public class FillapixCellController extends ElementController
{
    @Override
    public void changeCell(MouseEvent e, Element data)
    {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            if(e.isControlDown())
            {

            }
            else
            {

            }
        }
    }
}