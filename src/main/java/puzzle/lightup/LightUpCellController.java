package puzzle.lightup;

import controller.ElementController;
import model.gameboard.Element;

import java.awt.event.MouseEvent;

public class LightUpCellController extends ElementController
{
    @Override
    public void changeCell(MouseEvent e, Element data)
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
