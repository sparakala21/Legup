package ai;

import model.gameboard.Board;
import model.tree.Tree;
import puzzle.sudoku.Sudoku;
import puzzle.sudoku.SudokuBoard;
import puzzle.sudoku.SudokuCell;
import save.ExportFileException;

import java.awt.*;
import java.util.Random;

public class SudokuPuzzleGenerator
{

    private Random rand;
    private Sudoku sudoku;

    public SudokuPuzzleGenerator()
    {
        this.rand = new Random();
        this.sudoku = new Sudoku();
    }

    public SudokuPuzzleGenerator(int seed)
    {
        this.rand = new Random(seed);
    }

    public void newRandom(String fileName) throws ExportFileException
    {
        SudokuBoard board = generateRandomBoard(9);
        Tree tree = new Tree(board);
        sudoku.setTree(tree);

        sudoku.getExporter().exportPuzzle(fileName);
    }

    public SudokuBoard generateRandomBoard(int size)
    {
        Random rand = new Random();
        return generateRandomBoard(size, rand.nextInt());
    }

    public SudokuBoard generateRandomBoard(int size, int difficulty)
    {
        SudokuBoard board = new SudokuBoard(size);
        int minorSize = (int)Math.sqrt(size);
        for(int y = 0; y < size; y++)
        {
            for(int x = 0; x < size; x++)
            {
                int groupIndex = x / minorSize * minorSize + y / minorSize;
                SudokuCell cell = new SudokuCell(0, new Point(x, y), groupIndex);
                board.setCell(x, y, cell);
                cell.setIndex(y * size + x);
            }
        }
        SolveNode solveNode = new SolveNode(board);
        return (SudokuBoard) gen(solveNode, 0, 0);
    }

    private Board gen(SolveNode node, int x, int y)
    {
        SudokuBoard board = (SudokuBoard)node.getBoard();
        board.getCell(x, y).setValueInt(rand.nextInt(9) + 1);

//        if(!sudoku.isContraditoryBoard(board))
//        {
            if(sudoku.isBoardComplete(board))
            {
                return board;
            }
            else
            {
                for(int y1 = y + 1; y1 < board.getSize(); y1++)
                {
                    for(int x1 = x + 1; x1 < board.getSize(); x1++)
                    {
                        SolveNode newNode = new SolveNode(board.copy());
                        Board newBoard = gen(newNode, x1, y1);
                        if(newBoard != null)
                        {
                            return newBoard;
                        }
                    }
                }
            }
//        }
        return null;
    }

    public static void main(String[] args) throws ExportFileException
    {
        SudokuPuzzleGenerator gen = new SudokuPuzzleGenerator();
        gen.newRandom("C:\\Users\\jeffp\\Documents\\mysudoku.txt");
    }
}
