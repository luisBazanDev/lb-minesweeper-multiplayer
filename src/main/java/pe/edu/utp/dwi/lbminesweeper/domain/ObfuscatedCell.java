package pe.edu.utp.dwi.lbminesweeper.domain;

import pe.edu.utp.dwi.lbminesweeper.domain.enums.ObfuscatedCellType;

public class ObfuscatedCell {
	private int x;
	private int y;
	private int value;
	private ObfuscatedCellType type;


	/**
	 * 0-8 value, -1 flag, -2 hidden
	 * @return
	 */
	public ObfuscatedCell(Cell cell) {
		this.x = cell.getX();
		this.y = cell.getY();
		this.value = cell.isHide() ? -2 : cell.isFlag() ? -1 : cell.getValue();
		this.type = cell.isHide() ? ObfuscatedCellType.NONE : cell.isMine() ? ObfuscatedCellType.MINE : ObfuscatedCellType.NONE;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getValue() {
		return value;
	}

	public ObfuscatedCellType getType() {
		return type;
	}
}
