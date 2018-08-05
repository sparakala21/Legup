package edu.rpi.legup.ui.treeview;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import edu.rpi.legup.model.tree.TreeElementType;
import edu.rpi.legup.model.tree.TreeNode;

import java.util.ArrayList;

public class TreeNodeView extends TreeElementView
{
    static final double RADIUS = 25;
    static final double DIAMETER = 2 * RADIUS;

    private static final double MAIN_STROKE = 3.0;
    private static final double SELECTION_STROKE = 2.0;

    private static final Color NODE_COLOR_ROOT = Color.rgb(100, 100, 100);
    private static final Color NODE_MINOR_COLOR_ROOT = Color.rgb(75, 75, 75);

    private static final Color NODE_COLOR_DEFAULT = Color.valueOf("0xFFEB3B");
//    private static final Color NODE_MINOR_COLOR_DEFAULT = Color.rgb(216, 197, 52);

    private static final Color NODE_COLOR_CONTRADICTION = Color.rgb(178, 10, 16);
    private static final Color NODE_MINOR_COLOR_CONTRADICTION = Color.rgb(119, 13, 16);

    private static final Color OUTLINE_COLOR = Color.valueOf("0x212121");
    private static final Color SELECTION_COLOR = Color.valueOf("0x1E88E5");
    private static final Color OUTLINE_SELECTION_COLOR = Color.valueOf("0x1976D2");

    private static final Color HOVER_COLOR = Color.valueOf("0x90CAF9");
    private static final Color OUTLINE_HOVER_COLOR = Color.valueOf("0xBDBDBD");

    private Point2D location;

    private TreeTransitionView parentView;
    private ArrayList<TreeTransitionView> childrenViews;

    private boolean isCollapsed;
    private boolean isContradictoryState;

    private Circle node;
    private Paint nodeStroke;

    /**
     * TreeNodeView Constructor - creates a node for display
     *
     * @param treeNode treeElement associated with this transition
     */
    public TreeNodeView(TreeNode treeNode)
    {
        super(TreeElementType.NODE, treeNode);
        this.treeElement = treeNode;
        this.location = new Point2D(0.0,0.0);
        this.parentView = null;
        this.childrenViews = new ArrayList<>();
        this.isCollapsed = false;
        this.isContradictoryState = false;

        this.node = new Circle();
        this.node.setRadius(RADIUS);
        this.node.setStrokeWidth(MAIN_STROKE);
        this.node.setFill(NODE_COLOR_DEFAULT);

        getChildren().addAll(node);
    }

    public boolean isContradictoryState()
    {
        return isContradictoryState;
    }

    /**
     * Gets the list of children views associated with this tree node
     *
     * @return list of children views for this tree node
     */
    public ArrayList<TreeTransitionView> getChildrenViews()
    {
        return childrenViews;
    }

    /**
     * Sets the list of children views associated with this tree node
     *
     * @param childrenViews list of children views for this tree node
     */
    public void setChildrenViews(ArrayList<TreeTransitionView> childrenViews)
    {
        this.childrenViews = childrenViews;
    }

    /**
     * Adds a TreeTransitionView to the list of children views
     *
     * @param nodeView TreeTransitionView to add to the list of children views
     */
    public void addChildrenView(TreeTransitionView nodeView)
    {
        childrenViews.add(nodeView);
    }

    /**
     * Removes a TreeTransitionView from the list of children views
     *
     * @param nodeView TreeTransitionView to remove from the list of children views
     */
    public void removeChildrenView(TreeTransitionView nodeView)
    {
        childrenViews.remove(nodeView);
    }

    /**
     * Sets the parent tree transition view
     *
     * @param parentView parent tree transition view
     */
    public void setParentView(TreeTransitionView parentView)
    {
        this.parentView = parentView;
    }

    /**
     * Gets the parent tree transition view
     *
     * @return parent tree transition view
     */
    public TreeTransitionView getParentView()
    {
        return parentView;
    }

    /**
     * Gets the tree node associated with this view
     *
     * @return tree node
     */
    public TreeNode getTreeElement()
    {
        return (TreeNode)treeElement;
    }

    /**
     * Gets the location of the tree node
     *
     * @return location of the tree node
     */
    public Point2D getLocation()
    {
        return location;
    }

    /**
     * Sets the location of the tree node
     *
     * @param location location of the tree node
     */
    public void setLocation(Point2D location)
    {
        this.location = location;
    }

    /**
     * Gets the x location of the tree node
     *
     * @return x location
     */
    public double getX()
    {
        return location.getX();
    }

    /**
     * Sets the x location of the tree node
     *
     * @param x x location
     */
    public void setX(double x)
    {
        location = new Point2D(x, location.getY());
    }

    /**
     * Gets the y location of the tree node
     *
     * @return y location
     */
    public double getY()
    {
        return location.getY();
    }

    /**
     * Sets the y location of the tree node
     *
     * @param y y location
     */
    public void setY(double y)
    {
        location = new Point2D(location.getX(), y);
    }

    /**
     * Gets the radius of the tree node
     *
     * @return radius
     */
    public double getRadius() {return RADIUS; }

    /**
     * Is this tree node view collapsed in the view
     *
     * @return true if the node is collapsed, false otherwise
     */
    public boolean isCollapsed()
    {
        return isCollapsed;
    }

    /**
     * Sets the tree node view collapsed field
     *
     * @param isCollapsed true if the node is collapsed, false otherwise
     */
    public void setCollapsed(boolean isCollapsed)
    {
        this.isCollapsed = isCollapsed;
    }
}