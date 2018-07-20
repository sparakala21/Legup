package ai;

import model.gameboard.Board;

import java.util.ArrayList;
import java.util.List;

public class SolveNode
{
    private Board board;

    private List<SolveNode> children;

    public SolveNode(Board board)
    {
        this.board = board;
        this.children = new ArrayList<>();
    }

    public Board getBoard()
    {
        return board;
    }

    public void setBoard(Board board)
    {
        this.board = board;
    }

    public List<SolveNode> getChildren()
    {
        return children;
    }

    public void setChildren(List<SolveNode> children)
    {
        this.children = children;
    }
}
