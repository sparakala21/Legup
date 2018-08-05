package edu.rpi.legup.model.gameboard;

public class GridCell extends Element
{
    private int x;
    private int y;

    /**
     * GridCell Constructor - creates a new GridCell at the specified location
     *
     * @param valueString value String that represents the GridCell
     * @param x x location of the GridCell in the grid
     * @param y y location of the GridCell in the grid
     */
    public GridCell(String valueString, int x, int y)
    {
        super(valueString);
        this.x = x;
        this.y = y;
    }

    /**
     * GridCell Constructor - creates a new GridCell at the specified location
     *
     * @param valueInt value int that represents the GridCell
     * @param x x location of the GridCell in the grid
     * @param y y location of the GridCell in the grid
     */
    public GridCell(int valueInt, int x, int y)
    {
        super(valueInt);
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
     * Performs a deep copy on the GridCell
     *
     * @return a new copy of the GridCell that is independent of this one
     */
    public GridCell copy()
    {
        GridCell copy = new GridCell(x, y);
        copy.setIndex(index);
        copy.setModifiable(isModifiable);
        copy.setModified(isModified);
        copy.setGiven(isGiven);
        return copy;
    }
}

