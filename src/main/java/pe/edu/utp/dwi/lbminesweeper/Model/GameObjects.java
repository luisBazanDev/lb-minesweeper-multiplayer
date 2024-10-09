package pe.edu.utp.dwi.lbminesweeper.Model;

public class GameObjects{
    private GameSettings gameSettings;
    private Cell[] cells;
    private boolean gameOver, isWin;

    public GameObjects(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
        this.cells = new Cell[gameSettings.getWidthCells()];
        this.gameOver = false;
        this.isWin = false;
    }

    public GameObjects(Cell[] cells) {
        this.gameSettings = new GameSettings();
        this.cells = cells;
    }

    public GameObjects(GameSettings gameSettings, Cell[] cells) {
        this.gameSettings = gameSettings;
        this.cells = cells;
        this.gameOver = false;
        this.isWin = false;
    }

    public GameSettings getGameSettings() {
        return gameSettings;
    }

    public Cell[] getCells() {
        return cells;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;

        for (Cell cell : cells) {
            cell.setHide(true);
        }
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    private int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    public void fillCells(){
        for (int x = 0; x < gameSettings.getWidthCells(); x++){
            for (int y = 0; y < gameSettings.getHeightCells(); y++){

                Cell cell = new Cell(x, y, getRandomNumber(0, gameSettings.getOneOfEach()) == 0);

                cells[x] = cell;
            }
        }
    }
}
