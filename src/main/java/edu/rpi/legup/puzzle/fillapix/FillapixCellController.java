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
        FillapixCell cell = (FillapixCell) data;
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            int val = cell.getData();
            if(val >= 9) {
                cell.setData(-1);
            } else {
                cell.setData(++val);
            }
        } else {
            int val = cell.getData();
            if(val <= -1) {
                cell.setData(9);
            } else {
                cell.setData(--val);
            }
        }
    }
}