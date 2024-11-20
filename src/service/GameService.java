package service;

import exception.InvalidCellChoosenException;
import model.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GameService {
    private BoardService boardService;


    public GameService(BoardService boardService) {
        this.boardService = boardService;
    }

    public Game createGame(int size, List<Player> players)
    {  CheckWinnerUtility checkWinnerUtility = new CheckWinnerUtility(size);
       Game newGame= Game.builder()
                          .size(size)
                          .players(players)
                          .checkWinnerUtility(checkWinnerUtility)
                          .build();
       return newGame;
    }

    public void startGame(Game game)
    {
        game.setGamestate(Gamestate.inprogress);
        List<Player> players= game.getPlayers();
        Collections.shuffle(players);
        game.setPlayers(players);
    }



    public Move executeMove(Player player , Game game, int row, int col)
    {
        Cell cell = game.getBoard().getCells().get(row).get(col);
        if(cell.getCellstates()!=cellstates.Empty)
        {
            throw new InvalidCellChoosenException(" Choose proper cell this cell is already full");
        }
        cell.setCellstates(cellstates.Field);
        cell.setPlayer(player);
        Move move= new Move(cell,player);
        game.getMoves().add(move);
        game.getBoardList().add(game.getBoard().clone());
        return move;
    }

    public Game undoMove(int moves,Game game)
    {
        List<Board> playedBoard= game.getBoardList();
        List<Move> moveList = game.getMoves();
        int movesplayed= moveList.size();

        moveList=moveList.subList(0,movesplayed-moves);
        //everytime if ur doing sublist on anything it is inclusive or not
        game.setBoard(playedBoard.getLast());
        return game;
    }

    public void replay(Game game)
    {
        for(Board board: game.getBoardList())
        {
            boardService.printBoard(board);
            System.out.println("------------------------Next Board--------------------------------");
        }
    }
}
