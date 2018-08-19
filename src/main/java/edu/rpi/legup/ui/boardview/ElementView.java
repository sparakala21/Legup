package edu.rpi.legup.ui.boardview;

import edu.rpi.legup.model.gameboard.PuzzleElement;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.geometry.Point2D;

public abstract class ElementView extends Group
{
    protected int index;
    protected Point2D location;
    protected Dimension2D size;
    protected PuzzleElement element;
    private Color highLightColor;
    private boolean showCasePicker;
    private boolean isCaseRulePickable;
    private boolean isSelected;

    /**
     * ElementView Constructor - creates a puzzle element view
     *
     * @param element element element to which the puzzle element uses to draw
     */
    public ElementView(PuzzleElement element)
    {
        this.element = element;
        this.isSelected = false;
        this.isCaseRulePickable = false;
    }

    /**
     * Determines if the specified point is within the ElementView
     *
     * @param point point to check
     * @return true if the point is within the ElementView, false otherwise
     */
    public boolean isWithinBounds(Point2D point)
    {
        return contains(point);
    }

    /**
     * Gets the index of the ElementView
     *
     * @return index of the ElementView
     */
    public int getIndex()
    {
        return index;
    }

    /**
     * Sets the index of the ElementView
     *
     * @param index index of the ElementView
     */
    public void setIndex(int index)
    {
        this.index = index;
    }

    /**
     * Gets the location of the ElementView
     *
     * @return location of the ElementView
     */
    public Point2D getLocation()
    {
        return location;
    }

    /**
     * Sets the location of the ElementView
     *
     * @param location location of the ElementView
     */
    public void setLocation(Point2D location)
    {
        this.location = location;
    }

    /**
     * Gets the dimension of the ElementView
     *
     * @return dimension of the ElementView
     */
    public Dimension2D getSize()
    {
        return size;
    }

    /**
     * Sets the dimension of the ElementView
     *
     * @param size dimension of the ElementView
     */
    public void setSize(Dimension2D size)
    {
        this.size = size;
    }

    /**
     * Gets the Element associated with this view
     *
     * @return Element associated with this view
     */
    public PuzzleElement getPuzzleElement()
    {
        return element;
    }

    /**
     * Sets the Element associated with this view
     *
     * @param element Element associated with this view
     */
    public void setPuzzleElement(PuzzleElement element)
    {
        this.element = element;
    }

    public boolean isShowCasePicker()
    {
        return showCasePicker;
    }

    public void setShowCasePicker(boolean showCasePicker)
    {
        this.showCasePicker = showCasePicker;
    }

    /**
     * Gets the isCaseRulePickable field to determine if this ElementView
     * should be highlighted in some way to indicate if it can be chosen by
     * the CaseRule
     *
     * @return true if the ElementView can be chosen for the CaseRule, false otherwise
     */
    public boolean isCaseRulePickable()
    {
        return isCaseRulePickable;
    }

    /**
     * Sets the isCaseRulePickable field to determine if this ElementView
     * should be highlighted in some way to indicate if it can be chosen by
     * the CaseRule
     *
     * @param isCaseRulePickable true if the ElementView can be chosen for the CaseRule, false otherwise
     */
    public void setCaseRulePickable(boolean isCaseRulePickable)
    {
        this.isCaseRulePickable = isCaseRulePickable;
    }

    /**
     * Gets the high-light color
     *
     * @return high-light color
     */
    public Color getHighLightColor()
    {
        return highLightColor;
    }

    /**
     * Sets the high-light color
     *
     * @param highLightColor high-light color
     */
    public void setHighLightColor(Color highLightColor)
    {
        this.highLightColor = highLightColor;
    }

    /**
     * Gets whether the element is being selected
     *
     * @return tue if the element is currently selected, false otherwise
     */
    public boolean isSelected()
    {
        return isSelected;
    }

    /**
     * Sets whether the element is being selected
     *
     * @param selected tue if the element is currently selected, false otherwise
     */
    public void setSelected(boolean selected)
    {
        isSelected = selected;
    }

    public abstract void onPuzzleElementDataChange(PuzzleElement puzzleElement);
}
