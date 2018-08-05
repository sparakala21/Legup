package edu.rpi.legup.ui.treeview;

import javafx.scene.Group;
import edu.rpi.legup.model.tree.TreeElement;
import edu.rpi.legup.model.tree.TreeElementType;

public abstract class TreeElementView extends Group
{
    protected TreeElement treeElement;
    protected double span;
    protected int depth;
    protected boolean isSelected;
    protected TreeElementType type;

    /**
     * TreeElementView Constructor - creates a tree element view
     *
     * @param type tree element type
     * @param treeElement tree element element associated with this view
     */
    protected TreeElementView(TreeElementType type, TreeElement treeElement) {
        this.type = type;
        this.treeElement = treeElement;
        this.isSelected = false;
    }

    /**
     * Gets the span for the sub tree rooted at this view
     *
     * @return span bounded y span
     */
    public double getSpan()
    {
        return span;
    }

    /**
     * Sets the span for the sub tree rooted at this view.
     *
     * @param span bounded y span
     */
    public void setSpan(double span)
    {
        this.span = span;
    }

    /**
     * Gets the depth of this tree element in the tree
     *
     * @return depth of this tree element
     */
    public int getDepth()
    {
        return depth;
    }

    /**
     * Sets the depth of this tree element in the tree
     *
     * @param depth depth of this tree element
     */
    public void setDepth(int depth)
    {
        this.depth = depth;
    }

    /**
     * Gets the tree element type for this view
     *
     * @return tree element type
     */
    public TreeElementType getType()
    {
        return type;
    }

    /**
     * Gets the tree element associated with this view
     *
     * @return tree element associated with this view
     */
    public TreeElement getTreeElement()
    {
        return treeElement;
    }

    /**
     * Sets the tree element associated with this view
     *
     * @param treeElement tree element associated with this view
     */
    public void setTreeElement(TreeElement treeElement)
    {
        this.treeElement = treeElement;
    }

    /**
     * Gets the mouse selection
     *
     * @return mouse selection
     */
    public boolean isSelected()
    {
        return isSelected;
    }

    /**
     * Sets the mouse selection
     *
     * @param isSelected mouse selection
     */
    public void setSelected(boolean isSelected)
    {
        this.isSelected = isSelected;
    }
}
