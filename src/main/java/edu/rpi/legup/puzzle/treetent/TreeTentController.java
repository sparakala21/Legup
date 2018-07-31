package edu.rpi.legup.puzzle.treetent;

import edu.rpi.legup.controller.ElementController;
import edu.rpi.legup.model.gameboard.Element;

import java.awt.event.MouseEvent;


public class TreeTentController extends ElementController
{

    private TreeTentElementView lastCellPressed;
    private TreeTentElementView dragStart;
    public TreeTentController()
    {
        super();
        this.dragStart = null;
        this.lastCellPressed = null;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
//        Board board = getInstance().getBoard();
//        Tree tree = getInstance().getTree();
//        TreeView treeView = GameBoardFacade.getInstance().getLegupUI().getTreePanel().getTreeView();
//        BoardView boardView = getInstance().getLegupUI().getBoardView();
//        dragStart = (TreeTentElementView) boardView.getElement(e.getPoint());
//        lastCellPressed = (TreeTentElementView) boardView.getElement(e.getPoint());
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
//        TreeTentBoard board = (TreeTentBoard)getInstance().getBoard();
//        Tree tree = getInstance().getTree();
//        TreeView treeView = GameBoardFacade.getInstance().getLegupUI().getTreePanel().getTreeView();
//        BoardView boardView = getInstance().getLegupUI().getBoardView();
//        TreeTentElementView element = (TreeTentElementView) boardView.getElement(e.getPoint());
//        if(lastCellPressed != null && element != null)
//        {
//            TreeTentLine line = new MasyuLine((TreeTentCell) lastCellPressed.getElement(), (TreeTentCell) element.getElement());
//            board.getLines().add(line);
//            board.getModifiedData().add(line);
//            boardView.updateBoard(board);
//        }
//        lastCellPressed = element;
    }
    @Override
    public void mouseReleased(MouseEvent e)
    {
        super.mouseReleased(e);
//        TreeTentElementView dragEnd = (TreeTentElementView) boardView.getElement(e.getPoint());
//        TreeView treeView = GameBoardFacade.getInstance().getLegupUI().getTreePanel().getTreeView();
//        BoardView boardView = getInstance().getLegupUI().getBoardView();
//        TreeTentBoard board = (TreeTentBoard)getInstance().getBoard();
//        TreeTentElementView element = (TreeTentElementView) boardView.getElement(e.getPoint());
//        if(lastCellPressed != null && element != null)
//        {
//            TreeTentLine line = new TreeTentLine((TreeTentCell) lastCellPressed.getElement(), (TreeTentCell) element.getElement());
//            board.getLines().add(line);
//            board.getModifiedData().add(line);
//            boardView.onBoardChanged(board);
//        }
    }
    @Override
    public void changeCell(MouseEvent e, Element element)
    {
        if(element instanceof TreeTentCell) {
            TreeTentCell cell = (TreeTentCell) element;
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (cell.getData() >= 3) {
                    cell.setData(0);
                } else {
                    cell.setData(cell.getData() + 1);
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                if (cell.getData() <= 0) {
                    cell.setData(3);
                } else {
                    cell.setData(cell.getData() - 1);
                }
            }
        }
        else
        {
            TreeTentClue clue = (TreeTentClue) element;
            int max;
            if(clue.getType() == TreeTentType.CLUE_EAST)
            {
                max = boardView.getHeight();
            }
            else
            {
                max = boardView.getWidth();
            }

            if (e.getButton() == MouseEvent.BUTTON1) {
                if (clue.getData() >= max) {
                    clue.setData(0);
                } else {
                    clue.setData(clue.getData() + 1);
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                if (clue.getData() <= 0) {
                    clue.setData(max);
                } else {
                    clue.setData(clue.getData() - 1);
                }
            }
        }
    }
}
