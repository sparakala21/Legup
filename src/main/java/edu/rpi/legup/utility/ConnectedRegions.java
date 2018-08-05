package edu.rpi.legup.utility;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class ConnectedRegions
{
    public static List<Set<Point2D>> getConnectedRegions(int boundaryCell, int[][] cells, int width, int height)
    {
        Set<Integer> boundaryCells = new HashSet<>();
        boundaryCells.add(boundaryCell);
        return getConnectedRegions(boundaryCells, cells, width, height);
    }

    public static List<Set<Point2D>> getConnectedRegions(Set<Integer> boundaryCells, int[][] cells, int width, int height)
    {
        boolean[][] visited = new boolean[height][width];
        List<Set<Point2D>> results = new ArrayList<>();
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                Set<Point2D> region = floodfill(boundaryCells, cells, visited, width, height, x, y);
                if(region.size() > 0)
                {
                    results.add(region);
                }
            }
        }
        return results;
    }

    public static boolean regionContains(int toFind, int[][] cells, Set<Point2D> region)
    {
        for(Point2D p : region)
        {
            if(cells[(int)p.getY()][(int)p.getX()] == toFind)
            {
                return true;
            }
        }
        return false;
    }

    public static Set<Point2D> getRegionAroundPoint(Point2D p, int boundaryCell, int[][] cells, int width, int height)
    {
        Set<Integer> boundryCells = new HashSet<>();
        boundryCells.add(boundaryCell);
        return getRegionAroundPoint(p, boundryCells, cells, width, height);
    }

    public static Set<Point2D> getRegionAroundPoint(Point2D p, Set<Integer> boundaryCells, int[][] cells, int width, int height)
    {
        return floodfill(boundaryCells, cells, new boolean[height][width], width, height, (int)p.getX(), (int)p.getY());
    }

    private static Set<Point2D> floodfill(Set<Integer> boundaryCells, int[][] cells, boolean[][] visited, int w, int h, int x, int y)
    {
        HashSet<Point2D> result = new HashSet<>();
        if((x < 0) || (x >= w))
        {
            return result;
        }
        if((y < 0) || (y >= h))
        {
            return result;
        }
        if(!visited[y][x] && (!boundaryCells.contains(cells[y][x])))
        {
            result.add(new Point2D(x, y));
            visited[y][x] = true;
            for(int delta = -1; delta < 2; delta += 2)
            {
                result.addAll(floodfill(boundaryCells, cells, visited, w, h, x + delta, y));
                result.addAll(floodfill(boundaryCells, cells, visited, w, h, x, y + delta));
            }
        }
        return result;
    }
}