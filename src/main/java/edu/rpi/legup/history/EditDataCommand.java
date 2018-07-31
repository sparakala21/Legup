package edu.rpi.legup.history;

import edu.rpi.legup.model.Puzzle;
import edu.rpi.legup.model.gameboard.Board;
import edu.rpi.legup.model.gameboard.Element;
import edu.rpi.legup.model.observer.ITreeListener;
import edu.rpi.legup.model.tree.*;
import edu.rpi.legup.ui.boardview.BoardView;
import edu.rpi.legup.ui.boardview.ElementView;
import edu.rpi.legup.ui.treeview.*;

import java.awt.event.MouseEvent;

import static edu.rpi.legup.app.GameBoardFacade.getInstance;

public class EditDataCommand extends PuzzleCommand
{
    private TreeTransition transition;
    private boolean hasAddedNode;
    private Element saveElement;
    private Element element;

    private ElementView elementView;
    private TreeViewSelection selection;
    private MouseEvent event;

    /**
     * EditDataCommand Constructor - create a edu.rpi.legup.puzzle command for editing a board
     *
     * @param elementView currently selected edu.rpi.legup.puzzle element view that is being edited
     * @param selection currently selected tree element views that is being edited
     * @param event mouse event
     */
    public EditDataCommand(ElementView elementView, TreeViewSelection selection, MouseEvent event)
    {
        this.elementView = elementView;
        this.selection = selection;
        this.event = event;
        this.element = null;
        this.saveElement = null;
        this.transition = null;
        this.hasAddedNode = false;
    }

    /**
     * Executes a command
     */
    @Override
    public void execute()
    {
        if(!canExecute())
        {
            return;
        }

        Puzzle puzzle = getInstance().getPuzzleModule();
        BoardView boardView = getInstance().getLegupUI().getBoardView();
        TreeElementView selectedView = selection.getFirstSelection();
        TreeElement treeElement = selectedView.getTreeElement();

        Board board = treeElement.getBoard();
        Element selectedElement = elementView.getElement();
        element = board.getElementData(selectedElement);
        saveElement = element.copy();

        boardView.getElementController().changeCell(event, element);

        puzzle.notifyBoardListeners(listener -> listener.onBoardDataChanged(element));
    }

    /**
     * Determines whether this command can be executed
     */
    @Override
    public boolean canExecute()
    {
        return true;
    }

    /**
     * Gets the reason why the command cannot be executed
     *
     * @return if command cannot be executed, returns reason for why the command cannot be executed,
     * otherwise null if command can be executed
     */
    @Override
    public String getExecutionError()
    {
        return null;
    }

    /**
     * Undoes an command
     */
    @Override
    public void undo()
    {
        TreeElementView selectedView = selection.getFirstSelection();
        Tree tree = getInstance().getTree();
        Puzzle puzzle = getInstance().getPuzzleModule();

        Board board = transition.getBoard();
        Element selectedElement = elementView.getElement();

        if(selectedView.getType() == TreeElementType.NODE)
        {
            tree.removeTreeElement(transition);
            puzzle.notifyTreeListeners((ITreeListener listener) -> listener.onTreeElementRemoved(transition));
        }

        Board prevBoard = transition.getParents().get(0).getBoard();

        element.setData(saveElement.getData());
        board.notifyChange(element);

        if(prevBoard.getElementData(selectedElement).equalsData(element))
        {
            board.removeModifiedData(element);
        }
        else
        {
            board.addModifiedData(element);
        }
        transition.propagateChanges(element);

        puzzle.notifyBoardListeners(listener -> listener.onBoardDataChanged(element));
        puzzle.notifyTreeListeners(listener -> listener.onTreeSelectionChanged(selection));
    }
}
