package edu.rpi.legup.puzzle.rippleeffect;

import edu.rpi.legup.ui.boardview.GridElementView;

import java.awt.*;

public class RippleEffectElementView extends GridElementView {

    private static final Font NUM_FONT = new Font("TimesRoman",Font.BOLD,16);

    /**
     * Initializes a RippleEffectElementView
     * @param cell cell on the grid
     */
    public RippleEffectElementView(RippleEffectCell cell) {
        super(cell);
    }

    /**
     * @return cell used in this view.
     */
    @Override
    public RippleEffectCell getPuzzleElement() {
        return (RippleEffectCell) super.getPuzzleElement();
    }

    /**
     * Performs the drawing operation.
     * @param g2d drawing object for 2d graphics
     */
    @Override
    public void drawElement(Graphics2D g2d) {
        RippleEffectCell cell = (RippleEffectCell) super.puzzleElement;
        Point loc = this.location;
        Dimension dim = super.size;

        // start with a gray background
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(loc.x, loc.y, dim.width, dim.height);

        // draw the main square around the cell
        // TODO deal with region borders
        g2d.setColor(Color.BLACK);
        g2d.drawRect(loc.x, loc.y, dim.width, dim.height);

        // draw number in cell (color already set to black)
        if (cell.getData() != 0) {
            g2d.setFont(NUM_FONT);
            FontMetrics fm = g2d.getFontMetrics(NUM_FONT);
            String num = String.valueOf(cell.getData());
            // compute position for string drawing (reused from Nurikabe)
            int tx = loc.x + (dim.width - fm.stringWidth(num))/2;
            int ty = loc.y + (dim.height - fm.getHeight())/2 + fm.getAscent();
            g2d.drawString(num, tx, ty);
        }
    }
}
