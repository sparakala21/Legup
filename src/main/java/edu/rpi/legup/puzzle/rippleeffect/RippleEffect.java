package edu.rpi.legup.puzzle.rippleeffect;

import edu.rpi.legup.model.Puzzle;
import edu.rpi.legup.model.gameboard.Board;

public class RippleEffect extends Puzzle {

    /**
     * Initializes a RippleEffect puzzle.
     */
    public RippleEffect() {
        super();
        this.name = "Ripple Effect";
        this.importer = new RippleEffectImporter(this);
        this.exporter = new RippleEffectExporter(this);
    }

    /**
     * Initializes game board. Called by invoker of this RippleEffect instance.
     */
    @java.lang.Override
    public void initializeView() {
        this.boardView = new RippleEffectView((RippleEffectBoard) this.currentBoard);
        super.addBoardListener(this.boardView);
    }

    /**
     * Generates a random puzzle. Not implemented.
     * @param difficulty level of difficulty (1-10)
     * @return null
     */
    @java.lang.Override
    public Board generatePuzzle(int difficulty) {
        return null;
    }

    /**
     * Determines if puzzle is solved.
     * @param board board to check for validity
     * @return true if puzzle is solved, false otherwise
     */
    @java.lang.Override
    public boolean isBoardComplete(Board board) {
        // TODO need all cells filled with no contradictions
        return false;
    }

    @java.lang.Override
    public void onBoardChange(Board board) {
    }
}
