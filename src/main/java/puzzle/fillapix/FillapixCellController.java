package puzzle.fillapix;

import controller.ElementController;
import model.gameboard.Element;

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