package edu.rpi.legup.puzzle.treetent.rules;

import edu.rpi.legup.model.gameboard.Board;
import edu.rpi.legup.model.gameboard.CaseBoard;
import edu.rpi.legup.model.gameboard.PuzzleElement;
import edu.rpi.legup.model.rules.CaseRule;
import edu.rpi.legup.model.tree.TreeTransition;
import edu.rpi.legup.puzzle.treetent.TreeTentBoard;
import edu.rpi.legup.puzzle.treetent.TreeTentCell;
import edu.rpi.legup.puzzle.treetent.TreeTentLine;
import edu.rpi.legup.puzzle.treetent.TreeTentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
public class LinkTentCaseRule extends CaseRule {

    public LinkTentCaseRule() {
        super("Links from tent",
                "A tent must link to exactly one adjacent tree.",
                "edu/rpi/legup/images/treetent/caseLinkTent.png");
    }

    @Override
    public CaseBoard getCaseBoard(Board board) {
        TreeTentBoard treeTentBoard = (TreeTentBoard) board.copy();
        treeTentBoard.setModifiable(false);
        CaseBoard caseBoard = new CaseBoard(treeTentBoard, this);
        for (PuzzleElement element : treeTentBoard.getPuzzleElements()) {
            if (((TreeTentCell) element).getType() == TreeTentType.TENT &&
                    !getCases(treeTentBoard, element).isEmpty()) {
                caseBoard.addPickableElement(element);
            }
        }
        return caseBoard;
    }

    /**
     * Gets the possible cases at a specific location based on this case rule
     *
     * @param board         the current board state
     * @param puzzleElement equivalent puzzleElement
     * @return a list of elements the specified could be
     */
    @Override
    public ArrayList<Board> getCases(Board board, PuzzleElement puzzleElement) {
        ArrayList<Board> cases = new ArrayList<>();
        TreeTentBoard treeTentBoard = (TreeTentBoard) board;
        TreeTentCell cell = (TreeTentCell) puzzleElement;
        List<TreeTentCell> adjCells = treeTentBoard.getAdjacent(cell, TreeTentType.TREE);
        for (TreeTentCell c : adjCells) {
            TreeTentBoard caseBoard = (TreeTentBoard) board.copy();
            TreeTentLine line = new TreeTentLine(cell, c);
            ArrayList<TreeTentLine> lines = caseBoard.getLines();
            boolean add = true;

            for (TreeTentLine l : lines) {
                TreeTentCell c1 = l.getC1();
                TreeTentCell c2 = l.getC2();
                if (c1.getLocation().equals(cell.getLocation())){
                    add = false;
                }
                if (c1.getLocation().equals(c.getLocation())){
                    add = false;
                }
                if (c2.getLocation().equals(cell.getLocation())){
                    add = false;
                }
                if (c2.getLocation().equals(c.getLocation())){
                    add = false;
                }
            }
            if(add) {
                caseBoard.getLines().add(line);
                caseBoard.addModifiedData(line);
                cases.add(caseBoard);
            }
        }
        return cases;
    }

    /**
     * Checks whether the transition logically follows from the parent node using this rule
     *
     * @param transition transition to check
     * @return null if the child node logically follow from the parent node, otherwise error message
     */
    @Override
    public String checkRuleRaw(TreeTransition transition) {
        return null;
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
        return null;
    }
}
