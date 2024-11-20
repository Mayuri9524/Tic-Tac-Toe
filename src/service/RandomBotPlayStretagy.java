package service;

import model.*;

import java.util.List;

public class RandomBotPlayStretagy implements BoatPlayingStratergy{
    @Override
    public Move makemove(Player player, Game game) {
        Board board= game.getBoard();
        for(List<Cell> cells:board.getCells())
        {
            for(Cell cell:cells)
            {
                if(cell.getCellstates().equals(cellstates.Empty))
                {
                    cell.setCellstates(cellstates.Field);
                    cell.setPlayer(player);
                    Move move= new Move(cell,player);
                    game.getMoves().add(move);
                    game.getBoardList().add(game.getBoard().clone());
                    return move;
                }
            }
        }
        return null;

    }
}
