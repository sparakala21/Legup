package edu.rpi.legup.puzzle.rippleeffect;

import edu.rpi.legup.controller.BoardController;
import edu.rpi.legup.model.gameboard.PuzzleElement;
import edu.rpi.legup.ui.boardview.GridBoardView;

import java.awt.*;

public class RippleEffectView extends GridBoardView {
    public RippleEffectView(RippleEffectBoard b) {
        super(new BoardController(), new RippleEffectController(),
                b.getDimension());
        for (PuzzleElement pe : b.getPuzzleElements()) {
            RippleEffectCell rec = (RippleEffectCell) pe;
            Point loc = rec.getLocation();
            RippleEffectElementView ev = new RippleEffectElementView(rec);
            ev.setIndex(rec.getIndex());
            ev.setSize(this.elementSize);
            ev.setLocation(new Point());
            this.elementViews.add(ev);
        }
    }
}
