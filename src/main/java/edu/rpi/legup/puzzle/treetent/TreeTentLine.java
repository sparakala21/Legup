package edu.rpi.legup.puzzle.treetent;

import edu.rpi.legup.model.gameboard.PuzzleElement;

public class TreeTentLine extends PuzzleElement
{
    private TreeTentCell c1, c2;

    public TreeTentLine(TreeTentCell c1, TreeTentCell c2)
    {
        this.c1 = c1;
        this.c2 = c2;

    }

    public TreeTentCell getC1()
    {
        return c1;
    }

    public void setC1(TreeTentCell c1)
    {
        this.c1 = c1;
    }

    public TreeTentCell getC2()
    {
        return c2;
    }

    public void setC2(TreeTentCell c2)
    {
        this.c2 = c2;
    }

    public boolean compare(TreeTentLine line){
        return (line.getC1().isSameElement(c1) && line.getC2().isSameElement(c2)) ||
                (line.getC1().isSameElement(c2) && line.getC2().isSameElement(c1));
    }

    /**
     * Copies this elements element to a new Element object
     *
     * @return copied Element object
     */
    @Override
    public TreeTentLine copy()
    {
        return new TreeTentLine(c1.copy(), c2.copy());
    }
}
