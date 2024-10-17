package pe.edu.utp.dwi.lbminesweeper.command;

import pe.edu.utp.dwi.lbminesweeper.domain.enums.CommandType;

public class DiscoverCommand {
    private CommandType type;
    private int x;
    private int y;

    public DiscoverCommand() {}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
