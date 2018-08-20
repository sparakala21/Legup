package edu.rpi.legup.model.gameboard;

public class GridCell<T> extends PuzzleElement<T>
{
    protected int x;
    protected int y;

    /**
     * GridCell Constructor - creates a new GridCell at the specified location
     *
     * @param data value String that represents the GridCell
     * @param x x location of the GridCell in the grid
     * @param y y location of the GridCell in the grid
     */
    public GridCell(T data, int x, int y)
    {
        super(data);
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the location of the GridCell on the grid
     *
     * @return location of the GridCell
     */
    public int getX()
    {
        return x;
    }

    /**
     * Gets the location of the GridCell on the grid
     *
     * @return location of the GridCell
     */
    public int getY()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * Determines if this represents the same element as the specified element
     *
     * @param gridCell puzzleElement
     * @return if this represents the same element as the specified element, false otherwise
     */
    public boolean isSameElement(GridCell<T> gridCell) {
        return this.x == gridCell.getX() && this.y == gridCell.getY();
    }

    /**
     * Performs a deep copy on the GridCell
     *
     * @return a new copy of the GridCell that is independent of this one
     */
    public GridCell<T> copy()
    {
        GridCell<T> copy = new GridCell<>(data, x, y);
        copy.setIndex(index);
        copy.setModifiable(isModifiable);
        copy.setModified(isModified);
        copy.setGiven(isGiven);
        return copy;
    }
}

