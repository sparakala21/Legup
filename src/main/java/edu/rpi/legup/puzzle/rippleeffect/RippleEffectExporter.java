package edu.rpi.legup.puzzle.rippleeffect;

import edu.rpi.legup.model.PuzzleExporter;
import edu.rpi.legup.model.gameboard.PuzzleElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class RippleEffectExporter extends PuzzleExporter {
    public RippleEffectExporter(RippleEffect re) {
        super(re);
    }

    @Override
    protected Element createBoardElement(Document doc) {
        RippleEffectBoard board = (RippleEffectBoard)
                super.puzzle.getTree().getRootNode().getBoard();
        Element boardE = doc.createElement("board");
        boardE.setAttribute("width", String.valueOf(board.getWidth()));
        boardE.setAttribute("height", String.valueOf(board.getHeight()));
        // collect nonzero cells into cellsE
        Element cellsE = doc.createElement("cells");
        for (PuzzleElement pe : board.getPuzzleElements()) {
            RippleEffectCell re = (RippleEffectCell) pe;
            if (re.getData() == 0) continue;
            Element cellE = doc.createElement("cell");
            cellE.setAttribute("value", String.valueOf(re.getData()));
            cellE.setAttribute("x", String.valueOf(re.getLocation().x));
            cellE.setAttribute("y", String.valueOf(re.getLocation().y));
            cellsE.appendChild(cellE);
        }
        boardE.appendChild(cellsE);
        return boardE;
    }
}
