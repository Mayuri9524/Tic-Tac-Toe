package controller;

import exception.InvalidCellChoosenException;
import exception.InvalidMoveToUndo;
import model.*;
import service.CheckWinnerUtility;
import service.GameService;
import service.PlayerService;
import service.RandomBotPlayStretagy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
    private PlayerService playerService;
    private Scanner sc;
    private GameService gameService;
    private RandomBotPlayStretagy randomBotPlayStretagy;


    public GameController(PlayerService playerService,GameService gameService,RandomBotPlayStretagy randomBotPlayStretagy) {
        this.playerService = playerService;
        this.gameService=gameService;
        this.sc= new Scanner(System.in);
        this.randomBotPlayStretagy=randomBotPlayStretagy;
    }

    public List<Player> generatePlayerList(int size)
    {
        List<Player> players= new ArrayList<>();
        //bot logic
        System.out.println("Please enter 1 if you want a bot else enter 0");
        int n=sc.nextInt();
        n=sc.nextInt();
        Bot bot;

        if(n==1)
        {
            bot=playerService.createBot('$');
            size--;
            players.add(bot);
        }


        for(int i=0;i<size-1;i++)
        {
            System.out.println("Please Enter the name of the player: " +(i+1));
            String name= sc.nextLine();
            name= sc.nextLine();
            System.out.println("Please Enter the Symbol of the player: " +name);
            String symbolInput = sc.nextLine();
            char symbol= symbolInput.charAt(0);
            players.add(playerService.createPlayer(name,symbol));
        }
        return players;
    }

    public Move createMove(Player player, Game game)
    {   int row,col;
        if(player.getPlayerType().equals(PlayerType.Human)){
        System.out.println("Please enter the row number");
        row=sc.nextInt();
        System.out.println("Please enter the col number");
        col=sc.nextInt();
        try {
            Move move = gameService.executeMove(player, game, row, col);
            return move;
        }
        catch(InvalidCellChoosenException ex)
        {
            createMove(player, game);
        }}
        else {
            Move move=randomBotPlayStretagy.makemove(player,game);
            return move;
        }
        return  null;
        }
    public Player checkWinner(Board board, Move move, CheckWinnerUtility checkWinnerUtility)
    {
        return checkWinnerUtility.checkwinner(board,move);
    }


    public Game undogame(int undomove, Game game)
    {

        if(undomove>game.getMoves().size())
        {
            throw new InvalidMoveToUndo("This is invalid move");
        }
        return gameService.undoMove(undomove,game);

    }

    public void replay(Game game)
    {
        gameService.replay(game);
    }
}
// steps after playing  a move
// update the board and print
// check the winner
//nextplayer
//store move and store board