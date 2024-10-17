package pe.edu.utp.dwi.lbminesweeper.command;

import pe.edu.utp.dwi.lbminesweeper.domain.enums.CommandType;

public class GenericCommand {
    private CommandType type;

    public GenericCommand() {

    }

    public CommandType getType() {
        return type;
    }
}
