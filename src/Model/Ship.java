package Model;
import java.io.Serializable;

public class Ship implements Serializable {
	protected int row;
	protected int square;	

	// constructor
	public Ship(int row, int square) {
		setRow(row);
		setSquare(square);
	}

	// getters and setters
	public int getSquare() {
		return square;
	}

	public void setSquare(int square) {
		this.square = square;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

}
