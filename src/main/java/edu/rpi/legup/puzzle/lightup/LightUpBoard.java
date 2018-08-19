package edu.rpi.legup.puzzle.lightup;

import edu.rpi.legup.model.gameboard.PuzzleElement;
import edu.rpi.legup.model.gameboard.GridBoard;

public class LightUpBoard extends GridBoard
{
    public LightUpBoard(int width, int height)
    {
        super(width, height);
    }

    public LightUpBoard(int size)
    {
        super(size, size);
    }

    public void fillWithLight()
    {
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                getCell(x, y).setLite(false);
            }
        }

        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                LightUpCell cell = getCell(x, y);
                if(cell.getType() == LightUpCellType.BULB)
                {
                    cell.setLite(true);
                    for(int i = x + 1; i < width; i++)
                    {
                        LightUpCell c = getCell(i, y);
                        if(c.getType() == LightUpCellType.NUMBER || c.getType() == LightUpCellType.BLACK)
                        {
                            break;
                        }
                        c.setLite(true);
                    }
                    for(int i = x - 1; i >= 0; i--)
                    {
                        LightUpCell c = getCell(i, y);
                        if(c.getType() == LightUpCellType.NUMBER || c.getType() == LightUpCellType.BLACK)
                        {
                            break;
                        }
                        c.setLite(true);
                    }
                    for(int i = y + 1; i < height; i++)
                    {
                        LightUpCell c = getCell(x, i);
                        if(c.getType() == LightUpCellType.NUMBER || c.getType() == LightUpCellType.BLACK)
                        {
                            break;
                        }
                        c.setLite(true);
                    }
                    for(int i = y - 1; i >= 0; i--)
                    {
                        LightUpCell c = getCell(x, i);
                        if(c.getType() == LightUpCellType.NUMBER || c.getType() == LightUpCellType.BLACK)
                        {
                            break;
                        }
                        c.setLite(true);
                    }
                }
            }
        }
    }

    @Override
    public LightUpCell getCell(int x, int y)
    {
        return (LightUpCell)super.getCell(x, y);
    }

    @Override
    public void notifyChange(PuzzleElement puzzleElement)
    {
        super.notifyChange(puzzleElement);
        fillWithLight();
    }

    @Override
    public LightUpBoard copy()
    {
        LightUpBoard copy = new LightUpBoard(width, height);
        for(int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++)
            {
                copy.setCell(x, y, getCell(x, y).copy());
            }
        }
        return copy;
    }
}
