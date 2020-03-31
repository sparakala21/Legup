package edu.rpi.legup.puzzle.rippleeffect;

import edu.rpi.legup.model.gameboard.GridBoard;
import edu.rpi.legup.model.gameboard.PuzzleElement;
import edu.rpi.legup.puzzle.nurikabe.NurikabeBoard;

/**
 * RippleEffect board class.
 */
public class RippleEffectBoard extends GridBoard {

    /**
     * Construct rectangular board.
     * @param width board width
     * @param height board height
     */
    public RippleEffectBoard(int width, int height) {
        super(width, height);
    }

    /**
     * Construct square board.
     * @param size side length
     */
    public RippleEffectBoard(int size) {
        super(size, size);
    }

    /**
     * Override to change return type.
     * @param x x location of the cell
     * @param y y location of the cell
     * @return cell at coordinates x,y
     */
    @Override
    public RippleEffectCell getCell(int x, int y) {
        return (RippleEffectCell) super.getCell(x, y);
    }

    @Override
    public RippleEffectBoard copy() {
        RippleEffectBoard copy =
                new RippleEffectBoard(dimension.width, dimension.height);
        for (int x = 0; x < this.dimension.width; x++) {
            for (int y = 0; y < this.dimension.height; y++) {
                copy.setCell(x, y, getCell(x, y).copy());
            }
        }
        for(PuzzleElement e : this.modifiedData) {
            copy.getPuzzleElement(e).setModifiable(false);
        }
        return copy;
    }
}
