package pe.edu.utp.dwi.lbminesweeper.command;

import com.google.gson.Gson;
import pe.edu.utp.dwi.lbminesweeper.domain.ObfuscatedCell;
import pe.edu.utp.dwi.lbminesweeper.domain.enums.CommandType;
import pe.edu.utp.dwi.lbminesweeper.domain.enums.GameState;
import pe.edu.utp.dwi.lbminesweeper.model.Game;
import pe.edu.utp.dwi.lbminesweeper.service.GameProvider;

public class SyncCommand {
    private CommandType type;
    private long timestamp;
    private ObfuscatedCell[][] cells;
    private GameState state;

    public SyncCommand(String uuid) {
        this.type = CommandType.SYNC;
        this.timestamp = System.currentTimeMillis();

        Game game = GameProvider.getGame(uuid);
        this.cells = game.getObfuscatedCells();
        this.state = game.isWin() ?
                GameState.WIN :
                game.isGameOver() ?
                        GameState.GAME_OVER :
                        GameState.PROGRESS;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
