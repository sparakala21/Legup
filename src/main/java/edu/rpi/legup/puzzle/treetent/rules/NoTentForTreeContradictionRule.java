package edu.rpi.legup.puzzle.treetent.rules;

import edu.rpi.legup.model.gameboard.Board;
import edu.rpi.legup.model.gameboard.PuzzleElement;
import edu.rpi.legup.model.rules.ContradictionRule;
import edu.rpi.legup.model.tree.TreeNode;
import edu.rpi.legup.puzzle.treetent.TreeTentBoard;
import edu.rpi.legup.puzzle.treetent.TreeTentCell;
import edu.rpi.legup.puzzle.treetent.TreeTentType;
import edu.rpi.legup.puzzle.treetent.TreeTentLine;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NoTentForTreeContradictionRule extends ContradictionRule {

    public NoTentForTreeContradictionRule() {
        super("No Tent For Tree",
                "Each tree must link to a tent.",
                "edu/rpi/legup/images/treetent/contra_NoTentForTree.png");
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
        TreeTentBoard treeTentBoard = (TreeTentBoard) board;
        TreeTentCell cell = (TreeTentCell) puzzleElement;
        if (cell.getType() != TreeTentType.TREE) {
            return "This cell does not contain a contradiction at this location.";
        }
        List<TreeTentCell> adjTents = treeTentBoard.getAdjacent(cell, TreeTentType.TENT);
        int adjTent = 0;
        for (TreeTentCell t : adjTents){
            boolean isLinked = false;

            ArrayList<TreeTentLine> lines = treeTentBoard.getLines();
            for (TreeTentLine l : lines) {
                TreeTentCell c1 = l.getC1();
                TreeTentCell c2 = l.getC2();
                if (c1.getLocation().equals(t.getLocation())){
                    isLinked = true;
                }
                if (c2.getLocation().equals(t.getLocation())) {
                    isLinked = true;
                }
            }
            if (isLinked == false ){
                adjTent += 1;
            }


        }
        int adjUnknown = treeTentBoard.getAdjacent(cell, TreeTentType.UNKNOWN).size();
        if (adjTent == 0 && adjUnknown == 0) {
            return null;
        } else {
            return "This cell does not contain a contradiction at this location.";
        }
    }
}
