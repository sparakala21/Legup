package edu.rpi.legup.ui.treeview;

import edu.rpi.legup.app.GameBoardFacade;
import edu.rpi.legup.controller.TreeController;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import edu.rpi.legup.model.observer.ITreeListener;
import edu.rpi.legup.model.tree.Tree;
import edu.rpi.legup.model.tree.TreeElement;
import edu.rpi.legup.model.tree.TreeNode;
import edu.rpi.legup.model.tree.TreeTransition;
import edu.rpi.legup.ui.DynamicViewer;
import edu.rpi.legup.utility.DisjointSets;

import java.util.*;
import java.util.List;
import java.util.logging.Logger;

import static edu.rpi.legup.model.tree.TreeElementType.NODE;
import static edu.rpi.legup.ui.treeview.TreeNodeView.DIAMETER;
import static edu.rpi.legup.ui.treeview.TreeNodeView.RADIUS;

public class TreeView extends DynamicViewer implements ITreeListener
{
    private final static Logger LOGGER = Logger.getLogger(TreeView.class.getName());

    private static final double TRANS_GAP = 5;

    private static final double NODE_GAP_WIDTH = 70;
    private static final double NODE_GAP_HEIGHT = 25;

    private static final double BORDER_GAP_HEIGHT = 20;
    private static final double BORDER_GAP_WIDTH = 20;

    private static final float floater[] = new float[]{(5.0f), (10.0f)};
    private static final float floater2[] = new float[]{(2.0f), (3.0f)};
    private static final String NodeImgs = "images/Legup/tree/smiley/";

    private TreeNodeView nodeHover;

    private Point2D lastMovePoint = null;
    private Rectangle2D bounds = new Rectangle2D(0, 0, 0, 0);
    private int xOffset = 0;
    private int yOffset = 0;
    private Map<TreeNode, Color> collapseColorHash;

    private Tree tree;
    private TreeNodeView rootNodeView;
    private Map<TreeElement, TreeElementView> viewMap;
    private Dimension2D dimension;

    private TreeViewSelection selection;

    public TreeView(TreeController treeController)
    {
        super(treeController);
        collapseColorHash = new HashMap<>();

        viewMap = new HashMap<>();

        selection = new TreeViewSelection();
    }

    public TreeViewSelection getSelection()
    {
        return selection;
    }

    /**
     * Gets the tree node element that the mouse is hovering over
     *
     * @return tree node element that the mouse is hovering over
     */
    public TreeNodeView getNodeHover()
    {
        return nodeHover;
    }

    /**
     * Sets the tree node element that the mouse is hovering over
     *
     * @param nodeHover tree node element the mouse is hovering over
     */
    public void setNodeHover(TreeNodeView nodeHover)
    {
        this.nodeHover = nodeHover;
    }

    /**
     * Gets the TreeElementView by the specified point or null if no view exists at the specified point
     *
     * @param point location to query for a view
     * @return TreeElementView at the point specified, otherwise null
     */
    public TreeElementView getTreeElementView(Point2D point)
    {
        return getTreeElementView(point, rootNodeView);
    }

    /**
     * Recursively gets the TreeElementView by the specified point or null if no view exists at the specified point or
     * the view specified is null
     *
     * @param point location to query for a view
     * @param elementView view to determine if the point is contained within it
     * @return TreeElementView at the point specified, otherwise null
     */
    private TreeElementView getTreeElementView(Point2D point, TreeElementView elementView)
    {
        if(elementView == null)
        {
            return null;
        }
        else if(elementView.contains(point.getX(), point.getY()) && elementView.isVisible())
        {
            if(elementView.getType() == NODE && ((TreeNodeView)elementView).isContradictoryState())
            {
                return null;
            }
            return elementView;
        }
        else
        {
            if(elementView.getType() == NODE)
            {
                TreeNodeView nodeView = (TreeNodeView)elementView;
                for(TreeTransitionView transitionView: nodeView.getChildrenViews())
                {
                    TreeElementView view = getTreeElementView(point, transitionView);
                    if(view != null)
                    {
                        return view;
                    }
                }
            }
            else
            {
                TreeTransitionView transitionView = (TreeTransitionView)elementView;
                return getTreeElementView(point, transitionView.getChildView());
            }
        }
        return null;
    }

    // recursively computes the bounding rectangle of the tree
    private Rectangle2D getTreeBounds(TreeNodeView nodeView)
    {
        return null;
    }

    public void updateTreeView(Tree tree)
    {
        this.tree = tree;
        if(selection.getSelectedViews().size() == 0)
        {
            selection.newSelection(new TreeNodeView(tree.getRootNode()));
        }
    }

    /**
     * Sets the tree associated with this TreeView
     *
     * @param tree tree
     */
    public void setTree(Tree tree)
    {
        this.tree = tree;
    }

    public void updateTreeSize()
    {
        if(GameBoardFacade.getInstance().getTree() == null)
        {
            return;
        }
    }

    public void zoomFit()
    {
//        zoomTo(1.0);
//        updateTreeSize();
//        double fitwidth = (viewport.getWidth() - 8.0) / (getSize().width - 200);
//        double fitheight = (viewport.getHeight() - 8.0) / (getSize().height - 120);
//        // choose the smaller of the two and zoom
//        zoomTo((fitwidth < fitheight) ? fitwidth : fitheight);
//        viewport.setViewPosition(new Point(0, 0));
    }

    public void draw()
    {
        Tree tree = GameBoardFacade.getInstance().getTree();
        if(tree != null)
        {
            //setSize(bounds.getDimension());

            newReDraw();
        }
    }

    /**
     * Gets the color of a collapsed tree node.
     * This function must be called before the game board collapsing takes place,
     * otherwise transition element will be hidden
     *
     * @param treeNode collapsed tree node
     */
    public void getCollapseColor(TreeNode treeNode)
    {
        boolean overallColor = treeNode.isContradictoryBranch();
        if(overallColor)
        {
            this.collapseColorHash.put(treeNode, Color.GREEN);
        }
        else
        {
            this.collapseColorHash.put(treeNode, Color.RED);
        }
    }

    private void redrawTree(TreeNodeView nodeView)
    {
        if(nodeView != null)
        {
//            for(TreeTransitionView transitionView : nodeView.getChildrenViews())
//            {
//                transitionView.draw(graphics2D);
//                redrawTree(graphics2D, transitionView.getChildView());
//            }
        }
    }

    public TreeTransitionView addTransitionView(TreeNodeView nodeView, TreeTransition transition)
    {
        TreeTransitionView transitionView = new TreeTransitionView(transition, nodeView);
        nodeView.getChildrenViews().add(transitionView);
        return transitionView;
    }

    public TreeNodeView addNodeView(TreeTransitionView transitionView, TreeNode node)
    {
        TreeNodeView newNodeView = new TreeNodeView(node);
        transitionView.setChildView(newNodeView);
        newNodeView.setParentView(transitionView);
        return newNodeView;
    }

    public void removeTreeElement(TreeElementView view)
    {
        if(view.getType() == NODE)
        {
            TreeNodeView nodeView = (TreeNodeView)view;
            nodeView.getParentView().setChildView(null);
        }
        else
        {
            TreeTransitionView transitionView = (TreeTransitionView)view;
            transitionView.getParentViews().forEach((TreeNodeView n) -> n.removeChildrenView(transitionView));
        }
    }

    public void resetView()
    {
        this.tree = null;
        this.rootNodeView = null;
        this.selection.clearSelection();
    }

    /**
     * Called when a tree element is added to the tree
     *
     * @param element TreeElement that was added to the tree
     */
    @Override
    public void onTreeElementAdded(TreeElement element)
    {
        if(element.getType() == NODE)
        {
            addTreeNode((TreeNode)element);
        }
        else
        {
            addTreeTransition((TreeTransition)element);
        }
    }

    /**
     * Called when a tree element is removed from the tree
     *
     * @param element TreeElement that was removed to the tree
     */
    @Override
    public void onTreeElementRemoved(TreeElement element)
    {
        if(element.getType() == NODE)
        {
            TreeNode node = (TreeNode)element;
            TreeNodeView nodeView = (TreeNodeView)viewMap.get(node);

            nodeView.getParentView().setChildView(null);
            removeTreeNode(node);
        }
        else
        {
            TreeTransition trans = (TreeTransition)element;
            TreeTransitionView transView = (TreeTransitionView)viewMap.get(trans);

            transView.getParentViews().forEach(n -> n.removeChildrenView(transView));
            removeTreeTransition(trans);
        }
    }

    /**
     * Called when the tree selection was changed
     *
     * @param selection tree selection that was changed
     */
    @Override
    public void onTreeSelectionChanged(TreeViewSelection selection)
    {
        this.selection.getSelectedViews().forEach(v -> v.setSelected(false));
        selection.getSelectedViews().forEach(v -> v.setSelected(true));

        this.selection = selection;
    }

    /**
     * Gets the TreeElementView by the corresponding TreeElement associated with it
     *
     * @param element TreeElement of the view
     * @return TreeElementView of the TreeElement associated with it
     */
    public TreeElementView getElementView(TreeElement element)
    {
        return viewMap.get(element);
    }

    private void removeTreeNode(TreeNode node)
    {
        viewMap.remove(node);
        node.getChildren().forEach(t -> removeTreeTransition(t));
    }

    private void removeTreeTransition(TreeTransition trans)
    {
        viewMap.remove(trans);
        if(trans.getChildNode() != null)
        {
            removeTreeNode(trans.getChildNode());
        }
    }

    private void addTreeNode(TreeNode node)
    {
        TreeTransition parent = node.getParent();

        TreeNodeView nodeView = new TreeNodeView(node);
        TreeTransitionView parentView = (TreeTransitionView)viewMap.get(parent);

        nodeView.setParentView(parentView);
        parentView.setChildView(nodeView);

        viewMap.put(node, nodeView);

        if(!node.getChildren().isEmpty())
        {
            node.getChildren().forEach(t -> addTreeTransition(t));
        }
    }

    private void addTreeTransition(TreeTransition trans)
    {
        List<TreeNode> parents = trans.getParents();

        TreeTransitionView transView = new TreeTransitionView(trans);
        for(TreeNode parent : parents)
        {
            TreeNodeView parentNodeView = (TreeNodeView)viewMap.get(parent);
            transView.addParentView(parentNodeView);
            parentNodeView.addChildrenView(transView);
        }

        viewMap.put(trans, transView);

        if(trans.getChildNode() != null)
        {
            addTreeNode(trans.getChildNode());
        }
    }

    ///New Draw Methods

    public void newReDraw()
    {
        if(tree == null)
        {
            //TODO add error

            System.err.println("CreateViews: Null tree");
        }
        else
        {
            if(rootNodeView == null)
            {
                rootNodeView = new TreeNodeView(tree.getRootNode());

                createViews(rootNodeView);
                System.err.println("newReDraw: Created Views");

                selection.newSelection(rootNodeView);
            }

            calcSpan(rootNodeView);
            rootNodeView.setSpan(rootNodeView.getSpan() + DIAMETER);
            System.err.println("newReDraw: Calculated span: " + rootNodeView.getSpan());

            calculateViewLocations(rootNodeView, 0);
//            dimension.height = (int)rootNodeView.getSpan();
            System.err.println("newReDraw: Calculated view positions");

            System.err.println("newReDraw: redrawTree");

        }
    }

    public void createViews(TreeNodeView nodeView)
    {
        if(nodeView != null)
        {
            viewMap.put(nodeView.getTreeElement(), nodeView);

            TreeNode node = nodeView.getTreeElement();
            for(TreeTransition trans : node.getChildren())
            {
                TreeTransitionView transView = new TreeTransitionView(trans);
                viewMap.put(transView.getTreeElement(), transView);

                transView.addParentView(nodeView);
                nodeView.addChildrenView(transView);

                TreeNode childNode = trans.getChildNode();
                if(childNode != null)
                {
                    TreeNodeView childNodeView = new TreeNodeView(childNode);
                    viewMap.put(childNodeView.getTreeElement(), childNodeView);

                    childNodeView.setParentView(transView);
                    transView.setChildView(childNodeView);

                    createViews(childNodeView);
                }
            }
        }
    }

    public void calculateViewLocations(TreeNodeView nodeView, int depth)
    {
        nodeView.setDepth(depth);
        double xLoc = (NODE_GAP_WIDTH + DIAMETER) * depth + DIAMETER;
        nodeView.setX(xLoc);
//        dimension.width = Math.max(dimension.width, xLoc + DIAMETER);

        TreeTransitionView parentTransView = nodeView.getParentView();
        double yLoc = parentTransView == null ? (int)nodeView.getSpan() / 2 : parentTransView.getEndY() ;
        nodeView.setY(yLoc);

        ArrayList<TreeTransitionView> children = nodeView.getChildrenViews();
        switch(children.size())
        {
            case 0:
                break;
            case 1:
            {
                TreeTransitionView childView = children.get(0);

                List<TreeNodeView> parentsViews = childView.getParentViews();
                if(parentsViews.size() == 1)
                {
                    childView.setEndY(yLoc);

                    childView.setDepth(depth);

                    Point2D lineStartPoint = childView.getLineStartPoint(0);
                    lineStartPoint.x = xLoc + RADIUS + TRANS_GAP / 2;
                    lineStartPoint.y = yLoc;
                    childView.setEndX((NODE_GAP_WIDTH + DIAMETER) * (depth + 1) + RADIUS - TRANS_GAP / 2);

//                    dimension.width = Math.max(dimension.width, childView.getEndX() + DIAMETER);

                    TreeNodeView childNodeView = childView.getChildView();
                    if(childNodeView != null)
                    {
                        calculateViewLocations(childNodeView, depth + 1);
                    }
                }
                else if(parentsViews.size() > 1 && parentsViews.get(parentsViews.size() - 1) == nodeView)
                {
                    int yAvg = 0;
                    for(int i = 0; i < parentsViews.size(); i++)
                    {
                        TreeNodeView parentNodeView = parentsViews.get(i);
                        depth = Math.max(depth, parentNodeView.getDepth());
                        yAvg += parentNodeView.getY();

                        Point2D lineStartPoint = childView.getLineStartPoint(i);
                        lineStartPoint.x = parentNodeView.getX() + RADIUS + TRANS_GAP / 2;
                        lineStartPoint.y = parentNodeView.getY();
                    }
                    yAvg /= parentsViews.size();
                    childView.setEndY(yAvg);

                    childView.setDepth(depth);

                    childView.setEndX((NODE_GAP_WIDTH + DIAMETER) * (depth + 1) + RADIUS - TRANS_GAP / 2);

//                    dimension.width = Math.max(dimension.width, childView.getEndX() + DIAMETER);

                    TreeNodeView childNodeView = childView.getChildView();
                    if(childNodeView != null)
                    {
                        calculateViewLocations(childNodeView, depth + 1);
                    }
                }
                break;
            }
            default:
            {
                int span = 0;
                for(TreeTransitionView childView : children)
                {
                    span += childView.getSpan();
                }

                span = (int)((nodeView.getSpan() - span) / 2);
                for(int i = 0; i < children.size(); i++)
                {
                    TreeTransitionView childView = children.get(i);

                    childView.setDepth(depth);

                    Point2D lineStartPoint = childView.getLineStartPoint(0);
                    lineStartPoint.x = xLoc + RADIUS + TRANS_GAP / 2;
                    lineStartPoint.y = yLoc;
                    childView.setEndX((NODE_GAP_WIDTH + DIAMETER) * (depth + 1) + RADIUS - TRANS_GAP / 2);
                    childView.setEndY(yLoc - (int)(nodeView.getSpan() / 2) + span + (int)(childView.getSpan() / 2));

                    span += childView.getSpan();
                    TreeNodeView childNodeView = childView.getChildView();
                    if(childNodeView != null)
                    {
                        calculateViewLocations(childNodeView, depth + 1);
                    }
                }
                break;
            }
        }
    }

    public void calcSpan(TreeElementView view)
    {
        if(view.getType() == NODE)
        {
            TreeNodeView nodeView = (TreeNodeView)view;
            TreeNode node = nodeView.getTreeElement();
            if(nodeView.getChildrenViews().size() == 0)
            {
                nodeView.setSpan(DIAMETER);
            }
            else if(nodeView.getChildrenViews().size() == 1)
            {
                TreeTransitionView childView = nodeView.getChildrenViews().get(0);
                calcSpan(childView);
                if(childView.getParentViews().size() > 1)
                {
                    nodeView.setSpan(DIAMETER);
                }
                else
                {
                    nodeView.setSpan(childView.getSpan());
                }
            }
            else
            {
                DisjointSets<TreeTransition> branches = node.findMergingBranches();
                List<TreeTransition> children = node.getChildren();

                if(node == children.get(0).getParents().get(0))
                {
                    reorderBranches(node, branches);
                }

                List<Set<TreeTransition>> mergingSets = branches.getAllSets();

                double span = 0.0;
                for(Set<TreeTransition> mergeSet : mergingSets)
                {
                    if(mergeSet.size() > 1)
                    {
                        TreeTransition mergePoint = TreeNode.findMergingPoint(mergeSet);
                        TreeTransitionView mergePointView = (TreeTransitionView) viewMap.get(mergePoint);
                        double subSpan = 0.0;
                        for(TreeTransition branch: mergeSet)
                        {
                            TreeTransitionView branchView = (TreeTransitionView) viewMap.get(branch);
                            subCalcSpan(branchView, mergePointView);
                            subSpan += branchView.getSpan();
                        }
                        calcSpan(mergePointView);
                        span += Math.max(mergePointView.getSpan(), subSpan);
                    }
                    else
                    {
                        TreeTransition trans = mergeSet.iterator().next();
                        TreeTransitionView transView = (TreeTransitionView) viewMap.get(trans);
                        calcSpan(transView);
                        span += transView.getSpan();
                    }
                }

                nodeView.setSpan(span);
            }
        }
        else
        {
            TreeTransitionView transView = (TreeTransitionView)view;
            TreeNodeView nodeView = transView.getChildView();
            if(nodeView == null)
            {
                transView.setSpan(DIAMETER);
            }
            else
            {
                calcSpan(nodeView);
                transView.setSpan(nodeView.getSpan());
            }
        }
    }

    /**
     * Calculates the sub span of a given sub tree rooted at the specified view and stops at the tree element view
     * specified as stop. Stop tree element is NOT included in the span calculation
     *
     * @param view
     * @param stop
     */
    private void subCalcSpan(TreeElementView view, TreeElementView stop)
    {
        //safe-guard for infinite loop
        if(view == stop)
        {
            return;
        }

        if(view.getType() == NODE)
        {
            TreeNodeView nodeView = (TreeNodeView)view;
            TreeNode node = nodeView.getTreeElement();
            if(nodeView.getChildrenViews().size() == 0)
            {
                nodeView.setSpan(DIAMETER);
            }
            else if(nodeView.getChildrenViews().size() == 1)
            {
                TreeTransitionView childView = nodeView.getChildrenViews().get(0);
                if(childView == stop)
                {
                    nodeView.setSpan(DIAMETER);
                }
                else
                {
                    subCalcSpan(childView, stop);
                    if(childView.getParentViews().size() > 1)
                    {
                        nodeView.setSpan(DIAMETER);
                    }
                    else
                    {
                        nodeView.setSpan(childView.getSpan());
                    }
                }
            }
            else
            {
                DisjointSets<TreeTransition> branches = node.findMergingBranches();
                List<TreeTransition> children = node.getChildren();

                if(node == children.get(0).getParents().get(0))
                {
                    reorderBranches(node, branches);
                }

                List<Set<TreeTransition>> mergingSets = branches.getAllSets();

                double span = 0.0;
                for(Set<TreeTransition> mergeSet : mergingSets)
                {
                    if(mergeSet.size() > 1)
                    {
                        TreeTransition mergePoint = TreeNode.findMergingPoint(mergeSet);
                        TreeTransitionView mergePointView = (TreeTransitionView) viewMap.get(mergePoint);
                        double subSpan = 0.0;
                        for(TreeTransition branch: mergeSet)
                        {
                            TreeTransitionView branchView = (TreeTransitionView) viewMap.get(branch);
                            subCalcSpan(branchView, mergePointView);
                            subSpan += branchView.getSpan();
                        }
                        subCalcSpan(mergePointView, stop);
                        span += Math.max(mergePointView.getSpan(), subSpan);
                    }
                    else
                    {
                        TreeTransition trans = mergeSet.iterator().next();
                        TreeTransitionView transView = (TreeTransitionView) viewMap.get(trans);
                        subCalcSpan(transView, stop);
                        span += transView.getSpan();
                    }
                }

                nodeView.setSpan(span);
            }
        }
        else
        {
            TreeTransitionView transView = (TreeTransitionView)view;
            TreeNodeView nodeView = transView.getChildView();
            if(nodeView == null || nodeView == stop)
            {
                transView.setSpan(DIAMETER);
            }
            else
            {
                calcSpan(nodeView);
                transView.setSpan(nodeView.getSpan());
            }
        }
    }

    /**
     * Reorders branches such that merging branches are sequentially grouped together and transitions are kept in
     * relative order in the list of child transitions of the specified node
     *
     * @param node root node of the branches
     * @param branches DisjointSets of the child branches of the specified node which determine which branches merge
     */
    private void reorderBranches(TreeNode node, DisjointSets<TreeTransition> branches)
    {
        List<TreeTransition> children = node.getChildren();
        List<Set<TreeTransition>> mergingSets = branches.getAllSets();

        List<List<TreeTransition>> newOrder = new ArrayList<>();
        for(Set<TreeTransition> set : mergingSets)
        {
            List<TreeTransition> mergeBranch = new ArrayList<>();
            newOrder.add(mergeBranch);
            children.forEach((TreeTransition t) ->
            {
                if(set.contains(t))
                {
                    mergeBranch.add(t);
                }
            });
            mergeBranch.sort((TreeTransition t1, TreeTransition t2) ->
                    children.indexOf(t1) <= children.indexOf(t2) ? -1 : 1);
        }

        newOrder.sort((List<TreeTransition> b1, List<TreeTransition> b2) ->
        {
            int low1 = -1;
            int low2 = -1;
            for(TreeTransition t1 : b1)
            {
                int curIndex = children.indexOf(t1);
                if(low1 == -1 || curIndex < low1 )
                {
                    low1 = curIndex;
                }
            }
            for(TreeTransition t1 : b2)
            {
                int curIndex = children.indexOf(t1);
                if(low1 == -1 || curIndex < low1 )
                {
                    low1 = curIndex;
                }
            }
            return low1 < low2 ? -1 : 1;
        });

        List<TreeTransition> newChildren = new ArrayList<>();
        newOrder.forEach(l -> l.forEach(t -> newChildren.add(t)));
        node.setChildren(newChildren);
    }
}