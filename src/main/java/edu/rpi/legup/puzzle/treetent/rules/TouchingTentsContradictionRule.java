package edu.rpi.legup.puzzle.treetent.rules;

import edu.rpi.legup.model.gameboard.Board;
import edu.rpi.legup.model.gameboard.PuzzleElement;
import edu.rpi.legup.model.rules.ContradictionRule;
import edu.rpi.legup.puzzle.treetent.TreeTentBoard;
import edu.rpi.legup.puzzle.treetent.TreeTentCell;
import edu.rpi.legup.puzzle.treetent.TreeTentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TouchingTentsContradictionRule extends ContradictionRule {

    public TouchingTentsContradictionRule() {
        super("Touching Tents",
                "Tents cannot touch other tents.",
                "edu/rpi/legup/images/treetent/contra_adjacentTents.png");
    }

    /**
     * Checks whether the transition has a contradiction at the specific puzzleElement index using this rule
     *
     * @param board         board to check contradiction
     * @param puzzleElement equivalent puzzleElement
     * @return null if the transition contains a contradiction at the specified puzzleElement,
     * otherwise error message
     */
    @Override
    public String checkContradictionAt(Board board, PuzzleElement puzzleElement) {
        TreeTentBoard treeTentBoard = (TreeTentBoard) board.copy();
        List<TreeTentCell> Tents = new ArrayList<TreeTentCell>();
        TreeTentCell cell = (TreeTentCell) puzzleElement;
        for (PuzzleElement element : treeTentBoard.getPuzzleElements()) {
            if (((TreeTentCell) element).getType() == TreeTentType.TENT) {
                Tents.add((TreeTentCell)element);
            }
        }
        if (Tents.size() == 0) {
            return "No tent exist, no contradiction";
        }

        for (TreeTentCell t : Tents){
            List<TreeTentCell> adjTents = treeTentBoard.getAdjacent(t, TreeTentType.TENT);
            List<TreeTentCell> diaTents = treeTentBoard.getDiagonals(t, TreeTentType.TENT);
            if (!adjTents.isEmpty()){
                return null;
            }
            if (!diaTents.isEmpty()){
                return null;
            }

        }
        return "This cell does not contain a contradiction at this location.";
    }
}
