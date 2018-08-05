package edu.rpi.legup.puzzle.nurikabe;

import edu.rpi.legup.ui.boardview.GridElementView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class NurikabeElementView extends GridElementView
{

    private static final Font FONT = new Font("TimesRoman", 16);
    private static final Color FONT_COLOR = Color.BLACK;

    public NurikabeElementView(NurikabeCell cell)
    {
        super(cell);
    }

    /**
     * Gets the Element associated with this view
     *
     * @return Element associated with this view
     */
    @Override
    public NurikabeCell getElement()
    {
        return (NurikabeCell)super.getElement();
    }

}
