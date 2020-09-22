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

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LastCampingSpotBasicRule extends BasicRule {

    public LastCampingSpotBasicRule() {
        super("Last Camping Spot",
                "If an unlinked tree is adjacent to only one blank cell and not adjacent to any unlinked tents, the blank cell must be a tent.",
                "edu/rpi/legup/images/treetent/oneTentPosition.png");
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
        for (int i = 0; i<10;i++){
            System.out.printf("check");
        }
        if (puzzleElement instanceof TreeTentLine) {
            System.out.printf("erro 1");
            return "Line is not valid for this rule.";
        }
        TreeTentBoard initialBoard = (TreeTentBoard) transition.getParents().get(0).getBoard();
        TreeTentCell initCell = (TreeTentCell) initialBoard.getPuzzleElement(puzzleElement);
        TreeTentBoard finalBoard = (TreeTentBoard) transition.getBoard();
        TreeTentCell finalCell = (TreeTentCell) finalBoard.getPuzzleElement(puzzleElement);
        if (!(initCell.getType() == TreeTentType.UNKNOWN && finalCell.getType() == TreeTentType.TENT)) {
            System.out.printf("erro 2");
            return "This cell must be a tent.";
        }

        if (isForced(initialBoard, initCell)) {
            System.out.printf("erro 3");
            return null;
        } else {
            System.out.printf("erro 4");
            return "This cell is not forced to be tent.";
        }
    }

    private boolean isForced(TreeTentBoard board, TreeTentCell cell) {
        List<TreeTentCell> adjTrees = board.getAdjacent(cell, TreeTentType.TREE);
        boolean validCase = false;
        for (TreeTentCell c : adjTrees) {
            boolean isvalid = true;
            Point loc = c.getLocation();
            for (TreeTentLine line : board.getLines()) {
                if (line.getC1().getLocation().equals(loc) || line.getC2().getLocation().equals(loc)) {
                    System.out.printf("erro 5");
                    isvalid = false;
                    //valideTrees.add(0);
                    //return false;

                }
            }
            List<TreeTentCell> adjTents = board.getAdjacent(c, TreeTentType.TENT);
            if (!adjTents.isEmpty()){
                for (TreeTentCell t : adjTents){
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
                    if (isLinked == false){
                        isvalid = false;
                        //valideTrees.add(0);
                        //return false;
                    }

                }
            }
            List<TreeTentCell> adjUnkown = board.getAdjacent(c, TreeTentType.UNKNOWN);
            if (adjUnkown.size() != 1){
                System.out.printf("erro 7");
                isvalid = false;
                //valideTrees.add(0);
                //return false;

            }
            if(isvalid){
                validCase = true;
            }
        }
        if(validCase){
            return true;
        }
        return false;
    }

    /**
     * Creates a transition {@link Board} that has this rule applied to it using the {@link TreeNode}.
     *
     * @param node tree node used to create default transition board
     * @return default board or null if this rule cannot be applied to this tree node
     */
    @Override
    public Board getDefaultBoard(TreeNode node) {
        TreeTentBoard treeTentBoard = (TreeTentBoard) node.getBoard().copy();
        for (PuzzleElement element : treeTentBoard.getPuzzleElements()) {
            TreeTentCell cell = (TreeTentCell) element;
            if (cell.getType() == TreeTentType.UNKNOWN && isForced(treeTentBoard, cell)) {
                cell.setData(TreeTentType.TENT.value);
                treeTentBoard.addModifiedData(cell);
            }
        }
        if (treeTentBoard.getModifiedData().isEmpty()) {
            return null;
        } else {
            return treeTentBoard;
        }
    }
}
