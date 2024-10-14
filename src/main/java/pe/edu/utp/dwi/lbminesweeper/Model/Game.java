package pe.edu.utp.dwi.lbminesweeper.Model;

import pe.edu.utp.dwi.lbminesweeper.domain.enums.CellState;

public class Game {
    private GameSettings gameSettings;
    private Cell[][] cells;
    private boolean gameOver, isWin;

    public Game(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
        this.cells = new Cell[gameSettings.getWidth()][gameSettings.getHeight()];
        this.gameOver = false;
        this.isWin = false;

        fillCells();
    }

    private void fillCells(){
        int amount = this.gameSettings.getHeight() * this.gameSettings.getWidth() / this.gameSettings.getOneOfEach();

        for (int i = 0; i < amount; i++) {
            int x = getRandomNumber(0, gameSettings.getWidth() - 1);
            int y = getRandomNumber(0, gameSettings.getHeight() - 1);

            this.cells[x][y] = new Cell(x, y, -1);
        }

        for (int i = 0; i < this.gameSettings.getWidth(); i++) {
            for (int j = 0; j < this.gameSettings.getHeight(); j++) {
                if(cells[i][j] == null) {
                    cells[i][j] = new Cell(i, j, calculateMines(i, j));
                }
            }
        }
    }

    private int calculateMines(int x, int y) {
        int amount = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    Cell cell = this.cells[x - 1 + i][y - 1 + j];

                    if(cell != null && cell.isMine()) amount++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }

        return amount;
    }

    private int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    public GameSettings getGameSettings() {
        return gameSettings;
    }

    public Cell[][] getCells() {
        return cells;
    }

    /**
     * 0-8 value, -2 hidden
     * @return
     */
    public int[][] getObfuscatedCells() {
        int[][] shadowCells = new int[gameSettings.getWidth()][gameSettings.getHeight()];

        for (int i = 0; i < gameSettings.getWidth(); i++) {
            for (int j = 0; j < gameSettings.getHeight(); j++) {
                Cell cell = this.cells[i][j];
                shadowCells[i][j] = cell.getState() == CellState.SHOW ? cell.getValue() : -2;
            }
        }

        return shadowCells;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isWin() {
        return isWin;
    }

    public void gameOver() {
        this.gameOver = true;

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.show();
            }
        }
    }

    public void win() {
        this.isWin = true;

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.show();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if(cell == null) {
                    for (int i = 0; i < 18; i++) {
                        stringBuilder.append("*");
                    }
                    stringBuilder.append(" ");
                    continue;
                }
                stringBuilder.append(String.format("%d ", cell.getValue()));
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
