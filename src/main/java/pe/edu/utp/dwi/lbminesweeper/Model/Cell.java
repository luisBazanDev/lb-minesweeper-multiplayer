package pe.edu.utp.dwi.lbminesweeper.Model;

public class Cell {

    private int x, y;
    private boolean isBomb, hide, target;
    private int value;

    public Cell(int x, int y, boolean isBomb) {
        this.x = x;
        this.y = y;
        this.isBomb = isBomb;
        this.value = 0;
        this.hide = false;
        this.target = false;
    }

    public int calculateBombs(Cell[] gameCells){
        if (this.isBomb) {
            return -1;
        }

        int count = 0;

        for (Cell row : gameCells) {
            double distance = Math.sqrt(Math.pow(row.y - this.y, 2) + Math.pow(row.x - this.x, 2));
            if (distance < 2 && row.isBomb) {
                count++;
            }
        }

        return count;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public Integer getValue() {
        return value;
    }

    public boolean isTarget() {
        return target;
    }

    public boolean isHide() {
        return hide;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setTarget(boolean target) {
        this.target = target;
    }
}
