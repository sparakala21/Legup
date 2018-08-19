package edu.rpi.legup.puzzle.sudoku;

import edu.rpi.legup.model.gameboard.GridCell;
import edu.rpi.legup.model.gameboard.PuzzleElement;
import edu.rpi.legup.ui.boardview.GridElementView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SudokuElementView extends GridElementView
{
    private static final Font FONT = new Font("TimesRoman", 16);
    private static final Color FONT_COLOR = Color.BLACK;
    private static final Color BORDER_COLOR = Color.BLACK;

    public SudokuElementView(GridCell cell)
    {
        super(cell);
    }

    /**
     * Gets the PuzzleElement associated with this view
     *
     * @return PuzzleElement associated with this view
     */
    @Override
    public SudokuCell getPuzzleElement()
    {
        return (SudokuCell) super.getPuzzleElement();
    }

    @Override
    public void onPuzzleElementDataChange(PuzzleElement puzzleElement) {

    }
}
