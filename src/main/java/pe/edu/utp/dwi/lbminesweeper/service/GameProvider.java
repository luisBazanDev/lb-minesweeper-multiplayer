package pe.edu.utp.dwi.lbminesweeper.service;

import pe.edu.utp.dwi.lbminesweeper.model.Game;

import java.util.HashMap;

public class GameProvider {
	private static HashMap<String, Game> games = new HashMap<String, Game>();

	public static Game getGame(String uuid) {
		return games.get(uuid);
	}

	public static void addGame(Game game) {
		games.put(game.getUuid(), game);
	}
}
