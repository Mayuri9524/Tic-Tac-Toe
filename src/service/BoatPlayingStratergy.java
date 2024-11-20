package service;

import model.Game;
import model.Move;
import model.Player;

public interface BoatPlayingStratergy {
    Move makemove(Player player, Game game);
}
