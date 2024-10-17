package pe.edu.utp.dwi.lbminesweeper.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import pe.edu.utp.dwi.lbminesweeper.domain.Cell;
import pe.edu.utp.dwi.lbminesweeper.domain.GameSettings;
import pe.edu.utp.dwi.lbminesweeper.domain.ObfuscatedCell;

public class Game {
    private final String uuid;
    private final GameSettings gameSettings;
    private final Cell[][] cells;
    private boolean gameOver, isWin = false;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public Game(String uuid, GameSettings gameSettings) {
        this.uuid = uuid;
        this.gameSettings = gameSettings;
        this.cells = new Cell[gameSettings.getWidth()][gameSettings.getHeight()];

        fillCells();
    }

    public void discoverCell(int x, int y) {
        Cell cell = cells[x][y];

        if (!cell.isHide())
            return;

        if (cell.isFlag()) return;

        if (cell.isMine()) {
            gameOver();
            return;
        }

        cell.show();
        if(cell.getValue() == 0) discoverRecursiveCells(x, y);

        if(checkWin()) {
            win();
        }
    }

    private void toggleFlag(int x, int y) {
        Cell cell = cells[x][y];

        if(cell.isHide()) cell.flag();
        else if(cell.isFlag()) cell.hide();
    }

    private void discoverRecursiveCells(int x, int y) {
        System.out.printf("Discovering recursive cells X: %d, Y %d%n", x, y);
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                try {
                    Cell cell = cells[i][j];
                    if(!cell.isHide()) continue;
                    if(cell.isFlag()) continue;
                    cell.show();
                    if(cell.getValue() == 0) discoverRecursiveCells(i, j);
                } catch (IndexOutOfBoundsException e) {}
            }
        }
    }

    private void fillCells() {
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

    public String getUuid() {
        return uuid;
    }

    public ObfuscatedCell[][] getObfuscatedCells() {
        ObfuscatedCell[][] shadowCells = new ObfuscatedCell[gameSettings.getWidth()][gameSettings.getHeight()];

        for (int i = 0; i < gameSettings.getWidth(); i++) {
            for (int j = 0; j < gameSettings.getHeight(); j++) {
                Cell cell = this.cells[i][j];
                shadowCells[i][j] = new ObfuscatedCell(cell, this.isWin() || this.isGameOver());
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

    public void processMove(int x, int y, boolean isFlag) {
        if (isFlag) {
            toggleFlag(x, y);
        } else {
            discoverCell(x, y);
        }
    }

    private boolean checkWin() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell.isHide() && !cell.isMine()) {
                    return false;
                }
            }
        }
        return true;
    }
}
