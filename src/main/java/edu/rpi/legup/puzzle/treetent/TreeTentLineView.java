package edu.rpi.legup.puzzle.treetent;

import edu.rpi.legup.ui.boardview.ElementView;

import java.awt.*;

public class TreeTentLineView extends ElementView
{
    private final Color LINE_COLOR = Color.GREEN;

    private final Stroke LINE_STROKE = new BasicStroke(2);

    public TreeTentLineView(TreeTentLine line)
    {
        super(line);
    }
}
