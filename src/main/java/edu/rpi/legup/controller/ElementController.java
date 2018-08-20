package edu.rpi.legup.controller;

import edu.rpi.legup.model.gameboard.PuzzleElement;
import edu.rpi.legup.ui.boardview.BoardView;
import javafx.scene.input.MouseEvent;

public class ElementController extends Controller
{
    protected BoardView boardView;

    /**
     * ElementController - puzzleElement edu.rpi.legup.controller to handles edu.rpi.legup.ui events
     * associated interacting with a ElementView
     */
    public ElementController()
    {
        this.boardView = null;
    }

    public void setBoardView(BoardView boardView)
    {
        this.boardView = boardView;
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void onMouseReleased(MouseEvent e)
    {
//        TreePanel treePanel = GameBoardFacade.getInstance().getLegupUI().getTreePanel();
//        TreeView treeView = treePanel.getTreeView();
//        BoardView boardView = getInstance().getLegupUI().getBoardView();
//        Board board = boardView.getBoard();
//        ElementView elementView = boardView.getElement(e.getPoint());
//        TreeViewSelection selection = treeView.getSelection();
//
//        if(elementView != null)
//        {
//            if(board instanceof CaseBoard)
//            {
//                if(elementView.isCaseRulePickable())
//                {
//                    CaseRuleCommand caseRuleCommand = new CaseRuleCommand(elementView, selection, ((CaseBoard)board).getCaseRule());
//                    if(caseRuleCommand.canExecute())
//                    {
//                        caseRuleCommand.execute();
//                        getInstance().getHistory().pushChange(caseRuleCommand);
//                    }
//                    else
//                    {
//                        treePanel.updateError(caseRuleCommand.getExecutionError());
//                    }
//                }
//            }
//            else
//            {
//                ICommand edit = new EditDataCommand(elementView, selection, e);
//                if(edit.canExecute())
//                {
//                    edit.execute();
//                    getInstance().getHistory().pushChange(edit);
//                    treePanel.updateError("");
//                }
//                else
//                {
//                    treePanel.updateError(edit.getExecutionError());
//                }
//            }
//        }
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void onMouseEntered(MouseEvent e)
    {
//        BoardView boardView = getInstance().getLegupUI().getBoardView();
//        ElementView element = boardView.getElement(e.getPoint());
//        ElementSelection selection = boardView.getSelection();
//        if(element != null)
//        {
//            selection.newHover(element);
//
//            boardView.repaint();
//        }
    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void onMouseExited(MouseEvent e)
    {
//        BoardView view = getInstance().getLegupUI().getBoardView();
//        ElementView element = view.getElement(e.getPoint());
//        if(element != null)
//        {
//            view.getSelection().clearHover();
//            view.repaint();
//        }
    }

    @Override
    public void onMouseMoved(MouseEvent e)
    {
//        BoardView boardView = getInstance().getLegupUI().getBoardView();
//        ElementView element = boardView.getElement(e.getPoint());
//        ElementSelection selection = boardView.getSelection();
//        if(element != null && element != selection.getHover())
//        {
//            selection.newHover(element);
//            boardView.repaint();
//        }
    }

    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed()
    {
//        BoardView boardView = getInstance().getLegupUI().getBoardView();
//        ElementView selectedElement = boardView.getSelection().getFirstSelection();
//        PuzzleElement puzzleElement = selectedElement.getPuzzleElement();
//        int index = selectedElement.getIndex();
//
//        TreeView treeView = GameBoardFacade.getInstance().getLegupUI().getTreePanel().getTreeView();
//        TreeViewSelection selection = treeView.getSelection();
//        TreeElementView selectedView = selection.getFirstSelection();
//        TreeTransitionView transitionView = (TreeTransitionView)selectedView;
//
//        Board prevBord = transitionView.getTreeElement().getParents().get(0).getBoard();
//
//        int value = (Integer) ((SelectionItemView)e.getSource()).getData().getData();
//
//        puzzleElement.setData(value);
//
//        if(puzzleElement.equalsData(prevBord.getPuzzleElement(puzzleElement)))
//            puzzleElement.setModified(false);
//        else
//            puzzleElement.setModified(true);
//
//        transitionView.getTreeElement().propagateChanges(puzzleElement);
//
//        boardView.repaint();
//        boardView.getSelection().clearSelection();
    }

    public void changeCell(MouseEvent e, PuzzleElement data)
    {
//        if(e.getButton() == MouseEvent.BUTTON1)
//        {
//            if(e.isControlDown() && this.boardView.getSelectionPopupMenu() != null)
//            {
//                this.boardView.getSelectionPopupMenu().show(boardView, this.boardView.getCanvas().getX() + e.getX(), this.boardView.getCanvas().getY() + e.getY());
//            }
//            else
//            {
//                data.setData(((Integer)data.getData() + 1) % 10);
//            }
//        }
//        else if(e.getButton() == MouseEvent.BUTTON3)
//        {
//            data.setData(((Integer)data.getData() + 9) % 10);
//        }
    }
}