package model;

import exception.InvalidBoardSizeException;
import exception.InvalidNumberOfPlayers;
import service.CheckWinnerUtility;

import java.util.ArrayList;
import java.util.List;

public class Game {
private Board board;//int size
private List<Player> players;//player
private Gamestate gamestate;
private List<Move> moves;
private List<Board> boardList;
private Player winner;
private int nextMovePlayerIndex;
private CheckWinnerUtility checkWinnerUtility;

    private Game(Board board, List<Player> players,CheckWinnerUtility checkWinnerUtility) {
        this.board = board;
        this.players = players;
        this.moves = new ArrayList<>();
        this.boardList = new ArrayList<>();
        this.gamestate = Gamestate.yettostart;
        this.winner = null;
        this.nextMovePlayerIndex = 0;
        this.checkWinnerUtility=checkWinnerUtility;
    }

    public List<Board> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<Board> boardList) {
        this.boardList = boardList;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }

    public void setNextMovePlayerIndex(int nextMovePlayerIndex) {
        this.nextMovePlayerIndex = nextMovePlayerIndex;
    }

    public CheckWinnerUtility getCheckWinnerUtility() {
        return checkWinnerUtility;
    }


    public Gamestate getGamestate() {
        return gamestate;
    }

    public void setGamestate(Gamestate gamestate) {
        this.gamestate = gamestate;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public static class Builder
{
    private int size;
    private List<Player> players;
    private CheckWinnerUtility checkWinnerUtility;

    public Builder size(int size) {
        this.size = size;
      return this;
    }

    public Builder players(List<Player> players)
    {
        this.players=players;
        return this;
    }

    public Builder checkWinnerUtility(CheckWinnerUtility checkWinnerUtility) {
        this.checkWinnerUtility = checkWinnerUtility;
        return this;
    }

    public CheckWinnerUtility getCheckWinnerUtility() {
        return checkWinnerUtility;
    }

    private void validate() {
        if (size > 10 || size < 3) {
            throw new InvalidBoardSizeException("Board Size should be between 3 to 10 only");
        }

        //Player Validation
        if (players.size() != size - 1) {
            throw new InvalidNumberOfPlayers("Number of player should be size -1");
        }
    }

    public Game build()
    {    {
            validate();
            return new Game(new Board(size),players,checkWinnerUtility);
        }
    }


}

}
