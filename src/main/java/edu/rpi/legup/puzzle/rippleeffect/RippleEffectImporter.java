package edu.rpi.legup.puzzle.rippleeffect;

import edu.rpi.legup.model.Puzzle;
import edu.rpi.legup.model.PuzzleImporter;
import edu.rpi.legup.save.InvalidFileFormatException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.*;

public class RippleEffectImporter extends PuzzleImporter {
    /**
     * PuzzleImporter Constructor creates the puzzle object
     *
     * @param puzzle the puzzle
     */
    public RippleEffectImporter(Puzzle puzzle) {
        super(puzzle);
    }

    /**
     * Creates the RippleEffectBoard
     * @param node xml document node
     * @throws InvalidFileFormatException when input file is invalid
     */
    @Override
    public void initializeBoard(Node node) throws InvalidFileFormatException {
        Element boardE = (Element) node;
        Element cellsE = (Element) boardE.getElementsByTagName("cells").item(0);
        NodeList cellsList = cellsE.getElementsByTagName("cell");
        RippleEffectBoard re = null;
        String sizeE = boardE.getAttribute("size");
        String widthE = boardE.getAttribute("width");
        String heightE = boardE.getAttribute("height");
        int width = -1, height = -1;
        if (!sizeE.isEmpty()) {
            width = height = Integer.valueOf(sizeE);
            re = new RippleEffectBoard(width);
        } else if (!widthE.isEmpty() && !heightE.isEmpty()) {
            width = Integer.valueOf(widthE);
            height = Integer.valueOf(heightE);
            re = new RippleEffectBoard(width, height);
        }
        if (re == null) throw new InvalidFileFormatException(
                                        "dimension tags error");

        for (int i = 0; i < cellsList.getLength(); ++i) {
            Node n = cellsList.item(i);
            if (!n.getNodeName().equals("cell"))
                throw new InvalidFileFormatException("invalid cell tag name");
            Node valN = n.getAttributes().getNamedItem("value");
            Node xN = n.getAttributes().getNamedItem("x");
            Node yN = n.getAttributes().getNamedItem("y");
            int val = Integer.valueOf(valN.getNodeValue());
            int x = Integer.valueOf(xN.getNodeValue());
            int y = Integer.valueOf(yN.getNodeValue());
            RippleEffectCell rec = new RippleEffectCell(val, new Point(x, y));
            rec.setIndex(y*width + x);
            re.setCell(x, y, rec);
        }

        for (int y = 0; y < height; ++y)
            for (int x = 0; x < width; ++x) {
                if (re.getCell(x, y) != null) continue;
                Point loc = new Point(x, y);
                RippleEffectCell rec = new RippleEffectCell(0, loc);
                rec.setIndex(y*width + x);
                rec.setModifiable(true);
                re.setCell(x, y, rec);
            }
        this.puzzle.setCurrentBoard(re);
    }
}
