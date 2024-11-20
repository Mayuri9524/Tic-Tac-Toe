package model;

public class Cell {
    private int row;
    private int col;
    private cellstates cellstates;
    private Player player;

    public Cell(int row,int col){
        this.row=row;
        this.col=col;
        cellstates= model.cellstates.Empty;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public model.cellstates getCellstates() {
        return cellstates;
    }

    public void setCellstates(model.cellstates cellstates) {
        this.cellstates = cellstates;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Cell clone()
    {
        Cell cell= new Cell(this.row,this.col);
        cell.player=this.player;
        cell.cellstates=this.cellstates;
        return cell;

    }
}
