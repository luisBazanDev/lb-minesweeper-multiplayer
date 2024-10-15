package pe.edu.utp.dwi.lbminesweeper.domain;

public class GameSettings {
    private int height, width, OneOfEach;

    public GameSettings(int height, int width, int OneOfEach) {
        this.height = height;
        this.width = width;
        this.OneOfEach = OneOfEach;
    }

    public GameSettings() {
        this.height = 20;
        this.width = 20;
        this.OneOfEach = 7;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getOneOfEach() {
        return OneOfEach;
    }
}
