package edu.rpi.legup.model.gameboard;

public class GridBoard extends Board
{
    protected int width;
    protected int height;

    /**
     * GridBoard Constructor - models a GridBoard
     *
     * @param width width of the GridBoard
     * @param height height of the GridBoard
     */
    public GridBoard(int width, int height)
    {
        this.width = width;
        this.height = height;

        for(int i = 0; i < width * height; i++)
        {
            puzzleElements.add(null);
        }
    }

    /**
     * GridBoard Constructor - models a GridBoard
     *
     * @param size width and height of the GridBoard
     */
    public GridBoard(int size)
    {
        this(size, size);
    }

    /**
     * Gets a GridCell from the board
     *
     * @param x x location of the cell
     * @param y y location of the cell
     * @return GridCell at location (x,y)
     */
    public GridCell getCell(int x, int y)
    {
        if(x * width + y >= puzzleElements.size() || x >= width ||
                y >= height || x < 0 || y < 0)
        {
            return null;
        }
        return (GridCell)puzzleElements.get(y * width + x);
    }

    /**
     * Sets the GridCell at the location (x,y)
     *
     * @param x x location of the cell
     * @param y y location of the cell
     * @param cell GridCell to set at location (x,y)
     */
    public void setCell(int x, int y, GridCell cell)
    {
        if(x * width + y >= puzzleElements.size() && x < width &&
                y < height && x >= 0 && y >= 0)
        {
            return;
        }
        puzzleElements.set(y * width + x, cell);
    }

    /**
     * Gets the width of the board
     *
     * @return width of the board
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * Gets the height of the board
     *
     * @return height
     */
    public int getHeight()
    {
        return height;
    }


    /**
     * Performs a deep copy of the Board
     *
     * @return a new copy of the board that is independent of this one
     */
    public GridBoard copy()
    {
        GridBoard newGridBoard = new GridBoard(width, height);
        for(int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++)
            {
                newGridBoard.setCell(x, y, getCell(x, y).copy());
            }
        }
        return newGridBoard;
    }
}