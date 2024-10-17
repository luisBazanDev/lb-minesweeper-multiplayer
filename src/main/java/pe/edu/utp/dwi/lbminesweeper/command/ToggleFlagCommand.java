package pe.edu.utp.dwi.lbminesweeper.command;

import pe.edu.utp.dwi.lbminesweeper.domain.enums.CommandType;

public class ToggleFlagCommand {
    private CommandType type;
    private int x;
    private int y;

    public ToggleFlagCommand() {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
