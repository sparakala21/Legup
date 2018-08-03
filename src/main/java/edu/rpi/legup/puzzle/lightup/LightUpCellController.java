package edu.rpi.legup.puzzle.lightup;

import edu.rpi.legup.controller.ElementController;
import edu.rpi.legup.model.gameboard.PuzzleElement;

import java.awt.event.MouseEvent;

public class LightUpCellController extends ElementController
{
    @Override
    public void changeCell(MouseEvent e, PuzzleElement data)
    {
        LightUpCell cell = (LightUpCell)data;
        if(e.getButton() == MouseEvent.BUTTON1)
        {

        }
        else if(e.getButton() == MouseEvent.BUTTON3)
        {

        }
    }
}
