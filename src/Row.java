import java.io.Serializable;
import java.util.ArrayList;

public class Row implements Serializable {
	private ArrayList<Square> Squares = new ArrayList<Square>();
    private int position;
    private final int NUM_OF_SQUARES = 4;

    // constructor
    public Row(int position) {
        Square temporarySquare;
        for (int i = 1; i <= this.NUM_OF_SQUARES; i++) {
            temporarySquare = new Square(i);
            this.Squares.add(temporarySquare);
        }
        setPosition(position);
    }

    // getters and setters
    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return this.position;
    }

    public ArrayList<Square> getSquares() {
        return this.Squares;
    }

}
