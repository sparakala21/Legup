package edu.rpi.legup.puzzle.treetent.rules;

import edu.rpi.legup.model.gameboard.Board;
import edu.rpi.legup.model.gameboard.PuzzleElement;
import edu.rpi.legup.model.rules.BasicRule;
import edu.rpi.legup.model.tree.TreeNode;
import edu.rpi.legup.model.tree.TreeTransition;
import edu.rpi.legup.puzzle.treetent.TreeTentBoard;
import edu.rpi.legup.puzzle.treetent.TreeTentCell;
import edu.rpi.legup.puzzle.treetent.TreeTentLine;
import edu.rpi.legup.puzzle.treetent.TreeTentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TentForTreeBasicRule extends BasicRule {

    public TentForTreeBasicRule() {
        super("Tent for Tree",
                "If only one unlinked tent and no blank cells are adjacent to an unlinked tree, the unlinked tree must link to the unlinked tent.",
                "edu/rpi/legup/images/treetent/NewTreeLink.png");
    }

    /**
     * Checks whether the child node logically follows from the parent node
     * at the specific puzzleElement index using this rule
     *
     * @param transition    transition to check
     * @param puzzleElement equivalent puzzleElement
     * @return null if the child node logically follow from the parent node at the specified puzzleElement,
     * otherwise error message
     */
    @Override
    public String checkRuleRawAt(TreeTransition transition, PuzzleElement puzzleElement) {
        if (!(puzzleElement instanceof TreeTentLine)) {
            return "Lines must be created for this rule.";
        }
        TreeTentBoard initialBoard = (TreeTentBoard) transition.getParents().get(0).getBoard();
        TreeTentLine initLine = (TreeTentLine) initialBoard.getPuzzleElement(puzzleElement);
        TreeTentBoard finalBoard = (TreeTentBoard) transition.getBoard();
        TreeTentLine finalLine = (TreeTentLine) finalBoard.getPuzzleElement(puzzleElement);
        TreeTentCell tree, tent;
        if (finalLine.getC1().getType() == TreeTentType.TREE && finalLine.getC2().getType() == TreeTentType.TENT) {
            tree = finalLine.getC1();
            tent = finalLine.getC2();
        } else if (finalLine.getC2().getType() == TreeTentType.TREE && finalLine.getC1().getType() == TreeTentType.TENT) {
            tree = finalLine.getC2();
            tent = finalLine.getC1();
        } else {
            return "This line must connect a tree to a tent.";
        }

        if (isForced(initialBoard, tree, tent)) {
            return null;
        } else {
            return "This cell is not forced to be tent.";
        }
    }


    private boolean isForced(TreeTentBoard board, TreeTentCell tree, TreeTentCell tent) {
        List<TreeTentCell> tents = board.getAdjacent(tree, TreeTentType.TENT);
        for (TreeTentCell t : tents){
            boolean isLinked = false;
            ArrayList<TreeTentLine> lines = board.getLines();
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
            if (isLinked == false && !t.getLocation().equals(tent.getLocation())){
                return false;
            }

        }
        List<TreeTentCell> adjUnknown = board.getAdjacent(tree, TreeTentType.UNKNOWN);
        if (!adjUnknown.isEmpty()){
            return false;
        }
        return true;

    }

    /**
     * Creates a transition {@link Board} that has this rule applied to it using the {@link TreeNode}.
     *
     * @param node tree node used to create default transition board
     * @return default board or null if this rule cannot be applied to this tree node
     */
    @Override
    public Board getDefaultBoard(TreeNode node) {
        return null;
    }
}
