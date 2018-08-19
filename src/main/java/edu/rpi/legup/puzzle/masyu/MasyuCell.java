package edu.rpi.legup.puzzle.masyu;

import edu.rpi.legup.model.gameboard.GridCell;

public class MasyuCell extends GridCell<Integer>
{

    public MasyuCell(int value, int x, int y)
    {
        super(value, x, y);
    }

    public MasyuType getType()
    {
        switch(data)
        {
            case 0:
                return MasyuType.UNKNOWN;
            case 1:
                return MasyuType.BLACK;
            case 2:
                return MasyuType.WHITE;
            default:
                return null;
        }
    }

    @Override
    public MasyuCell copy()
    {
        MasyuCell copy = new MasyuCell(data, x, y);
        copy.setIndex(index);
        copy.setModifiable(isModifiable);
        copy.setGiven(isGiven);
        return copy;
    }
}
