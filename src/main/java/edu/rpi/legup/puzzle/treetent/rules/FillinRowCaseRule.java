package edu.rpi.legup.puzzle.treetent.rules;

import edu.rpi.legup.model.gameboard.Board;
import edu.rpi.legup.model.gameboard.CaseBoard;
import edu.rpi.legup.model.gameboard.PuzzleElement;
import edu.rpi.legup.model.rules.CaseRule;
import edu.rpi.legup.model.tree.TreeTransition;
import edu.rpi.legup.puzzle.treetent.TreeTentBoard;
import edu.rpi.legup.puzzle.treetent.TreeTentCell;
import edu.rpi.legup.puzzle.treetent.TreeTentClue;
import edu.rpi.legup.puzzle.treetent.TreeTentType;
import edu.rpi.legup.model.gameboard.GridBoard;

import java.util.ArrayList;

public class FillinRowCaseRule extends CaseRule {

    public FillinRowCaseRule() {
        super("Fill In row",
                "A row must have the number of tents of its clue.",
                "edu/rpi/legup/images/treetent/case_rowcount.png");
    }

    @Override
    public CaseBoard getCaseBoard(Board board) {
        TreeTentBoard treeTentBoard = (TreeTentBoard) board.copy();
        treeTentBoard.setModifiable(false);
        CaseBoard caseBoard = new CaseBoard(treeTentBoard, this);
        for (PuzzleElement element : treeTentBoard.getColClues()) {
        	System.out.printf("%d\n", ((TreeTentClue) element).getType().value);
            if (((TreeTentClue) element).getType() == TreeTentType.CLUE_SOUTH) {
            	System.out.printf("%d\n", ((TreeTentClue) element).getType().value);
                caseBoard.addPickableElement(element);
            }
        }
        for (PuzzleElement element : treeTentBoard.getRowClues()) {
        	System.out.printf("%d\n", ((TreeTentClue) element).getType().value);
            if (((TreeTentClue) element).getType() == TreeTentType.CLUE_EAST) {
            	System.out.printf("%d\n", ((TreeTentClue) element).getType().value);
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
    	int index = ((TreeTentClue)puzzleElement).getClueIndex();
    	int clue = ((TreeTentClue)puzzleElement).getData();
    	if(((TreeTentClue)puzzleElement).getType() == TreeTentType.CLUE_EAST) { // change row
    		int count = get_tent_num((TreeTentBoard)board, (TreeTentClue)puzzleElement);
    		clue -= count;
    		place_tents(cases, (TreeTentBoard) board, (TreeTentClue) puzzleElement, clue, -1);
    	}
    	if(((TreeTentClue)puzzleElement).getType() == TreeTentType.CLUE_SOUTH) { // change col
    		int count = get_tent_num((TreeTentBoard)board, (TreeTentClue)puzzleElement);
    		clue -= count;
    		place_tents(cases, (TreeTentBoard) board, (TreeTentClue) puzzleElement, clue, -1);
    		System.out.printf("%d", count);
    		
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
    
    private int get_tent_num(TreeTentBoard board, TreeTentClue puzzleElement) {
    	int count = 0;
    	int index = ((TreeTentClue)puzzleElement).getClueIndex();
    	if(((TreeTentClue)puzzleElement).getType() == TreeTentType.CLUE_EAST) { // change row
    	    for(int i = 0; i < board.getWidth(); i++) {
    	    	TreeTentCell cell = ((TreeTentBoard)board).getCell(i, index-1);
    	    	if(cell.getType() == TreeTentType.TENT) {
    	    		count += 1;
    	    	}
    	    }
    		
    	}
    	if(((TreeTentClue)puzzleElement).getType() == TreeTentType.CLUE_SOUTH) { // change col
    		for(int i = 0; i < board.getHeight(); i++) {
    	    	TreeTentCell cell = ((TreeTentBoard)board).getCell(index-1, i);
    	    	if(cell.getType() == TreeTentType.TENT) {
    	    		count += 1;
    	    	}
    	    }
    	}
    	return count;
    }
    
    private void place_tents(ArrayList<Board> cases, TreeTentBoard board, TreeTentClue puzzleElement, int clue, int last_tent) {
    	int index = ((TreeTentClue)puzzleElement).getClueIndex();
    	if(clue == 0) {
			Board cas = board.copy();
			if(((TreeTentClue)puzzleElement).getType() == TreeTentType.CLUE_EAST) { // change row
	    	    for(int i = 0; i < board.getWidth(); i++) {
	    	    	TreeTentCell cell = ((TreeTentBoard)board).getCell(i, index-1);
	    	    	if(cell.getType() == TreeTentType.UNKNOWN) {
	    	    		PuzzleElement data = cas.getPuzzleElement(cell);
	        	        data.setData(TreeTentType.GRASS.value);
	        	        cas.addModifiedData(data);
	    	    	}
	    	    }
	    	}
	    	if(((TreeTentClue)puzzleElement).getType() == TreeTentType.CLUE_SOUTH) { // change col
	    		for(int i = 0; i < board.getHeight(); i++) {
	    	    	TreeTentCell cell = ((TreeTentBoard)board).getCell(index-1, i);
	    	    	if(cell.getType() == TreeTentType.UNKNOWN) {
	    	    		PuzzleElement data = cas.getPuzzleElement(cell);
	        	        data.setData(TreeTentType.GRASS.value);
	        	        cas.addModifiedData(data);
	    	    	}
	    	    }
	    	}
	    	cases.add(cas);
		}
    	if(((TreeTentClue)puzzleElement).getType() == TreeTentType.CLUE_EAST) { // change row
    		clue -= 1;
    		for(int i = last_tent+1; i < board.getWidth(); i++) {
    			TreeTentCell cell = ((TreeTentBoard)board).getCell(i, index-1);
    	    	if(cell.getType() == TreeTentType.UNKNOWN) {
    	    		PuzzleElement data = board.getPuzzleElement(cell);
        	        data.setData(TreeTentType.TENT.value);
        	        board.addModifiedData(data);
        	        place_tents(cases, board, puzzleElement, clue, i);
        	        data.setData(TreeTentType.UNKNOWN.value);
        	        board.addModifiedData(data);
    	    	}
        	}
    	}
    	if(((TreeTentClue)puzzleElement).getType() == TreeTentType.CLUE_SOUTH) { // change col
    		clue -= 1;
    		for(int i = last_tent+1; i < board.getHeight(); i++) {
    			TreeTentCell cell = ((TreeTentBoard)board).getCell(index-1, i);
    	    	if(cell.getType() == TreeTentType.UNKNOWN) {
    	    		PuzzleElement data = board.getPuzzleElement(cell);
        	        data.setData(TreeTentType.TENT.value);
        	        board.addModifiedData(data);
        	        place_tents(cases, board, puzzleElement, clue, i);
        	        data.setData(TreeTentType.UNKNOWN.value);
        	        board.addModifiedData(data);
    	    	}
        	}
    	}
    }
}
