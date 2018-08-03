package edu.rpi.legup.ui.treeview;

import edu.rpi.legup.controller.TreeController;
import edu.rpi.legup.model.gameboard.Board;
import edu.rpi.legup.model.rules.Rule;
import edu.rpi.legup.model.tree.Tree;
import edu.rpi.legup.ui.LegupUI;
import controller.TreeController;
import javafx.scene.layout.Pane;
import model.gameboard.Board;
import model.rules.Rule;
import model.tree.Tree;
import ui.LegupUI;

public class TreePanel extends Pane
{
    public boolean modifiedSinceSave = false;
    public boolean modifiedSinceUndoPush = false;
    public int updateStatusTimer = 0;

    private Pane main;
    private TreeView treeView;
    private TreeToolbarPanel toolbar;
    private LegupUI legupUI;

    private Rule curRuleApplied = null;


    public TreePanel(LegupUI legupUI)
    {
        this.legupUI = legupUI;

        main = new Pane();

        TreeController treeController = new TreeController();
        treeView = new TreeView(treeController);
        treeController.setViewer(treeView);

        toolbar = new TreeToolbarPanel(this);


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
