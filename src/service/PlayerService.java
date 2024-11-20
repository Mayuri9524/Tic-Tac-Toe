package service;

import exception.DuplicateSymbolException;
import model.Bot;
import model.BotDifficultyLevel;
import model.Player;
import model.PlayerType;

import java.util.HashSet;

public class PlayerService {
    private HashSet<Character> symbolset;
    private static int counter=1;
    public PlayerService() {
        this.symbolset = new HashSet<>();
    }

    public Player createPlayer(String name, char symbol)
    {
        if(symbolset.contains(symbol))
        {
            throw new DuplicateSymbolException("Please choose different symbol this symbol is already taken");
        }
        symbolset.add(symbol);
        Player player= new Player(
                name,
                symbol,
                counter++,
                PlayerType.Human
                );

        return player;
    }

    public Bot createBot(char symbol)
    {
        String name="Bot";
        if(symbolset.contains(symbol))
        {
            throw new DuplicateSymbolException("Please choose different symbol this symbol is already taken");
        }
        symbolset.add(symbol);
        Bot player= new Bot(
                name,
                symbol,
                counter++,
                PlayerType.Bot,
                BotDifficultyLevel.Easy
        );

        return player;
    }
}
