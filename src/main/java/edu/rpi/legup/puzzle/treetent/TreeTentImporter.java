package edu.rpi.legup.puzzle.treetent;

import edu.rpi.legup.model.PuzzleImporter;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import edu.rpi.legup.save.InvalidFileFormatException;

import java.awt.*;

public class TreeTentImporter extends PuzzleImporter
{
    public TreeTentImporter(TreeTent treeTent)
    {
        super(treeTent);
    }

    /**
     * Creates the board for building
     *
     * @param node xml document node
     * @throws InvalidFileFormatException
     */
    @Override
    public void initializeBoard(Node node) throws InvalidFileFormatException
    {
        try
        {
            if(!node.getNodeName().equalsIgnoreCase("board"))
            {
                throw new InvalidFileFormatException("TreeTent Importer: cannot find board element");
            }
            Element boardElement = (Element)node;
            if(boardElement.getElementsByTagName("cells").getLength() == 0)
            {
                throw new InvalidFileFormatException("TreeTent Importer: no element found for board");
            }
            Element dataElement = (Element)boardElement.getElementsByTagName("cells").item(0);
            NodeList elementDataList = dataElement.getElementsByTagName("cell");

            TreeTentBoard treeTentBoard = null;
            if(!boardElement.getAttribute("size").isEmpty())
            {
                int size = Integer.valueOf(boardElement.getAttribute("size"));
                treeTentBoard = new TreeTentBoard(size);
            }
            else if(!boardElement.getAttribute("width").isEmpty() && !boardElement.getAttribute("height").isEmpty())
            {
                int width = Integer.valueOf(boardElement.getAttribute("width"));
                int height = Integer.valueOf(boardElement.getAttribute("height"));
                treeTentBoard = new TreeTentBoard(width, height);
            }

            if(treeTentBoard == null)
            {
                throw new InvalidFileFormatException("TreeTent Importer: invalid board dimensions");
            }

            int width = treeTentBoard.getWidth();
            int height = treeTentBoard.getHeight();

            for(int i = 0; i < elementDataList.getLength(); i++)
            {
                TreeTentCell cell = (TreeTentCell)puzzle.getFactory().importCell(elementDataList.item(i), treeTentBoard);
                Point loc = cell.getLocation();
                if(cell.getData() != 0)
                {
                    cell.setModifiable(false);
                    cell.setGiven(true);
                }
                treeTentBoard.setCell(loc.x, loc.y, cell);
            }

            for(int y = 0; y < height; y++)
            {
                for(int x = 0; x < width; x++)
                {
                    if(treeTentBoard.getCell(x, y) == null)
                    {
                        TreeTentCell cell = new TreeTentCell(0, new Point(x, y));
                        cell.setIndex(y * height + x);
                        cell.setModifiable(true);
                        treeTentBoard.setCell(x, y, cell);
                    }
                }
            }

            for(int i = 0; i < treeTentBoard.getHeight(); i++)
            {
                int value = 0;
                int index = i + 1;

                treeTentBoard.getEast().set(index - 1, new TreeTentClue(value, index, TreeTentType.CLUE_EAST));
            }

            for(int i = 0; i < treeTentBoard.getWidth(); i++)
            {
                int value = 0;
                int index = i + 1;

                treeTentBoard.getSouth().set(index - 1, new TreeTentClue(value, index, TreeTentType.CLUE_SOUTH));
            }

            if(boardElement.getElementsByTagName("lines").getLength() == 1)
            {
                Element linesElement = (Element)boardElement.getElementsByTagName("lines").item(0);
                NodeList linesList = linesElement.getElementsByTagName("line");
                for(int i = 0; i < linesList.getLength(); i++)
                {
                    treeTentBoard.getLines().add((TreeTentLine)puzzle.getFactory().importCell(linesList.item(i), treeTentBoard));
                }
            }

            puzzle.setCurrentBoard(treeTentBoard);
        }
        catch(NumberFormatException e)
        {
            throw new InvalidFileFormatException("TreeTent Importer: unknown value where integer expected");
        }
    }
}
