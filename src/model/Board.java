package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> cells;
    private int size;

    public Board(int size) {
        this.size = size;

        //N->N X N
        // N arrayList and each arrayList will contain arrayList of N cells
        // 3x3
        /*
        [[C C C],
        [C C C],
        [C C C]
         */

        cells= new ArrayList<>();
        /// Creates entire board from this
        for(int i=0;i<size;i++)
        {   cells.add(new ArrayList<>());
            for(int j=0;j<size;j++)
            {
                cells.get(i).add(new Cell(i,j));
            }
        }
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Board clone()
    {
        Board board= new Board((this.size));
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                board.cells.get(i).set(j,cells.get(i).get(j).clone());
            }
        }
        return board;
    }
}
