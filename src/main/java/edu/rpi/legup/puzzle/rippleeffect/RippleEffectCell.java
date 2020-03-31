package edu.rpi.legup.puzzle.rippleeffect;

import edu.rpi.legup.model.gameboard.GridCell;

import java.awt.*;

/**
 * RippleEffectCell as a GridCell with location and integer.
 */
public class RippleEffectCell extends GridCell<Integer> {
    /**
     * Create a cell object.
     * @param val 0 for empty, positive for numbered cells
     * @param loc location in the grid
     */
    public RippleEffectCell(int val, Point loc) {
        super(val, loc);
        if (val < 0)
            throw new IllegalArgumentException("cell value cannot be negative");
    }

    @Override
    public RippleEffectCell copy() {
        RippleEffectCell rec = new RippleEffectCell(this.data,
                (Point) this.location.clone());
        rec.setIndex(this.index);
        rec.setModifiable(this.isModifiable);
        rec.setGiven(this.isGiven);
        return rec;
    }
}
