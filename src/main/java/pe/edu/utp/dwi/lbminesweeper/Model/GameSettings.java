package pe.edu.utp.dwi.lbminesweeper.Model;

public class GameSettings {
    private int HeightCells, WidthCells, OneOfEach;

    public GameSettings(int heightCells, int widthCells, int OneOfEach) {
        this.HeightCells = heightCells;
        this.WidthCells = widthCells;
        this.OneOfEach = OneOfEach;
    }

    public GameSettings() {
        this.HeightCells = 20;
        this.WidthCells = 20;
        this.OneOfEach = 7;
    }

    public int getHeightCells() {
        return HeightCells;
    }

    public int getWidthCells() {
        return WidthCells;
    }

    public int getOneOfEach() {
        return OneOfEach;
    }
}
