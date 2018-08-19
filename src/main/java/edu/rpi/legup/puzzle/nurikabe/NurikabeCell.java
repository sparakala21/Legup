package edu.rpi.legup.puzzle.nurikabe;

import edu.rpi.legup.model.gameboard.GridCell;

public class NurikabeCell extends GridCell<Integer>
{

    public NurikabeCell(int valueInt, int x, int y)
    {
        super(valueInt, x, y);
    }

    public NurikabeType getType()
    {
        switch(data)
        {
            case -2:
                return NurikabeType.UNKNOWN;
            case -1:
                return NurikabeType.BLACK;
            case 0:
                return NurikabeType.WHITE;
            default:
                if(data > 0)
                {
                    return NurikabeType.NUMBER;
                }
        }
        return null;
    }

    @Override
    public NurikabeCell copy()
    {
        NurikabeCell copy = new NurikabeCell(data, x, y);
        copy.setIndex(index);
        copy.setModifiable(isModifiable);
        copy.setGiven(isGiven);
        return copy;
    }
}
