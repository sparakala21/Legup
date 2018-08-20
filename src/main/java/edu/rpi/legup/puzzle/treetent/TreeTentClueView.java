package edu.rpi.legup.puzzle.treetent;

import edu.rpi.legup.ui.boardview.ElementView;

import java.awt.*;

public class TreeTentClueView extends ElementView
{

    private static final Font FONT = new Font("TimesRoman", Font.BOLD, 16);
    private static final Color FONT_COLOR = Color.BLACK;

    public TreeTentClueView(TreeTentClue clue)
    {
        super(clue);
    }

    /**
     * Gets the PuzzleElement associated with this view
     *
     * @return PuzzleElement associated with this view
     */
    @Override
    public TreeTentClue getPuzzleElement()
    {
        return (TreeTentClue)super.getPuzzleElement();
    }
}