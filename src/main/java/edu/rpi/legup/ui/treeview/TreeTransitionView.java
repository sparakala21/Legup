package edu.rpi.legup.ui.treeview;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import edu.rpi.legup.model.tree.TreeElementType;
import edu.rpi.legup.model.tree.TreeTransition;

import java.util.List;
import java.util.ArrayList;

import static java.lang.Math.*;

public class TreeTransitionView extends TreeElementView
{
    static final double RADIUS = 10;
    static final double DIAMETER = 2 * RADIUS;
    static final double GAP = 5;

    private static final double MAIN_STROKE = 3.0;
    private static final double SELECTION_STROKE = 2.0;

    private static final Color SELECTION_COLOR = Color.valueOf("0x1E88E5");
    private static final Color OUTLINE_SELECTION_COLOR = Color.valueOf("0x1976D2");

    private static final Color HOVER_COLOR = Color.valueOf("0x90CAF9");
    private static final Color OUTLINE_HOVER_COLOR = Color.valueOf("0xBDBDBD");

    private TreeNodeView childView;
    private ArrayList<TreeNodeView> parentViews;
    private Polygon arrowhead;
    private List<Line> lines;

    private List<Point2D> lineStartPoints;
    private Point2D lineEndPoint;

    private Point2D endPoint;

    private boolean isCollapsed;

    /**
     * TreeTransitionView Constructor - creates a transition arrow for display
     *
     * @param transition tree transition associated with this view
     */
    public TreeTransitionView(TreeTransition transition)
    {
        super(TreeElementType.TRANSITION, transition);
        this.parentViews = new ArrayList<>();
        this.isCollapsed = false;
        this.endPoint = new Point2D(0,0);
        this.lineStartPoints = new ArrayList<>();
        this.lineEndPoint = new Point2D(0,0);

        this.arrowhead = new Polygon();
        this.arrowhead.setFill(SELECTION_COLOR);

        this.lines = new ArrayList<>();

        getChildren().addAll(arrowhead);
    }

    /**
     * TreeTransitionView Constructor - creates a transition arrow for display
     *
     * @param transition tree transition associated with this view
     * @param parentView TreeNodeView of the parent associated with this transition
     */
    public TreeTransitionView(TreeTransition transition, TreeNodeView parentView)
    {
        this(transition);
        this.parentViews.add(parentView);
        this.lineStartPoints.add(new Point2D(0,0));
    }

    /**
     * Constructs the arrowhead shape from the start and end points
     */
    private void constructArrowhead()
    {
        double nodeRadii = TreeNodeView.RADIUS;

        double thetaArrow = Math.toRadians(30);

        double point1X = endPoint.getX();
        double point1Y = endPoint.getY();

        double point2X = point1X - nodeRadii;
        double point2Y = point1Y + (int)Math.round(nodeRadii / (2 * cos(thetaArrow)));

        double point3X = point1X - nodeRadii;
        double point3Y = point1Y - (int)Math.round(nodeRadii / (2 * cos(thetaArrow)));

        lineEndPoint = new Point2D(point2X, (point3Y - point2Y) / 2 + point2Y);

        arrowhead.getPoints().clear();
        arrowhead.getPoints().addAll(
                point1X, point1Y,
                point2X, point2Y,
                point3X, point3Y
        );
    }

    /**
     * Gets the TreeElement associated with this view
     *
     * @return the TreeElement associated with this view
     */
    public TreeTransition getTreeElement()
    {
        return (TreeTransition)treeElement;
    }

    /**
     * Gets the TreeNodeView child view
     *
     * @return TreeNodeView child view
     */
    public TreeNodeView getChildView()
    {
        return childView;
    }

    /**
     * Sets the TreeNodeView child view
     *
     * @param childView TreeNodeView child view
     */
    public void setChildView(TreeNodeView childView)
    {
        this.childView = childView;
    }

    /**
     * Gets the list of parent views associated with this tree transition view
     *
     * @return list of parent views for this tree transition view
     */
    public ArrayList<TreeNodeView> getParentViews()
    {
        return parentViews;
    }

    /**
     * Sets the list of parent views associated with this tree transition view
     *
     * @param parentViews list of parent views for this tree transition view
     */
    public void setParentViews(ArrayList<TreeNodeView> parentViews)
    {
        this.parentViews = parentViews;
        this.lineStartPoints.clear();
        for(TreeNodeView parentView : this.parentViews)
        {
            this.lineStartPoints.add(new Point2D(0,0));
        }
    }

    /**
     * Adds a TreeNodeView to the list of parent views
     *
     * @param nodeView TreeNodeView to add to the list of parent views
     */
    public void addParentView(TreeNodeView nodeView)
    {
        parentViews.add(nodeView);
        lineStartPoints.add(new Point2D(0,0));
    }

    /**
     * Removes a TreeNodeView from the list of parent views
     *
     * @param nodeView TreeNodeView to remove from the list of parent views
     */
    public void removeParentView(TreeNodeView nodeView)
    {
        int index = parentViews.indexOf(nodeView);
        parentViews.remove(nodeView);
        if(index != -1)
        {
            lineStartPoints.remove(index);
        }
    }

    public Point2D getEndPoint()
    {
        return endPoint;
    }

    public void setEndPoint(Point2D endPoint)
    {
        this.endPoint = endPoint;
    }

    public double getEndX()
    {
        return endPoint.getX();
    }

    public void setEndX(double x)
    {
        this.endPoint = new Point2D(x, endPoint.getY());
    }

    public double getEndY()
    {
        return endPoint.getY();
    }

    public void setEndY(double y)
    {
        this.endPoint = new Point2D(endPoint.getX(), y);
    }

    public List<Point2D> getLineStartPoints()
    {
        return lineStartPoints;
    }

    public void setLineStartPoints(List<Point2D> lineStartPoints)
    {
        this.lineStartPoints = lineStartPoints;
    }

    public Point2D getLineStartPoint(int index)
    {
        return index < lineStartPoints.size() ? lineStartPoints.get(index) : null;
    }

    public boolean isCollapsed()
    {
        return isCollapsed;
    }

    public void setCollapsed(boolean isCollapsed)
    {
        this.isCollapsed = isCollapsed;
    }
}
