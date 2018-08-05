package edu.rpi.legup.puzzle.nurikabe;

import edu.rpi.legup.model.gameboard.GridCell;
import javafx.geometry.Point2D;

public class NurikabeCell extends GridCell
{

    public NurikabeCell(int valueInt, Point2D location)
    {
        super(valueInt, location);
    }

    /**
     * Gets the int value that represents this element
     *
     * @return int value
     */
    @Override
    public Integer getData()
    {
        return (Integer)super.getData();
    }

    public NurikabeType getType()
    {
        Integer value = getData();
        switch(value)
        {
            case -2:
                return NurikabeType.UNKNOWN;
            case -1:
                return NurikabeType.BLACK;
            case 0:
                return NurikabeType.WHITE;
            default:
                if(value > 0)
                {
                    return NurikabeType.NUMBER;
                }
        }
        return null;
    }

    @Override
    public NurikabeCell copy()
    {
        NurikabeCell copy = new NurikabeCell((Integer) data, location);
        copy.setIndex(index);
        copy.setModifiable(isModifiable);
        copy.setGiven(isGiven);
        return copy;
    }
}
