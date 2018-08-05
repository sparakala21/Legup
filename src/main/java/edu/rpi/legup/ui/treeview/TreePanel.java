package edu.rpi.legup.ui.treeview;

import edu.rpi.legup.controller.TreeController;
import javafx.scene.layout.Pane;
import edu.rpi.legup.model.gameboard.Board;
import edu.rpi.legup.model.rules.Rule;
import edu.rpi.legup.model.tree.Tree;

public class TreePanel extends Pane
{
    public boolean modifiedSinceSave = false;
    public boolean modifiedSinceUndoPush = false;
    public int updateStatusTimer = 0;

    private Pane main;
    private TreeView treeView;

    private Rule curRuleApplied = null;


    public TreePanel()
    {
        main = new Pane();

        TreeController treeController = new TreeController();
        treeView = new TreeView(treeController);
        treeController.setViewer(treeView);

        updateStatusTimer = 0;
    }

    public void repaintTreeView(Tree tree)
    {
        treeView.updateTreeView(tree);
    }

    public void boardDataChanged(Board board)
    {
        modifiedSinceSave = true;
        modifiedSinceUndoPush = true;
        updateStatus();
        //colorTransitions();
    }

    public void updateStatus()
    {
        updateStatusTimer = ((updateStatusTimer - 1) > 0) ? (updateStatusTimer - 1) : 0;
        if(updateStatusTimer > 0)
        {
            return;
        }
    }

    public void updateStatus(String statusString)
    {
    }

    public void updateError(String error)
    {
    }

    public TreeView getTreeView()
    {
        return treeView;
    }
}
