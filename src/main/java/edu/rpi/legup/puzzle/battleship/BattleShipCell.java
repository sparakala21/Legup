package edu.rpi.legup.puzzle.battleship;

import edu.rpi.legup.model.gameboard.GridCell;

import java.awt.*;

public class BattleShipCell extends GridCell<Integer> {

    public BattleShipCell(int data, int x, int y) {
        super(data, x, y);
    }

    /**
     * Gets the type of this BattleShipCell
     *
     * @return type of BattleShipCell
     */
    public BattleShipCellType getType() {
        return BattleShipCellType.getType(getData());
    }

    /**
     * Performs a deep copy on the BattleShipCell
     *
     * @return a new copy of the BattleShipCell that is independent of this one
     */
    public BattleShipCell copy() {
        BattleShipCell copy = new BattleShipCell(data, x, y);
        copy.setIndex(index);
        copy.setModifiable(isModifiable);
        copy.setGiven(isGiven);
        return copy;
    }
}
