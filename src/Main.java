import controller.GameController;
import exception.GameDrawException;
import model.*;
import service.BoardService;
import service.GameService;
import service.PlayerService;
import service.RandomBotPlayStretagy;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        PlayerService playerService = new PlayerService();
        BoardService boardService = new BoardService();
        GameService gameService= new GameService(boardService);
        RandomBotPlayStretagy randomBotPlayStretagy= new RandomBotPlayStretagy();
        GameController gameController= new GameController(playerService,gameService,randomBotPlayStretagy);

        System.out.println(" WELCOME TO TIC-TAC-TOE GAME");
        Scanner sc= new Scanner(System.in);

        System.out.println(" Please Enter Board size ");
        int size= sc.nextInt();
        List<Player> players=gameController.generatePlayerList(size);

        Game game= gameService.createGame(size,players);
        gameService.startGame(game);
        while(true){
        int nextPlayerIndex=game.getNextMovePlayerIndex();
        Player currentPlayer= game.getPlayers().get(nextPlayerIndex);
        System.out.println("Please make the move : "+currentPlayer.getName());
        if(!currentPlayer.getPlayerType().equals(PlayerType.Bot))
        {System.out.println("Do you want to undo to a certain step? if yes enter 1 or enter 0 ");
        int undo=sc.nextInt();
        if(undo==1)
        {
            System.out.println("Please enter the number of moves you want to go back");
            int undomove=sc.nextInt();
            game=gameController.undogame(undomove,game);
        }}
        //boardService.printBoard(game.getBoard());
        Move move=gameController.createMove(currentPlayer,game);
        boardService.printBoard(game.getBoard());
        try{
        Player winner= gameController.checkWinner(game.getBoard(),move,game.getCheckWinnerUtility());

        if(winner!=null)
        {
            //break;
            game.setGamestate(Gamestate.finishdone);

            System.out.println("winner is: "+winner.getName());
            System.out.println("Do you want a replay? if yes enter 1, else 0");
            int replay=sc.nextInt();
            if(replay==1)
            {
                gameController.replay(game);
                break;
            }
            break;
        }}catch(GameDrawException ex)
        {
            System.out.println("Game is over as it is draw, please start again");
        }
        game.setNextMovePlayerIndex(((game.getNextMovePlayerIndex())+1)%players.size());
    }}

}