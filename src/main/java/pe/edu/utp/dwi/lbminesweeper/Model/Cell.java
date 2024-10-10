package pe.edu.utp.dwi.lbminesweeper.Model;

import pe.edu.utp.dwi.lbminesweeper.domain.enums.CellState;

public class Cell {
    private final int x, y;
    private CellState state;
    /*
    Values
    -1 Mine
    0-8 Value of mines around
     */
    private final int value;

    public Cell(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.state = CellState.HIDDEN;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CellState getState() {
        return state;
    }

    public int getValue() {
        return value;
    }

    public boolean isMine() {
        return this.value == -1;
    }

    public boolean isHide() {
        return state == CellState.HIDDEN;
    }

    public void hide() {
        this.state = CellState.HIDDEN;
    }

    public void show() {
        this.state = CellState.SHOW;
    }

    @Override
    public String toString() {
        return String.format("%03d", this.value);
        // return String.format("[%s] [%d,%d] %d", this.state.toString(), this.x, this.y, this.value);
    }
}
